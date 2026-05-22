public final class CharCodingSupport {

    private CharCodingSupport() {
    }

    public static CharsetDecoder createDecoder(final CharCodingConfig cconfig) {
        if (cconfig == null) {
            return null;
        }
        final Charset charset = cconfig.getCharset();
        final CodingErrorAction malformed = cconfig.getMalformedInputAction();
        final CodingErrorAction unmappable = cconfig.getUnmappableInputAction();
        if (charset != null) {
            return charset.newDecoder()
                    .onMalformedInput(malformed != null ? malformed : CodingErrorAction.REPORT)
                    .onUnmappableCharacter(unmappable != null ? unmappable: CodingErrorAction.REPORT);
        }
        return null;
    }

    public static CharsetEncoder createEncoder(final CharCodingConfig cconfig) {
        if (cconfig == null) {
            return null;
        }
        final Charset charset = cconfig.getCharset();
        if (charset != null) {
            final CodingErrorAction malformed = cconfig.getMalformedInputAction();
            final CodingErrorAction unmappable = cconfig.getUnmappableInputAction();
            return charset.newEncoder()
                    .onMalformedInput(malformed != null ? malformed : CodingErrorAction.REPORT)
                    .onUnmappableCharacter(unmappable != null ? unmappable: CodingErrorAction.REPORT);
        }
        return null;
    }

}
