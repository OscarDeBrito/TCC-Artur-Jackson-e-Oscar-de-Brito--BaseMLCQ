public interface Customer840Repository extends CrudRepository<Customer840, Long> {

	List<Customer840> findByLastName(String lastName);
}
