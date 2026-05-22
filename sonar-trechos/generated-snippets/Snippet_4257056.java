public class FingerClient extends SocketClient
{
    /***
     * The default FINGER port.  Set to 79 according to RFC 1288.
     ***/
    public static final int DEFAULT_PORT = 79;

    private static final String __LONG_FLAG = "/W ";

    private transient char[] __buffer = new char[1024];

    /***
     * The default FingerClient constructor.  Initializes the
     * default port to <code> DEFAULT_PORT </code>.
     ***/
    public FingerClient()
    {
        setDefaultPort(DEFAULT_PORT);
    }


    /***
     * Fingers a user at the connected host and returns the output
     * as a String.  You must first connect to a finger server before
     * calling this method, and you should disconnect afterward.
     *
     * @param longOutput Set to true if long output is requested, false if not.
     * @param username  The name of the user to finger.
     * @return The result of the finger query.
     * @throws IOException If an I/O error occurs while reading the socket.
     ***/
    public String query(boolean longOutput, String username) throws IOException
    {
        int read;
        StringBuilder result = new StringBuilder(__buffer.length);
        BufferedReader input;

        input =
            new BufferedReader(new InputStreamReader(getInputStream(longOutput,
                               username), getCharset()));

        try {
            while (true)
            {
                read = input.read(__buffer, 0, __buffer.length);
                if (read <= 0) {
                    break;
                }
                result.append(__buffer, 0, read);
            }
        } finally {
            input.close();
        }

        return result.toString();
    }


    /***
     * Fingers the connected host and returns the output
     * as a String.  You must first connect to a finger server before
     * calling this method, and you should disconnect afterward.
     * This is equivalent to calling <code> query(longOutput, "") </code>.
     *
     * @param longOutput Set to true if long output is requested, false if not.
     * @return The result of the finger query.
     * @throws IOException If an I/O error occurs while reading the socket.
     ***/
    public String query(boolean longOutput) throws IOException
    {
        return query(longOutput, "");
    }


    /***
     * Fingers a user and returns the input stream from the network connection
     * of the finger query.  You must first connect to a finger server before
     * calling this method, and you should disconnect after finishing reading
     * the stream.
     *
     * @param longOutput Set to true if long output is requested, false if not.
     * @param username  The name of the user to finger.
     * @return The InputStream of the network connection of the finger query.
     *         Can be read to obtain finger results.
     * @throws IOException If an I/O error during the operation.
     ***/
    public InputStream getInputStream(boolean longOutput, String username)
    throws IOException
    {
        return getInputStream(longOutput, username, null);
    }

    /***
     * Fingers a user and returns the input stream from the network connection
     * of the finger query.  You must first connect to a finger server before
     * calling this method, and you should disconnect after finishing reading
     * the stream.
     *
     * @param longOutput Set to true if long output is requested, false if not.
     * @param username  The name of the user to finger.
     * @param encoding the character encoding that should be used for the query,
     *        null for the platform's default encoding
     * @return The InputStream of the network connection of the finger query.
     *         Can be read to obtain finger results.
     * @throws IOException If an I/O error during the operation.
     ***/
    public InputStream getInputStream(boolean longOutput, String username, String encoding)
    throws IOException
    {
        DataOutputStream output;
        StringBuilder buffer = new StringBuilder(64);
        if (longOutput) {
            buffer.append(__LONG_FLAG);
        }
        buffer.append(username);
        buffer.append(SocketClient.NETASCII_EOL);

        // Note: Charsets.toCharset() returns the platform default for null input
        byte[] encodedQuery = buffer.toString().getBytes(Charsets.toCharset(encoding).name()); // Java 1.6 can use charset directly

        output = new DataOutputStream(new BufferedOutputStream(_output_, 1024));
        output.write(encodedQuery, 0, encodedQuery.length);
        output.flush();

        return _input_;
    }


    /***
     * Fingers the connected host and returns the input stream from
     * the network connection of the finger query.  This is equivalent to
     * calling getInputStream(longOutput, "").  You must first connect to a
     * finger server before calling this method, and you should disconnect
     * after finishing reading the stream.
     *
     * @param longOutput Set to true if long output is requested, false if not.
     * @return The InputStream of the network connection of the finger query.
     *         Can be read to obtain finger results.
     * @throws IOException If an I/O error during the operation.
     ***/
    public InputStream getInputStream(boolean longOutput) throws IOException
    {
        return getInputStream(longOutput, "");
    }

}
