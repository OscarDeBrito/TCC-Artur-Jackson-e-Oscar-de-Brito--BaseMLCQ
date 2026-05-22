public class Snippet__4578967 {

    @Override
        public FOPGVTFontFamily resolve(String familyName) {
            FOPGVTFontFamily fopGVTFontFamily = super.resolve(familyName);
            // TODO why don't DB fonts work with GOCA?!?
            if (fopGVTFontFamily != null && fopGVTFontFamily.deriveFont(1, new HashMap())
                    .getFont().getFontMetrics() instanceof DoubleByteFont) {
                notifyDBFontRejection(fopGVTFontFamily.getFamilyName());
                fopGVTFontFamily = null;
            }
            return fopGVTFontFamily;
        }

}
