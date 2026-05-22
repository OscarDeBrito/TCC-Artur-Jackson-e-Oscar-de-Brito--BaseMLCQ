public class VMSVersioningFTPEntryParser extends VMSFTPEntryParser
{

    private final Pattern _preparse_pattern_;
    private static final String PRE_PARSE_REGEX =
        "(.*?);([0-9]+)\\s*.*";

    /**
     * Constructor for a VMSFTPEntryParser object.
     *
     * @throws IllegalArgumentException
     * Thrown if the regular expression is unparseable.  Should not be seen
     * under normal conditions.  It it is seen, this is a sign that
     * <code>REGEX</code> is  not a valid regular expression.
     */
    public VMSVersioningFTPEntryParser()
    {
        this(null);
    }

    /**
     * This constructor allows the creation of a VMSVersioningFTPEntryParser
     * object with something other than the default configuration.
     *
     * @param config The {@link FTPClientConfig configuration} object used to
     * configure this parser.
     * @throws IllegalArgumentException
     * Thrown if the regular expression is unparseable.  Should not be seen
     * under normal conditions.  It it is seen, this is a sign that
     * <code>REGEX</code> is  not a valid regular expression.
     * @since 1.4
     */
    public VMSVersioningFTPEntryParser(FTPClientConfig config)
    {
        super();
        configure(config);
        try
        {
            //_preparse_matcher_ = new Perl5Matcher();
            _preparse_pattern_ = Pattern.compile(PRE_PARSE_REGEX);
        }
        catch (PatternSyntaxException pse)
        {
            throw new IllegalArgumentException (
                "Unparseable regex supplied:  " + PRE_PARSE_REGEX);
        }

   }

    /**
     * Implement hook provided for those implementers (such as
     * VMSVersioningFTPEntryParser, and possibly others) which return
     * multiple files with the same name to remove the duplicates ..
     *
     * @param original Original list
     *
     * @return Original list purged of duplicates
     */
    @Override
    public List<String> preParse(List<String> original) {
        HashMap<String, Integer> existingEntries = new HashMap<String, Integer>();
        ListIterator<String> iter = original.listIterator();
        while (iter.hasNext()) {
            String entry = iter.next().trim();
            MatchResult result = null;
            Matcher _preparse_matcher_ = _preparse_pattern_.matcher(entry);
            if (_preparse_matcher_.matches()) {
                result = _preparse_matcher_.toMatchResult();
                String name = result.group(1);
                String version = result.group(2);
                Integer nv = Integer.valueOf(version);
                Integer existing = existingEntries.get(name);
                if (null != existing) {
                    if (nv.intValue() < existing.intValue()) {
                        iter.remove();  // removes older version from original list.
                        continue;
                    }
                }
                existingEntries.put(name, nv);
            }

        }
        // we've now removed all entries less than with less than the largest
        // version number for each name that were listed after the largest.
        // we now must remove those with smaller than the largest version number
        // for each name that were found before the largest
        while (iter.hasPrevious()) {
            String entry = iter.previous().trim();
            MatchResult result = null;
            Matcher _preparse_matcher_ = _preparse_pattern_.matcher(entry);
            if (_preparse_matcher_.matches()) {
                result = _preparse_matcher_.toMatchResult();
                String name = result.group(1);
                String version = result.group(2);
                Integer nv = Integer.valueOf(version);
                Integer existing = existingEntries.get(name);
                if (null != existing) {
                    if (nv.intValue() < existing.intValue()) {
                        iter.remove(); // removes older version from original list.
                    }
                }
            }

        }
        return original;
    }


    @Override
    protected boolean isVersioning() {
        return true;
    }

}
