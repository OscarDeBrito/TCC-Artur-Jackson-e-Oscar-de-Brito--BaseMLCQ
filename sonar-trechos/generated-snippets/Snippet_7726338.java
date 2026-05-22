private static class MyXMLChangeLogSerializer extends XMLChangeLogSerializer {
        private final PostgresLiquibaseSnapshot currentSnapshot;

        MyXMLChangeLogSerializer(PostgresLiquibaseSnapshot currentSnapshot) {
            this.currentSnapshot = currentSnapshot;
        }

        @Override
        public Element createNode(LiquibaseSerializable object) {
            Element element = super.createNode(object);

            if (object instanceof ChangeSet) {
                ChangeSet changeSet = (ChangeSet) object;
                changeSet.getChanges().forEach(change -> {
                    if (change instanceof CreateIndexChange) {
                        CreateIndexChange cic = (CreateIndexChange) change;
                        if (cic.getColumns().size() != 1) {
                            return;
                        }

                        ColumnDescription cd = this.currentSnapshot
                                .getColumnDescriptionByIndexId(cic.getIndexName());
                        String indexType = cd != null ? cd.getIndexType() : null;

                        if (indexType != null) {
                            Document doc = element.getOwnerDocument();
                            Element modifySqlElement = doc.createElement("modifySql");
                            Element replaceElement = doc.createElement("regExpReplace");
                            replaceElement.setAttribute("replace", "\\(.+\\)");
                            replaceElement.setAttribute("with",
                                    String.format(" USING %s $0", indexType));
                            modifySqlElement.appendChild(replaceElement);
                            element.appendChild(modifySqlElement);
                        }
                    }
                });
            }
            return element;
        }
    }
