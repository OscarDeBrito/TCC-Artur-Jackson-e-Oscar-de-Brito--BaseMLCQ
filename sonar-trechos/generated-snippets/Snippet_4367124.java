public class ParamTypeSpecVisitor extends VisitorBase {

    public ParamTypeSpecVisitor(Scope scope,
                                Definition defn,
                                XmlSchema schemaRef,
                                WSDLASTVisitor wsdlVisitor) {
        super(scope, defn, schemaRef, wsdlVisitor);
    }

    public void visit(AST node) {
        // <param_type_spec> ::= <base_type_spec>
        //                     | <string_type>
        //                     | <wstring_type>
        //                     | <scoped_name>


        Visitor visitor = null;


        if (PrimitiveTypesVisitor.accept(node)) {
            // base_type_spec
            visitor = new PrimitiveTypesVisitor(getScope(), definition, schema, schemas);
        } else if (StringVisitor.accept(node)) {
            // string_type_spec
            // wstring_type_spec
            visitor = new StringVisitor(getScope(), definition, schema, wsdlVisitor, null);

        } else if (ScopedNameVisitor.accept(getScope(), definition, schema, node, wsdlVisitor)) {
            // scoped_name
            visitor = new ScopedNameVisitor(getScope(), definition, schema, wsdlVisitor);

        } else {
            throw new RuntimeException("[ParamTypeSpecVisitor] Invalid IDL: unknown element "
                                       + node.toString());
        }

        visitor.visit(node);
        setSchemaType(visitor.getSchemaType());
        setCorbaType(visitor.getCorbaType());
        setFullyQualifiedName(visitor.getFullyQualifiedName());
    }
}
