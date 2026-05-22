public class Snippet__4502504 {

    private final boolean sameDevice(String udn, String urn)
        {
            String _udn = this.udn;
            String _urn = this.urn;
            if (_udn == null || _urn == null)
                return false; // not subscribed
            return _udn.equals(udn) && _urn.equals(urn);
        }

}
