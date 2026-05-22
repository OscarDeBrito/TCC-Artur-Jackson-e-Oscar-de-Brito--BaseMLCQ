public class Snippet__4005583 {

    public Component getTreeCellRendererComponent(JTree tree,
                                                              Object value,
                                                              boolean sel,
                                                              boolean expanded,
                                                              boolean leaf,
                                                              int row,
                                                              boolean hasFocus) {
                    super.getTreeCellRendererComponent(tree, value, sel, expanded,
                                                       leaf, row, hasFocus);
                    switch (getNodeType(value)) {
                    case Node.ELEMENT_NODE:
                        setIcon(elementIcon);
                        break;
                    case Node.COMMENT_NODE:
                        setIcon(commentIcon);
                        break;
                    case Node.PROCESSING_INSTRUCTION_NODE:
                        setIcon(piIcon);
                        break;
                    case Node.TEXT_NODE:
                    case Node.CDATA_SECTION_NODE:
                        setIcon(textIcon);
                        break;
                    }
                    return this;
                }

}
