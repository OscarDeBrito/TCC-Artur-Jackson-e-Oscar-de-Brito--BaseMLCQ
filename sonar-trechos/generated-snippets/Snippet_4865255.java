public class Snippet__4865255 {

    public QPropertyDefinition[] getMandatoryQPropertyDefinitions() {
            // since mandatory items must have a name,
            // we're only searching the named item definitions
            if (namedItemDefs.size() == 0) {
                return QPropertyDefinition.EMPTY_ARRAY;
            }
            ArrayList<QItemDefinition> defs = new ArrayList<QItemDefinition>(namedItemDefs.size());
            for (List<QItemDefinition> list : namedItemDefs.values()) {
                for (QItemDefinition qDef : list) {
                    if (!qDef.definesNode() && qDef.isMandatory()) {
                        defs.add(qDef);
                    }
                }
            }
            if (defs.size() == 0) {
                return QPropertyDefinition.EMPTY_ARRAY;
            }
            return defs.toArray(new QPropertyDefinition[defs.size()]);
        }

}
