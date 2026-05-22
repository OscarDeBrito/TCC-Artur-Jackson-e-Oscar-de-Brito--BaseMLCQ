public class Snippet__5767507 {

    @Override
        public PrivilegeTO getPrivilegeTO(final Privilege privilege) {
            PrivilegeTO privilegeTO = new PrivilegeTO();
            privilegeTO.setKey(privilege.getKey());
            privilegeTO.setDescription(privilege.getDescription());
            privilegeTO.setApplication(privilege.getApplication().getKey());
            privilegeTO.setSpec(privilege.getSpec());
            return privilegeTO;
        }

}
