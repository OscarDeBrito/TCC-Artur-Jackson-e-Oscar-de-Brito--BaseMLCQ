public class Snippet__6198340 {

    @Override
        public boolean equals(Object o) {
          if (this == o) {
            return true;
          }
          if (!(o instanceof Key)) {
            return false;
          }
          Key key = (Key) o;
          return annotationType.equals(key.annotationType) && type.equals(key.type);
        }

}
