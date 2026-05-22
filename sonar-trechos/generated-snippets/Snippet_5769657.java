public class Snippet__5769657 {

    @Override
        public void setIntValues(final Item orgUnitItem, final Attribute attr, final RealmTO realmTO) {
            List<Object> values = null;
            if (attr != null) {
                values = attr.getValue();
                for (ItemTransformer transformer : MappingUtils.getItemTransformers(orgUnitItem)) {
                    values = transformer.beforePull(orgUnitItem, realmTO, values);
                }
            }

            if (values != null && !values.isEmpty() && values.get(0) != null) {
                switch (orgUnitItem.getIntAttrName()) {
                    case "name":
                        realmTO.setName(values.get(0).toString());
                        break;

                    case "fullpath":
                        String parentFullPath = StringUtils.substringBeforeLast(values.get(0).toString(), "/");
                        Realm parent = realmDAO.findByFullPath(parentFullPath);
                        if (parent == null) {
                            LOG.warn("Could not find Realm with path {}, ignoring", parentFullPath);
                        } else {
                            realmTO.setParent(parent.getFullPath());
                        }
                        break;

                    default:
                }
            }
        }

}
