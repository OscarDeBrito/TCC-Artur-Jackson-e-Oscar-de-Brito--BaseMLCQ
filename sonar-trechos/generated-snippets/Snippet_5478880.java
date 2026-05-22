public class Snippet__5478880 {

    @Override
        public void open()
        {
            Collection<LinkDefinition<Source, Target>> links = _linkStore.openAndLoad(new LinkStoreUpdaterImpl());
            for(LinkDefinition<Source, Target> link: links)
            {
                ConcurrentMap<LinkKey, Link_1_0<S,T>> linkRegistry = getLinkRegistry(link.getRole());
                LinkDefinition<S, T> definition = (LinkDefinition<S, T>) link;
                linkRegistry.put(new LinkKey(link), new LinkImpl<>(definition, this));
            }
        }

}
