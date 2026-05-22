private class HeaderWrapperRequest extends HttpServletRequestWrapper {
        private PushbackInputStream inputStream = null;
        private ServletInputStream servletInputStream = null;
        private HttpServletRequest origRequest = null;
        private BufferedReader reader = null;

        private final Map<String, String> newHeaders = new HashMap<String, String>();


        /**
         * @param request
         * @throws IOException
         */
        public HeaderWrapperRequest( HttpServletRequest request ) throws IOException {
            super( request );
            origRequest = request;
            inputStream = new PushbackInputStream( request.getInputStream() );
            servletInputStream = new DelegatingServletInputStream( inputStream );
        }


        /**
         * @throws IOException
         *
         */
        private void adapt() throws IOException {

            String path = origRequest.getRequestURI();
            String method = origRequest.getMethod();
            if (logger.isTraceEnabled()) {
                logger.trace("Content path is '{}'", path);
            }


            // first ensure that an Accept header is set

            @SuppressWarnings( "rawtypes" ) Enumeration acceptHeaders = origRequest.getHeaders( HttpHeaders.ACCEPT );
            if ( !acceptHeaders.hasMoreElements() ) {
                setHeader( HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON );
            }

            // next, ensure that one and only one content-type is set

            int initial = inputStream.read();
            if ( initial == -1 ) {

                // request has no body, set type to application/json
                if ( ( HttpMethod.POST.equals( method ) || HttpMethod.PUT.equals( method ) )
                    && !MediaType.APPLICATION_FORM_URLENCODED.equals( getContentType() ) ) {
                    if (logger.isTraceEnabled()) {
                        logger.trace("Setting content type to application/json for POST or PUT with no content at path '{}'", path);
                    }

                    setHeader( HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON );
                    setHeader( HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON );
                }

                return;
            }

            char firstChar = ( char ) initial;
            if ( ( firstChar == '{' || firstChar == '[' )
                 && !MediaType.APPLICATION_JSON.equals( getContentType() )) {

                // request appears to be JSON so set type to application/json
                if (logger.isTraceEnabled()) {
                    logger.trace("Setting content type to application/json for POST or PUT with json content at path '{}'", path);
                }
                setHeader( HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON );
                setHeader( HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON );
            }

            inputStream.unread( initial );
        }


        public void setHeader( String name, String value ) {
            newHeaders.put( name.toLowerCase(), value );
        }


        /*
         * (non-Javadoc)
         *
         * @see
         * javax.servlet.http.HttpServletRequestWrapper#getHeader(java.lang.
         * String)
         */
        @Override
        public String getHeader( String name ) {
            String header = newHeaders.get( name );

            if ( header != null ) {
                return header;
            }

            return super.getHeader( name );
        }


        /*
         * (non-Javadoc)
         *
         * @see
         * javax.servlet.http.HttpServletRequestWrapper#getHeaders(java.lang
         * .String)
         */
        @Override
        public Enumeration getHeaders( String name ) {

            Set<String> headers = new LinkedHashSet<String>();
            String overridden = newHeaders.get( name );

            if ( overridden != null ) {
                headers.add( overridden );
                return Collections.enumeration( headers );
            }

            return super.getHeaders( name );
        }


        /*
         * (non-Javadoc)
         *
         * @see javax.servlet.http.HttpServletRequestWrapper#getHeaderNames()
         */
        @Override
        public Enumeration getHeaderNames() {
            Set<String> headers = new LinkedHashSet<String>();

            for ( Enumeration e = super.getHeaderNames(); e.hasMoreElements(); ) {
                headers.add( e.nextElement().toString() );
            }

            headers.addAll( newHeaders.keySet() );

            return Collections.enumeration( headers );
        }


        /*
         * (non-Javadoc)
         *
         * @see javax.servlet.ServletRequestWrapper#getInputStream()
         */
        @Override
        public ServletInputStream getInputStream() throws IOException {
            return servletInputStream;
        }


        /*
         * (non-Javadoc)
         *
         * @see javax.servlet.ServletRequestWrapper#getReader()
         */
        @Override
        public BufferedReader getReader() throws IOException {
            if ( reader != null ) {
                return reader;
            }

            reader = new BufferedReader( new InputStreamReader( servletInputStream ) );

            return reader;
        }

        // NOTE, for full override we need to implement the other getHeader
        // methods. We won't use it, so I'm not implementing it here
    }
