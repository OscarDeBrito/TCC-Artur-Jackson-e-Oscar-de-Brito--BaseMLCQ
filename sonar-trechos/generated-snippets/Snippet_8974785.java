public class RegexpQueryNodeProcessor extends QueryNodeProcessorImpl {

  @Override
  protected QueryNode preProcessNode(QueryNode node) throws QueryNodeException {
    return node;
  }

  @Override
  protected QueryNode postProcessNode(QueryNode node) throws QueryNodeException {
    if (node instanceof RegexpQueryNode) {
      RegexpQueryNode regexpNode = (RegexpQueryNode) node;
      Analyzer analyzer = getQueryConfigHandler().get(ConfigurationKeys.ANALYZER);
      if (analyzer != null) {
        String text = regexpNode.getText().toString();
        // because we call utf8ToString, this will only work with the default TermToBytesRefAttribute
        text = analyzer.normalize(regexpNode.getFieldAsString(), text).utf8ToString();
        regexpNode.setText(text);
      }
    }
    return node;
  }

  @Override
  protected List<QueryNode> setChildrenOrder(List<QueryNode> children) throws QueryNodeException {
    return children;
  }

}
