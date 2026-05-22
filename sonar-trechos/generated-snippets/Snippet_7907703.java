public class Snippet__7907703 {

    @Override
      public int hashCode() {
        int hashCode = 1;

        hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(pythonTimeMs);

        hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(initTimeMs);

        hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(parseTimeMs);

        hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(processingTimeMs);

        hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(actionGraphTimeMs);

        hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(rulekeyTimeMs);

        hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(fetchTimeMs);

        hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(buildTimeMs);

        hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(installTimeMs);

        return hashCode;
      }

}
