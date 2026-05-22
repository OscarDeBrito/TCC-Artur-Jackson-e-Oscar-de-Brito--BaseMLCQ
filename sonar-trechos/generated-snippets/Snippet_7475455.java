public class Snippet__7475455 {

    @Override
        public void emitNullCheck(Value address, LIRFrameState state) {
            if (address.getValueKind().getPlatformKind() == AArch64Kind.DWORD) {
                CompressEncoding encoding = config.getOopEncoding();
                Value uncompressed = emitUncompress(address, encoding, false);
                append(new AArch64Move.NullCheckOp(asAddressValue(uncompressed), state));
            } else {
                super.emitNullCheck(address, state);
            }
        }

}
