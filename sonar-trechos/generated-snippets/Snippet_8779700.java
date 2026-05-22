public class Snippet__8779700 {

    @Override
        public void setKeyComparator(Class<? extends WritableComparator> cmpcl) {
          super.setKeyComparator(cmpcl);
          for (Node n : kids) {
            n.setKeyComparator(cmpcl);
          }
        }

}
