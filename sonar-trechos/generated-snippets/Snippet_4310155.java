public class StoreSubstringFilterType extends GrammarAction<LdapMessageContainer<SearchRequest>>
{
    /** The logger */
    private static final Logger LOG = LoggerFactory.getLogger( StoreSubstringFilterType.class );


    /**
     * Instantiates a new action.
     */
    public StoreSubstringFilterType()
    {
        super( "Store Substring filter type" );
    }


    /**
     * {@inheritDoc}
     */
    public void action( LdapMessageContainer<SearchRequest> container ) throws DecoderException
    {
        TLV tlv = container.getCurrentTLV();

        // Store the value.
        SubstringFilter substringFilter = ( SubstringFilter ) container.getTerminalFilter();

        if ( tlv.getLength() == 0 )
        {
            String msg = I18n.err( I18n.ERR_05153_NULL_ATTRIBUTE_DESCRIPTION );
            LOG.error( msg );
            throw new DecoderException( msg );
        }
        else
        {
            String type = Strings.utf8ToString( tlv.getValue().getData() );
            substringFilter.setType( type );

            // We now have to get back to the nearest filter which
            // is not terminal.
            container.setTerminalFilter( substringFilter );
        }
    }
}
