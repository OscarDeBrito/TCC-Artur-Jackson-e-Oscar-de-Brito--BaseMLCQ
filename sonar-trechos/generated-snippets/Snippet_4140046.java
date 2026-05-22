public class Snippet__4140046 {

    protected RexNode makeMultiply(
            RexNode a,
            RexNode b) {
          return builder.makeCall(
              SqlStdOperatorTable.MULTIPLY,
              a,
              b);
        }

}
