public class SectionCutterAction extends ConfigurableServiceableAction implements ThreadSafe {

    Vector sections = new Vector();

    /**
     *  Description of the Method
     *
     * @param  conf                        Description of Parameter
     * @exception  ConfigurationException  Description of Exception
     */
    public void configure(Configuration conf)
        throws ConfigurationException {
        try {
            Configuration[] sectionConfigurations;
            sectionConfigurations = conf.getChildren("section");

            for (int i = 0; i < sectionConfigurations.length; i++) {
                try {
                    if (getLogger().isDebugEnabled()) {
                        getLogger().debug("Creating one section");
                    }
                    sections.add(new Section(sectionConfigurations[i]));
                } catch (Exception e) {
                    getLogger().error("Failed configuring section", e);
                    if (getLogger().isDebugEnabled()) {
                        // In production, try to continue. Assume that one rotten section config can't stop the whole app.
                        // When debug is enabled, scream, screech and grind to a halt.
                        throw (e);
                    }
                }
            }
        } catch (Exception e) {
            throw new ConfigurationException("Cannot configure action", e);
        }
    }

    /**
     *  A simple Action that logs if the <code>Session</code> object has been
     *  created
     *
     * @param  redirector     Description of Parameter
     * @param  resolver       Description of Parameter
     * @param  objectModel    Description of Parameter
     * @param  src            Description of Parameter
     * @param  par            Description of Parameter
     * @return                Description of the Returned Value
     * @exception  Exception  Description of Exception
     */
    public Map act(Redirector redirector, SourceResolver resolver, Map objectModel, String src, Parameters par)
        throws Exception {
        Request request = ObjectModelHelper.getRequest(objectModel);
        Map results = new HashMap();
        if (request != null) {
            boolean hasMatched = false;

            if (getLogger().isDebugEnabled()) {
                getLogger().debug("Matching against '" + request.getSitemapURI() + "'");
            }
            for (Enumeration sectionsEnum = sections.elements(); sectionsEnum.hasMoreElements() && !hasMatched; ) {
                Section section = (Section) sectionsEnum.nextElement();
                if (section.matches(request.getSitemapURI())) {
                    if (getLogger().isDebugEnabled()) {
                        getLogger().debug("Matched '" + section.matchExpression + "'");
                    }

                    section.fillMap(results);
                    hasMatched = true;
                }
            }
        } else {
            getLogger().warn("Request was null");
        }

        return Collections.unmodifiableMap(results);
    }

    /**
     *  Description of the Class
     *
     */
    static class Section extends Object {
        String matchExpression;
        Dictionary mapVars = new Hashtable();

        /**
         *  Constructor for the Section object
         *
         * @param  conf           Description of Parameter
         * @exception  Exception  Description of Exception
         */
        public Section(Configuration conf)
            throws Exception {
            matchExpression = conf.getAttribute("pattern");
            Configuration[] variables;
            variables = conf.getChildren("set-var");

            for (int i = 0; i < variables.length; i++) {
                mapVars.put(variables[i].getAttribute("name"), variables[i].getAttribute("value"));
            }
        }

        /**
         *  Description of the Method
         *
         * @param  expression  Description of Parameter
         * @return             Description of the Returned Value
         */
        public boolean matches(String expression) {
            return expression.startsWith(matchExpression);
        }

        /**
         *  Description of the Method
         *
         * @param  map  Description of Parameter
         */
        public void fillMap(Map map) {
            for (Enumeration keys = mapVars.keys(); keys.hasMoreElements(); ) {
                Object key = keys.nextElement();
                Object value = mapVars.get(key);

                map.put(key, value);
            }
        }
    }
}
