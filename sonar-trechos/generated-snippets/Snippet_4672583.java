public class Snippet__4672583 {

    private int getMatchingLevelForNodes(Node n1, Node n2) {
        int count = 0;
        do {
          if (n1.equals(n2)) {
            return count;
          }
          ++count;
          n1 = n1.getParent();
          n2 = n2.getParent();
        } while (n1 != null);
        return this.maxLevel;
      }

}
