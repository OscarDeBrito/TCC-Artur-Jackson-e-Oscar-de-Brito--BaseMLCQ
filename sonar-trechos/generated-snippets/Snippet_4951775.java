public class PutOptions extends BaseHttpRequestOptions {
   public static final PutOptions NONE = new PutOptions();

   /**
    * Add public access to all users
    * 
    */
   public PutOptions publicRead() {
      this.replaceHeader("x-emc-useracl", "root=FULL_CONTROL");
      this.replaceHeader("x-emc-groupacl", "other=READ");
      return this;
   }

   public PutOptions publicNone() {
      this.replaceHeader("x-emc-useracl", "root=FULL_CONTROL");
      this.replaceHeader("x-emc-groupacl", "other=NONE");
      return this;
   }

   /**
    * By default Atmos does not allow overwriting objects.
    *
    * Note: older versions of Atmos do not support this header.
    */
   public PutOptions overwrite() {
      this.replaceHeader("x-emc-force-overwrite", "true");
      return this;
   }

   public static class Builder {

      /**
       * @see PutOptions#publicRead
       */
      public static PutOptions publicRead() {
         PutOptions options = new PutOptions();
         return options.publicRead();
      }

      public static PutOptions publicNone() {
         PutOptions options = new PutOptions();
         return options.publicNone();
      }

      public static PutOptions overwrite() {
         PutOptions options = new PutOptions();
         return options.overwrite();
      }
   }
}
