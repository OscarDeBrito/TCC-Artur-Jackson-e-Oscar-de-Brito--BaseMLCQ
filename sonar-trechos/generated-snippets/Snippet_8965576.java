public class Snippet__8965576 {

    @Override
      public void walk(Node nd) throws SemanticException {
        opStack.push(nd);
        dispatch(nd, opStack);

        // The rule has been applied, we bail out
        if (retMap.get(nd) != null) {
          opStack.pop();
          return;
        }

        // move all the children to the front of queue
        if (nd.getChildren() != null) {
          for (Node n : nd.getChildren()) {
            walk(n);
          }
        }

        opStack.pop();
      }

}
