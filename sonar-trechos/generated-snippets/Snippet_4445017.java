public class Snippet__4445017 {

    @java.lang.Override
        public int hashCode() {
          if (memoizedHashCode != 0) {
            return memoizedHashCode;
          }
          int hash = 41;
          hash = (19 * hash) + getDescriptor().hashCode();
          if (hasSqlQuery()) {
            hash = (37 * hash) + SQL_QUERY_FIELD_NUMBER;
            hash = (53 * hash) + getSqlQuery().hashCode();
          }
          hash = (29 * hash) + unknownFields.hashCode();
          memoizedHashCode = hash;
          return hash;
        }

}
