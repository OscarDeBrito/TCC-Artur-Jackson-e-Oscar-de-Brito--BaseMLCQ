public interface Customer1677Repository extends CrudRepository<Customer1677, Long> {

	List<Customer1677> findByLastName(String lastName);
}
