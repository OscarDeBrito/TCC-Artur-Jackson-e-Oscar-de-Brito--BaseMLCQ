public class Snippet__4830091 {

    @Override
        protected void configure(final Marshaller marshaller) {
            marshaller.setAdapter(PersistentEntityAdapter.class,
                    serviceRegistry.injectServicesInto(new PersistentEntityAdapter()));
            marshaller.setAdapter(PersistentEntitiesAdapter.class,
                    serviceRegistry.injectServicesInto(new PersistentEntitiesAdapter()));
        }

}
