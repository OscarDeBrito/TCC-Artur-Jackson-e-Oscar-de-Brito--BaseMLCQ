public class Snippet__4480085 {

    @java.lang.Override
        public boolean equals(final java.lang.Object obj) {
          if (obj == this) {
           return true;
          }
          if (!(obj instanceof org.apache.drill.exec.proto.UserBitShared.NamePart)) {
            return super.equals(obj);
          }
          org.apache.drill.exec.proto.UserBitShared.NamePart other = (org.apache.drill.exec.proto.UserBitShared.NamePart) obj;

          boolean result = true;
          result = result && (hasType() == other.hasType());
          if (hasType()) {
            result = result && type_ == other.type_;
          }
          result = result && (hasName() == other.hasName());
          if (hasName()) {
            result = result && getName()
                .equals(other.getName());
          }
          result = result && (hasChild() == other.hasChild());
          if (hasChild()) {
            result = result && getChild()
                .equals(other.getChild());
          }
          result = result && unknownFields.equals(other.unknownFields);
          return result;
        }

}
