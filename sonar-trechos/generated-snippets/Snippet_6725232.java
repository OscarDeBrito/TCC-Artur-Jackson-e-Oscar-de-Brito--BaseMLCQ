public class Snippet__6725232 {

    public APIRequestDelete requestAllFields (boolean value) {
          for (String field : FIELDS) {
            this.requestField(field, value);
          }
          return this;
        }

}
