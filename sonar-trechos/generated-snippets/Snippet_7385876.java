public class Snippet__7385876 {

    @Before
        public void setup() throws Exception {
            RepositoryFactorySupport factory = new CouchbaseRepositoryFactory(operationsMapping, indexManager);
            bookRepository = factory.getRepository(BookRepository.class);
            authorRepository = factory.getRepository(AuthorRepository.class);
            addressRepository = factory.getRepository(AddressRepository.class);
        }

}
