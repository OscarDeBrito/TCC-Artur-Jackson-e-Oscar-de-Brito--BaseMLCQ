public class Snippet__5748379 {

    public Object visit(Context ctx, Stack<Expr> stack, Expr expr) throws TajoException {
        if (expr instanceof UnaryOperator) {
          preHook(ctx, stack, expr);
          visitUnaryOperator(ctx, stack, (UnaryOperator) expr);
          postHook(ctx, stack, expr, null);
        } else if (expr instanceof BinaryOperator) {
          preHook(ctx, stack, expr);
          visitBinaryOperator(ctx, stack, (BinaryOperator) expr);
          postHook(ctx, stack, expr, null);
        } else {
          super.visit(ctx, stack, expr);
        }

        if (expr != null && ctx.targetType == expr.getType()) {
          ctx.set.add(expr);
        }

        return null;
      }

}
