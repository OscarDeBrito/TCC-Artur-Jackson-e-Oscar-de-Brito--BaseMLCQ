public class Snippet__4138124 {

    @Override public SqlNode visit(SqlIdentifier id) {
          // First check for builtin functions which don't have
          // parentheses, like "LOCALTIME".
          SqlCall call =
              SqlUtil.makeCall(
                  validator.getOperatorTable(),
                  id);
          if (call != null) {
            return call.accept(this);
          }
          final SqlIdentifier fqId = getScope().fullyQualify(id).identifier;
          SqlNode expandedExpr = expandDynamicStar(id, fqId);
          validator.setOriginal(expandedExpr, id);
          return expandedExpr;
        }

}
