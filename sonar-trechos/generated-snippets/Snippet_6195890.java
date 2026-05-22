public class Snippet__6195890 {

    @Override
      protected void configure() {
        bind(FactoryDao.class).to(JpaFactoryDao.class);
        bind(RemoveFactoriesBeforeUserRemovedEventSubscriber.class).asEagerSingleton();
      }

}
