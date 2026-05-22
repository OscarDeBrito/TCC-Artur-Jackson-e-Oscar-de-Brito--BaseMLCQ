public class Snippet__4162084 {

    @Override
        public StoredObject createPolicy(String name, String policyText, Map<String, PropertyData<?>> propMap, String user,
                Acl addACEs, Acl removeACEs) {
            PolicyImpl policy = new PolicyImpl();
            policy.createSystemBasePropertiesWhenCreated(propMap, user);
            policy.setCustomProperties(propMap);
            policy.setRepositoryId(fRepositoryId);
            policy.setName(name);
            policy.setPolicyText(policyText);
            String id = storeObject(policy);
            policy.setId(id);
            applyAcl(policy, addACEs, removeACEs);
            return policy;
        }

}
