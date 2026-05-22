public class Snippet__3828619 {

    @Override
       public boolean addAddressInfo(AddressInfo addressInfo) throws Exception {
          boolean added = reloadAddressInfo(addressInfo);
          if (added && storageManager != null) {
             long txID = storageManager.generateID();
             try {
                storageManager.addAddressBinding(txID, addressInfo);
                storageManager.commitBindings(txID);
             } catch (Exception e) {
                try {
                   storageManager.rollbackBindings(txID);
                } catch (Exception ignored) {
                }
                throw e;
             }
          }
          return added;
       }

}
