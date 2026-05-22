public class Snippet__4055848 {

    public static String addLengthPrefixedCoder(
          String coderId, RunnerApi.Components.Builder components, boolean replaceWithByteArrayCoder) {
        String lengthPrefixedByteArrayCoderId = addLengthPrefixByteArrayCoder(components);
        String urn = components.getCodersOrThrow(coderId).getSpec().getSpec().getUrn();

        // We handle three cases:
        //  1) the requested coder is already a length prefix coder. In this case we just honor the
        //     request to replace the coder with a byte array coder.
        //  2) the requested coder is a known coder but not a length prefix coder. In this case we
        //     rebuild the coder by recursively length prefixing any unknown component coders.
        //  3) the requested coder is an unknown coder. In this case we either wrap the requested coder
        //     with a length prefix coder or replace it with a length prefix byte array coder.
        if (ModelCoders.LENGTH_PREFIX_CODER_URN.equals(urn)) {
          return replaceWithByteArrayCoder ? lengthPrefixedByteArrayCoderId : coderId;
        } else if (ModelCoders.urns().contains(urn)) {
          return addForModelCoder(coderId, components, replaceWithByteArrayCoder);
        } else {
          return replaceWithByteArrayCoder
              ? lengthPrefixedByteArrayCoderId
              : addWrappedWithLengthPrefixCoder(coderId, components);
        }
      }

}
