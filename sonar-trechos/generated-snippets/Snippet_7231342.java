public class Snippet__7231342 {

    @Implementation(minSdk = M)
      protected Network getActiveNetwork() {
        if (defaultNetworkActive) {
          return netIdToNetwork.get(getActiveNetworkInfo().getType());
        }
        return null;
      }

}
