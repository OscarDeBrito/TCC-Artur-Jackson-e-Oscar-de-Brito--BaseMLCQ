public class Snippet__3812099 {

    @Override
       public boolean equals(final Object other) {
          if (this == other) {
             return true;
          }
          if (!(other instanceof Xid)) {
             return false;
          }
          Xid xother = (Xid) other;
          if (xother.getFormatId() != formatId) {
             return false;
          }
          if (xother.getBranchQualifier().length != branchQualifier.length) {
             return false;
          }
          if (xother.getGlobalTransactionId().length != globalTransactionId.length) {
             return false;
          }
          for (int i = 0; i < branchQualifier.length; i++) {
             byte[] otherBQ = xother.getBranchQualifier();
             if (branchQualifier[i] != otherBQ[i]) {
                return false;
             }
          }
          for (int i = 0; i < globalTransactionId.length; i++) {
             byte[] otherGtx = xother.getGlobalTransactionId();
             if (globalTransactionId[i] != otherGtx[i]) {
                return false;
             }
          }
          return true;
       }

}
