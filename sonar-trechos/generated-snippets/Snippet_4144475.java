public class Snippet__4144475 {

    protected List<RexFieldCollation> visitFieldCollations(
          List<RexFieldCollation> collations, boolean[] update) {
        ImmutableList.Builder<RexFieldCollation> clonedOperands =
            ImmutableList.builder();
        for (RexFieldCollation collation : collations) {
          RexNode clonedOperand = collation.left.accept(this);
          if ((clonedOperand != collation.left) && (update != null)) {
            update[0] = true;
            collation =
                new RexFieldCollation(clonedOperand, collation.right);
          }
          clonedOperands.add(collation);
        }
        return clonedOperands.build();
      }

}
