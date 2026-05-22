public class Snippet__8904487 {

    @Override
        public Object process(Node nd, Stack<Node> stack, NodeProcessorCtx procCtx,
            Object... nodeOutputs) throws SemanticException {

          MapJoinWalkerCtx ctx = (MapJoinWalkerCtx) procCtx;
          AbstractMapJoinOperator<? extends MapJoinDesc> mapJoin = ctx.getCurrMapJoinOp();
          List<AbstractMapJoinOperator<? extends MapJoinDesc>> listRejectedMapJoins = ctx
              .getListRejectedMapJoins();

          // the mapjoin has already been handled
          if ((listRejectedMapJoins != null) && (listRejectedMapJoins.contains(mapJoin))) {
            return null;
          }
          addNoReducerMapJoinToCtx(ctx, mapJoin);
          return null;
        }

}
