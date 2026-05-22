public final class VoidCallInstruction extends VoidInstruction implements FunctionStart {

    private SymbolImpl target;

    private final SymbolImpl[] arguments;

    private final AttributesCodeEntry paramAttr;

    private VoidCallInstruction(AttributesCodeEntry paramAtt, int argCount) {
        this.arguments = argCount == 0 ? NO_ARGS : new SymbolImpl[argCount];
        this.paramAttr = paramAtt;
    }

    @Override
    public void accept(SymbolVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public SymbolImpl[] getArguments() {
        return arguments;
    }

    @Override
    public SymbolImpl getCallTarget() {
        return target;
    }

    @Override
    public AttributesGroup getFunctionAttributesGroup() {
        return paramAttr.getFunctionAttributesGroup();
    }

    @Override
    public AttributesGroup getReturnAttributesGroup() {
        return paramAttr.getReturnAttributesGroup();
    }

    @Override
    public AttributesGroup getParameterAttributesGroup(int idx) {
        return paramAttr.getParameterAttributesGroup(idx);
    }

    @Override
    public void replace(SymbolImpl original, SymbolImpl replacement) {
        if (target == original) {
            target = replacement;
        }
        for (int i = 0; i < arguments.length; i++) {
            if (arguments[i] == original) {
                arguments[i] = replacement;
            }
        }
    }

    public static VoidCallInstruction fromSymbols(IRScope scope, int targetIndex, int[] arguments, AttributesCodeEntry paramAttr) {
        final VoidCallInstruction inst = new VoidCallInstruction(paramAttr, arguments.length);
        inst.target = scope.getSymbols().getForwardReferenced(targetIndex, inst);
        FunctionStart.parseArguments(scope, inst.target, inst, inst.arguments, arguments);
        return inst;
    }

    @Override
    public String toString() {
        return FunctionStart.asString(target, arguments);
    }
}
