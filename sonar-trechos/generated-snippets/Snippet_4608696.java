private class StateStackElement {
        final VariableScope scope;
        final ClassNode clazz;
        final boolean inConstructor;

        StateStackElement() {
            scope = VariableScopeVisitor.this.currentScope;
            clazz = VariableScopeVisitor.this.currentClass;
            inConstructor = VariableScopeVisitor.this.inConstructor;
        }
    }
