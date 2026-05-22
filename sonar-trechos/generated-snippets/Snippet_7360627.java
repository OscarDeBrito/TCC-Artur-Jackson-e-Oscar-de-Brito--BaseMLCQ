public class Snippet__7360627 {

    @Override
    	public ReactiveCassandraBatchOperations delete(Iterable<?> entities, WriteOptions options) {

    		assertNotExecuted();

    		Assert.notNull(entities, "Entities must not be null");
    		Assert.notNull(options, "WriteOptions must not be null");

    		this.batchMonos.add(Mono.just(doDelete(entities, options)));

    		return this;
    	}

}
