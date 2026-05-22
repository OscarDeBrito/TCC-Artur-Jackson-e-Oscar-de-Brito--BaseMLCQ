public class Snippet__5622695 {

    public void emitBindableImplementsConstructorCode(boolean popIndent) {
            writeNewline("// Compiler generated Binding support implementation:");
            String dispatcherClass = getEmitter().formatQualifiedName(DISPATCHER_CLASS_QNAME);
            write(ASEmitterTokens.THIS);
            write(ASEmitterTokens.MEMBER_ACCESS);
            write(BINDABLE_DISPATCHER_NAME);
            write(ASEmitterTokens.SPACE);
            write(ASEmitterTokens.EQUAL);
            write(ASEmitterTokens.SPACE);
            write(ASEmitterTokens.NEW);
            write(ASEmitterTokens.SPACE);
            write(dispatcherClass);
            write(ASEmitterTokens.PAREN_OPEN);
            write(ASEmitterTokens.THIS);
            write(ASEmitterTokens.PAREN_CLOSE);
            writeNewline(ASEmitterTokens.SEMICOLON);
            if (popIndent) writeNewline("",false);
            else writeNewline();

        }

}
