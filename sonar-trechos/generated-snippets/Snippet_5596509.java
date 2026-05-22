public class Snippet__5596509 {

    @Override
        public void emitPackageContents(IPackageDefinition definition)
        {
            IASScope containedScope = definition.getContainedScope();
            ITypeDefinition type = findType(containedScope.getAllLocalDefinitions());
            if (type == null)
                return;

            write("function($exports");

            exportWriter.queueExports(type, false);

            write(") {");
            indentPush();
            writeNewline();
            write("\"use strict\"; ");
            writeNewline();

            ITypeNode tnode = findTypeNode(definition.getNode());
            if (tnode != null)
            {
                getWalker().walk(tnode); // IClassNode | IInterfaceNode
            }

            indentPop();
            writeNewline();
            write("}"); // end returned function
        }

}
