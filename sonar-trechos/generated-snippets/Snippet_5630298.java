public class SwitchEmitter extends JSSubEmitter implements
        ISubEmitter<ISwitchNode>
{
    public SwitchEmitter(IJSEmitter emitter)
    {
        super(emitter);
    }

    @Override
    public void emit(ISwitchNode node)
    {
        startMapping(node);
        writeToken(ASEmitterTokens.SWITCH);
        write(ASEmitterTokens.PAREN_OPEN);
        endMapping(node);
        IASNode expressionNode = node.getChild(0);
        getWalker().walk(expressionNode);
        startMapping(node, expressionNode);
        writeToken(ASEmitterTokens.PAREN_CLOSE);
        endMapping(node);
        IASNode statementContentsNode = node.getStatementContentsNode();
        startMapping(statementContentsNode);
        write(ASEmitterTokens.BLOCK_OPEN);
        endMapping(statementContentsNode);
        indentPush();
        writeNewline();

        IConditionalNode[] cnodes = ASNodeUtils.getCaseNodes(node);
        ITerminalNode dnode = ASNodeUtils.getDefaultNode(node);
        
        for (int i = 0; i < cnodes.length; i++)
        {
            IConditionalNode casen = cnodes[i];
            IContainerNode cnode = (IContainerNode) casen.getChild(1);
            startMapping(casen);
            writeToken(ASEmitterTokens.CASE);
            endMapping(casen);
            IExpressionNode conditionalExpressionNode = casen.getConditionalExpressionNode();
            getWalker().walk(conditionalExpressionNode);
            startMapping(casen, conditionalExpressionNode);
            write(ASEmitterTokens.COLON);
            if (!EmitterUtils.isImplicit(cnode))
                write(ASEmitterTokens.SPACE);
            endMapping(casen);
            getWalker().walk(casen.getStatementContentsNode());
            if (i == cnodes.length - 1 && dnode == null)
            {
                indentPop();
                writeNewline();
            }
            else
                writeNewline();
        }
        if (dnode != null)
        {
            IContainerNode cnode = (IContainerNode) dnode.getChild(0);
            startMapping(dnode);
            write(ASEmitterTokens.DEFAULT);
            write(ASEmitterTokens.COLON);
            if (!EmitterUtils.isImplicit(cnode))
                write(ASEmitterTokens.SPACE);
            endMapping(dnode);
            getWalker().walk(dnode);
            indentPop();
            writeNewline();
        }
        startMapping(node, node.getEndLine(), node.getEndColumn() - 1);
        write(ASEmitterTokens.BLOCK_CLOSE);
        endMapping(node);
    }
}
