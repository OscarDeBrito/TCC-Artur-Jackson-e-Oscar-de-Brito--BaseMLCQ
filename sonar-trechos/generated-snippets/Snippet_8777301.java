public class Snippet__8777301 {

    @Override //Object
        public int hashCode() {
          int result = 1;
          result = PRIME * result
              + ((serverAddress == null) ? 0 : serverAddress.hashCode());
          result = PRIME * result + ((protocol == null) ? 0 : protocol.hashCode());
          result = PRIME * result + ((rpcKind == null) ? 0 : rpcKind.hashCode());
          return result;
        }

}
