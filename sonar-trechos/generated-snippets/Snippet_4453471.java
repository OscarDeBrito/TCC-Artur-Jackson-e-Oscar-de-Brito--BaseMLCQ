public class Snippet__4453471 {

    public Wrapper get(Fragment node) {
        Wrapper wrapper = fragmentMap.get(node);
        if (wrapper == null) {

          int majorFragmentId = 0;

          // If there is a sending exchange, we need to number other than zero.
          if (node.getSendingExchange() != null) {

            // assign the upper 16 bits as the major fragment id.
            majorFragmentId = node.getSendingExchange().getChild().getOperatorId() >> 16;

            // if they are not assigned, that means we mostly likely have an externally generated plan.  in this case, come up with a major fragmentid.
            if (majorFragmentId == 0) {
              majorFragmentId = majorFragmentIdIndex;
            }
          }
          wrapper = new Wrapper(node, majorFragmentId);
          fragmentMap.put(node, wrapper);
          majorFragmentIdIndex++;
        }
        return wrapper;
      }

}
