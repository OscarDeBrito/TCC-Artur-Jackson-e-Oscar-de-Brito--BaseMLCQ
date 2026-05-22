public class Snippet__6937904 {

    public java.lang.String getLanguage() {
            java.lang.Object ref = language_;
            if (ref instanceof java.lang.String) {
              return (java.lang.String) ref;
            } else {
              com.google.protobuf.ByteString bs = 
                  (com.google.protobuf.ByteString) ref;
              java.lang.String s = bs.toStringUtf8();
              if (bs.isValidUtf8()) {
                language_ = s;
              }
              return s;
            }
          }

}
