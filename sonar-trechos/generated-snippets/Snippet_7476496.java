public interface Customer1569Repository extends CrudRepository<Customer1569, Long> {

	List<Customer1569> findByLastName(String lastName);
}
