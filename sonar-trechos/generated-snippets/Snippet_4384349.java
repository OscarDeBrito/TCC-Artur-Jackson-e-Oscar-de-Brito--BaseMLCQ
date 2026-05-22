public class Snippet__4384349 {

    public String getText()
        {
            // entry/searchresult/bookmark
            IEntry[] entries = getEntries();
            if ( entries != null )
            {
                return entries.length > 1 ? Messages.getString( "CopyAction.CopyEntriesDNs" ) : Messages.getString( "CopyAction.CopyEntryDN" ); //$NON-NLS-1$ //$NON-NLS-2$
            }

            // searches
            ISearch[] searches = getSearches();
            if ( searches != null )
            {
                return searches.length > 1 ? Messages.getString( "CopyAction.CopySearches" ) : Messages.getString( "CopyAction.CopySearch" ); //$NON-NLS-1$ //$NON-NLS-2$
            }

            // values
            IValue[] values = getValues();
            if ( values != null )
            {
                return values.length > 1 ? Messages.getString( "CopyAction.CopyValues" ) : Messages.getString( "CopyAction.CopyValue" ); //$NON-NLS-1$ //$NON-NLS-2$
            }

            return Messages.getString( "CopyAction.Copy" ); //$NON-NLS-1$
        }

}
