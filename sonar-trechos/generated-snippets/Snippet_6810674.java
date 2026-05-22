public class SplitRulesetNodes extends SkippingTreeVisitor
    implements CssCompilerPass {

  private final MutatingVisitController visitController;

  public SplitRulesetNodes(MutatingVisitController visitController) {
    this(visitController, false);
  }

  public SplitRulesetNodes(MutatingVisitController visitController,
        boolean skipping) {
    super(skipping);
    this.visitController = visitController;
  }

  @Override
  public boolean enterRuleset(CssRulesetNode node) {
    boolean canModifyRuleset = canModifyRuleset(node);
    if (canModifyRuleset) {
      List<CssNode> replacementNodes = Lists.newArrayList();

      CssSelectorListNode selectors = node.getSelectors();
      CssDeclarationBlockNode declarations = node.getDeclarations();

      for (CssSelectorNode sel : selectors.childIterable()) {
        for (CssNode child : declarations.childIterable()) {
          CssRulesetNode ruleset = new CssRulesetNode();
          ruleset.setSourceCodeLocation(node.getSourceCodeLocation());
          ruleset.addDeclaration(child.deepCopy());
          ruleset.addSelector(sel.deepCopy());

          replacementNodes.add(ruleset);
        }
      }

      visitController.replaceCurrentBlockChildWith(
          replacementNodes,
          false);
    }
    return canModifyRuleset;
  }

  @Override
  public void runPass() {
    visitController.startVisit(this);
  }

}
