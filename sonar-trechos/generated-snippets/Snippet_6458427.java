public class Snippet__6458427 {

    public String deleteMapping ( final String channelId, final String name )
        {
            if ( !this.idToNameMap.containsEntry ( channelId, name ) )
            {
                return null;
            }

            this.nameToIdMap.remove ( name );
            this.idToNameMap.remove ( channelId, name );

            final Collection<String> names = this.model.getNameMap ().get ( channelId );
            if ( names != null )
            {
                names.remove ( name );
            }

            return channelId;
        }

}
