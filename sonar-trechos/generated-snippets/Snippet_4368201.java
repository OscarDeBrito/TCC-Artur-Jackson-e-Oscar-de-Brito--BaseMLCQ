public class TypedefVisitor extends VisitorBase {

    public TypedefVisitor(Scope scope,
                          Definition defn,
                          XmlSchema schemaRef,
                          WSDLASTVisitor wsdlVisitor) {
        super(scope, defn, schemaRef, wsdlVisitor);
    }

    public static boolean accept(AST node) {
        return node.getType() == IDLTokenTypes.LITERAL_typedef;
    }

    public void visit(AST typedefNode) {
        // "typedef" <type_declarator>
        // <type_declarator> ::= <type_spec> <declarators>

        AST typeDeclaratorNode = typedefNode.getFirstChild();
        AST identifierNode = TypesUtils.getCorbaTypeNameNode(typeDeclaratorNode);
        TypesVisitor typesVisitor = new TypesVisitor(getScope(),
                                                     definition,
                                                     schema,
                                                     wsdlVisitor,
                                                     identifierNode);
        typesVisitor.visit(typeDeclaratorNode);

        XmlSchemaType schemaType = typesVisitor.getSchemaType();
        CorbaType corbaType = typesVisitor.getCorbaType();
        Scope fullyQualifiedName = typesVisitor.getFullyQualifiedName();
        Scope typedefScope = new Scope(getScope(), identifierNode);

        if (SequenceVisitor.accept(typeDeclaratorNode)
            || FixedVisitor.accept(typeDeclaratorNode)) {
            // Handle cases "typedef sequence"
            //              "typedef fixed"
            DeclaratorVisitor declaratorVisitor = new DeclaratorVisitor(typedefScope,
                                                                        definition,
                                                                        schema,
                                                                        wsdlVisitor,
                                                                        schemaType,
                                                                        corbaType,
                                                                        fullyQualifiedName);
            declaratorVisitor.visit(identifierNode);

        } else if (StringVisitor.accept(typeDeclaratorNode)) {
            // Handle cases "typedef string"
            //              "typedef wstring"

            if (StringVisitor.isBounded(typeDeclaratorNode)
                && !wsdlVisitor.getBoundedStringOverride()) {
                DeclaratorVisitor declaratorVisitor = new DeclaratorVisitor(typedefScope,
                                                                            definition,
                                                                            schema,
                                                                            wsdlVisitor,
                                                                            schemaType,
                                                                            corbaType,
                                                                            fullyQualifiedName);
                declaratorVisitor.visit(identifierNode);

            } else {
                // unbounded string type is already in the XmlSchema and only needs to be added
                // to the CorbaTypeMap, therefore we cannot use DeclaratorVisitor here.

                while (identifierNode != null) {
                    if (ArrayVisitor.accept(identifierNode)) {
                        ArrayVisitor arrayVisitor = new ArrayVisitor(new Scope(getScope(),
                                                                               identifierNode.getText()),
                                                                     definition,
                                                                     schema,
                                                                     wsdlVisitor,
                                                                     identifierNode,
                                                                     fullyQualifiedName);
                        arrayVisitor.setSchemaType(schemaType);
                        arrayVisitor.setCorbaType(corbaType);
                        arrayVisitor.visit(identifierNode);

                    } else {
                        generateStringAlias(typeDeclaratorNode,
                                            identifierNode,
                                            schemaType,
                                            corbaType,
                                            fullyQualifiedName);
                    }
                    identifierNode = identifierNode.getNextSibling();
                }
            }

        } else {
            // typedef used to define an alias
            // if declaring an array, do not generate aliases
            if (!ArrayVisitor.accept(identifierNode)) {
                generateAlias(identifierNode,
                              schemaType,
                              corbaType,
                              fullyQualifiedName);
                corbaType = getCorbaType();
            }
            DeclaratorVisitor declaratorVisitor = new DeclaratorVisitor(typedefScope,
                                                                        definition,
                                                                        schema,
                                                                        wsdlVisitor,
                                                                        schemaType,
                                                                        corbaType,
                                                                        fullyQualifiedName);
            declaratorVisitor.visit(identifierNode);

        }


        setSchemaType(schemaType);
        setCorbaType(corbaType);
        setFullyQualifiedName(fullyQualifiedName);
    }

    private void generateAlias(AST identifierNode,
                               XmlSchemaType schemaType,
                               CorbaType corbaType,
                               Scope fqName) {

        Scope scopedName = new Scope(getScope(), identifierNode);
        // corba:alias
        Alias alias = new Alias();
        alias.setQName(new QName(typeMap.getTargetNamespace(), scopedName.toString()));
        if (corbaType != null) {
            alias.setBasetype(corbaType.getQName());
        // if (schemaType == null) might not be correct here
        } else if (schemaType == null) {
            wsdlVisitor.getDeferredActions().
                add(fqName, new TypedefDeferredAction(alias));
            scopedNames.add(scopedName);
        }
        alias.setRepositoryID(scopedName.toIDLRepositoryID());

        // add corba:alias
        setCorbaType(alias);
    }

    private void generateStringAlias(AST typeDeclaratorNode,
                                     AST identifierNode,
                                     XmlSchemaType schemaType,
                                     CorbaType corbaType,
                                     Scope fqName) {

        Scope typedefScope = new Scope(getScope(), identifierNode);

        Alias corbaString = new Alias();
        if (typeDeclaratorNode.getType() == IDLTokenTypes.LITERAL_string) {
            corbaString.setBasetype(CorbaConstants.NT_CORBA_STRING);
        } else if (typeDeclaratorNode.getType() == IDLTokenTypes.LITERAL_wstring) {
            corbaString.setBasetype(CorbaConstants.NT_CORBA_WSTRING);
        } else {
            // should never get here
            throw new RuntimeException("[TypedefVisitor] Attempted to visit an invalid node: "
                                       + typeDeclaratorNode.toString());
        }
        Scope newScope = new Scope(typedefScope.getParent(), identifierNode);
        corbaString.setQName(new QName(typeMap.getTargetNamespace(), newScope.toString()));
        corbaString.setType(Constants.XSD_STRING);
        corbaString.setRepositoryID(newScope.toIDLRepositoryID());

        typeMap.getStructOrExceptionOrUnion().add(corbaString);

    }


}
