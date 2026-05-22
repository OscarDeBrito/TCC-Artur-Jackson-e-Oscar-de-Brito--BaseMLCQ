public interface GetStackSpaceFactory {

    LLVMExpressionNode createGetStackSpace(LLVMContext context, Type type);

    static GetStackSpaceFactory createAllocaFactory() {
        return (context, type) -> context.getNodeFactory().createAlloca(type);
    }

    static GetStackSpaceFactory createGetUniqueStackSpaceFactory(UniquesRegion uniquesRegion) {
        return (context, type) -> context.getNodeFactory().createGetUniqueStackSpace(type, uniquesRegion);
    }

}
