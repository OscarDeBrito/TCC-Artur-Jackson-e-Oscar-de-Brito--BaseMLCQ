public class Snippet__4634991 {

    private void finishIndyCall(Handle bsmHandle, String methodName, String sig, int numberOfArguments, Object... bsmArgs) {
            CompileStack compileStack = controller.getCompileStack();
            OperandStack operandStack = controller.getOperandStack();

            controller.getMethodVisitor().visitInvokeDynamicInsn(methodName, sig, bsmHandle, bsmArgs);

            operandStack.replace(ClassHelper.OBJECT_TYPE, numberOfArguments);
            compileStack.popLHS();
        }

}
