public class ExtensionMethodNode extends MethodNode {
    private final MethodNode extensionMethodNode;
    private final boolean isStaticExtension; // true if it's a static method
    
    public ExtensionMethodNode(
            MethodNode extensionMethodNode,
            String name, int modifiers, ClassNode returnType, Parameter[] parameters, ClassNode[] exceptions, Statement code, boolean isStaticExtension) {
        super(name, modifiers, returnType, parameters, exceptions, code);
        this.extensionMethodNode = extensionMethodNode;
        this.isStaticExtension = isStaticExtension;
    }

    public ExtensionMethodNode(
            MethodNode extensionMethodNode,
            String name, int modifiers, ClassNode returnType, Parameter[] parameters, ClassNode[] exceptions, Statement code) {
        this(extensionMethodNode, name, modifiers, returnType, parameters, exceptions, code, false);
    }

    public MethodNode getExtensionMethodNode() {
        return extensionMethodNode;
    }

    public boolean isStaticExtension() {
        return isStaticExtension;
    }
}
