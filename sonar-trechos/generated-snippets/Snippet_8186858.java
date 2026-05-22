public class Snippet__8186858 {

    public int tightMarshal1(OpenWireFormat wireFormat, Object o, BooleanStream bs) throws IOException {

            ControlCommand info = (ControlCommand)o;

            int rc = super.tightMarshal1(wireFormat, o, bs);
            rc += tightMarshalString1(info.getCommand(), bs);

            return rc + 0;
        }

}
