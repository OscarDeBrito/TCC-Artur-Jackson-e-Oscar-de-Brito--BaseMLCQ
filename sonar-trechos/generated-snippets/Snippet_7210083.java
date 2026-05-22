public class Snippet__7210083 {

    abstract static class RequestParamsBuilder<T> {
        T body;

        public RequestParamsBuilder(T body) {
          this.body = body;
        }

        abstract RequestParams buildRequestParams();

        void setBody(T body) {
          this.body = body;
        }
      }

}
