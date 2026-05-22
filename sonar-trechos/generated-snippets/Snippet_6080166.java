abstract class CondList {
        protected final Node thenPart;
        protected final java.util.List<Tree.Condition> conditions;
        
        public CondList(java.util.List<Tree.Condition> conditions, Tree.Block thenPart) {
            this.conditions = conditions;
            this.thenPart = thenPart;
        }
        public CondList(java.util.List<Tree.Condition> conditions, Tree.Expression thenPart) {
            this.conditions = conditions;
            this.thenPart = thenPart;
        }
        

        protected Cond getConditionTransformer(Tree.Condition cond) {
            return getConditionTransformer(cond, null);
        }

        protected Cond getConditionTransformer(Tree.Condition cond, Tree.Variable elseVariable) {
            if (cond instanceof Tree.IsCondition) {
                Tree.IsCondition is = (Tree.IsCondition)cond;
                IsVarTrans var = new IsVarTrans(is.getVariable());
                IsVarTrans elseVar = (elseVariable != null) ? new IsVarTrans(elseVariable, var.getTestVariableName()) : null;
                return new IsCond(is, var, elseVar);
            } else if (cond instanceof Tree.ExistsCondition) {
                Tree.ExistsCondition exists = (Tree.ExistsCondition)cond;
                ExistsVarTrans var = new ExistsVarTrans(exists.getVariable());
                ExistsVarTrans elseVar = (elseVariable != null) ? new ExistsVarTrans(elseVariable, var.getTestVariableName()) : null;
                return new ExistsCond(exists, var, elseVar);
            } else if (cond instanceof Tree.NonemptyCondition) {
                Tree.NonemptyCondition nonempty = (Tree.NonemptyCondition)cond;
                NonemptyVarTrans var = new NonemptyVarTrans(nonempty.getVariable());
                NonemptyVarTrans elseVar = (elseVariable != null) ? new NonemptyVarTrans(elseVariable, var.getTestVariableName()) : null;
                return new NonemptyCond(nonempty, var, elseVar);
            } else if (cond instanceof Tree.BooleanCondition) {
                if (this instanceof AssertCondList) {
                    Tree.Term booleanExpr = TreeUtil.unwrapExpressionUntilTerm(((Tree.BooleanCondition)cond).getExpression());
                    boolean negated;
                    if (booleanExpr instanceof Tree.NotOp) {
                        negated = true;
                        booleanExpr = TreeUtil.unwrapExpressionUntilTerm(((Tree.NotOp)booleanExpr).getTerm());
                    } else {
                        negated = false;
                    }
                    if (booleanExpr instanceof Tree.IsOp) {
                        return new IsOpBooleanCond((Tree.BooleanCondition)cond, negated, (Tree.IsOp)booleanExpr);
                    } else if (booleanExpr instanceof Tree.EqualityOp
                            ||booleanExpr instanceof Tree.ComparisonOp) {
                        return new EqualityOpBooleanCond((Tree.BooleanCondition)cond, negated, (Tree.BinaryOperatorExpression)booleanExpr);
                    } else if (booleanExpr instanceof Tree.WithinOp) {
                        return new WithinOpBooleanCond((Tree.BooleanCondition)cond, negated, (Tree.WithinOp)booleanExpr);
                    }
                }
                return new BooleanCond((Tree.BooleanCondition)cond);
            }
            throw BugException.unhandledNodeCase(cond);
        }
        
        protected List<JCStatement> transformList(java.util.List<Tree.Condition> conditions) {
            Tree.Condition condition = conditions.get(0);
            at(condition);
            if (conditions.size() == 1) {
                return transformInnermost(condition);
            } else {
                return transformIntermediate(condition, conditions.subList(1, conditions.size()));
            }
        }

        protected abstract List<JCStatement> transformInnermost(Tree.Condition condition);
        
        protected List<JCStatement> transformIntermediate(Tree.Condition condition, java.util.List<Tree.Condition> rest) {
            return transformList(rest);
        }
        
        public abstract List<JCStatement> getResult();
    }
