public class Snippet__8916223 {

    @Override
        public int hashCode() {
          List<Object> list = new ArrayList<Object>();

          boolean present_o1 = true && (isSetO1());
          list.add(present_o1);
          if (present_o1)
            list.add(o1);

          boolean present_o2 = true && (isSetO2());
          list.add(present_o2);
          if (present_o2)
            list.add(o2);

          return list.hashCode();
        }

}
