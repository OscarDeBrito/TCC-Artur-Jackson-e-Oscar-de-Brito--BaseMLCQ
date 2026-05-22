private static class TypeRequirementVisitor extends AbstractQueryModelVisitor<RuntimeException> {
        private static final Var RDF_TYPE_VAR = VarNameUtils.createUniqueConstVar(RDF.TYPE);
        private static final Set<Resource> BASE_TYPES = Sets.newHashSet(RDFS.RESOURCE, OWL.THING);
        static {
            RDF_TYPE_VAR.setConstant(true);
        }

        private final String varName;
        private final StatementPattern typeRequirement;
        public TypeRequirementVisitor(String varName, Resource requiredType) {
            final Var typeVar = VarNameUtils.createUniqueConstVar(requiredType);
            typeVar.setConstant(true);
            this.varName = varName;
            if (BASE_TYPES.contains(requiredType)) {
                this.typeRequirement = null;
            }
            else {
                this.typeRequirement = new StatementPattern(new Var(varName), RDF_TYPE_VAR, typeVar);
            }
        }
        @Override
        public void meet(SingletonSet node) {
            if (typeRequirement != null) {
                node.replaceWith(typeRequirement);
            }
        }
        @Override
        public void meet(Extension node) {
            Set<String> argBindings = node.getArg().getBindingNames();
            if (typeRequirement != null) {
                node.getElements().removeIf(elem -> {
                    if (varName.equals(elem.getName())) {
                        ValueExpr expr = elem.getExpr();
                        if (expr == null) {
                            return true;
                        }
                        else if (expr instanceof Var) {
                            String fromName = ((Var) expr).getName();
                            if (getVarValue((Var) expr) == null && !argBindings.contains(fromName)) {
                                return true;
                            }
                        }
                    }
                    return false;
                });
                meetUnaryTupleOperator(node);
            }
        }
        @Override
        public void meetNode(QueryModelNode node) {
            if (typeRequirement != null) {
                if (node instanceof TupleExpr && ((TupleExpr) node).getBindingNames().contains(varName)) {
                    final Join withType = new Join((TupleExpr) node.clone(), typeRequirement);
                    node.replaceWith(withType);
                }
                else {
                    node.visitChildren(this);
                }
            }
        }
        @Override
        public void meetUnaryTupleOperator(UnaryTupleOperator node) {
            if (typeRequirement != null) {
                if (node.getArg().getBindingNames().contains(varName)) {
                    node.visitChildren(this);
                }
                else {
                    meetNode(node);
                }
            }
        }
    }
