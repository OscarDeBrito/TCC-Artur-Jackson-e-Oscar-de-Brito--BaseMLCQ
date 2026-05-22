public interface Customer170Repository extends CrudRepository<Customer170, Long> {

	List<Customer170> findByLastName(String lastName);
}
