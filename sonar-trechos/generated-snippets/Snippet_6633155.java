public class Snippet__6633155 {

    @Override
      public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String,JsonAttribute> attr: this.entrySet()) {
          if (sb.length()>0) {
            sb.append(",");
          }
          sb.append("\"").append(attr.getKey()).append("\":").append(attr.getValue());
        }
        return sb.toString();
      }

}
