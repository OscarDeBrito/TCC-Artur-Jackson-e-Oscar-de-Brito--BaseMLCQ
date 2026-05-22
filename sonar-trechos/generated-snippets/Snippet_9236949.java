public class Snippet__9236949 {

    protected List<XRGlyphCacheEntry>[] seperateGlyphTypes(List<XRGlyphCacheEntry> glyphList, boolean containsLCDGlyphs) {
            ArrayList<XRGlyphCacheEntry> lcdGlyphs = null;
            ArrayList<XRGlyphCacheEntry> grayGlyphs = null;

            for (XRGlyphCacheEntry cacheEntry : glyphList) {
                if (cacheEntry.isGrayscale(containsLCDGlyphs)) {
                    if (grayGlyphs == null) {
                        grayGlyphs = new ArrayList<>(glyphList.size());
                    }
                    cacheEntry.setGlyphSet(grayGlyphSet);
                    grayGlyphs.add(cacheEntry);
                } else {
                    if (lcdGlyphs == null) {
                        lcdGlyphs = new ArrayList<>(glyphList.size());
                    }
                    cacheEntry.setGlyphSet(lcdGlyphSet);
                    lcdGlyphs.add(cacheEntry);
                }
            }
            // Arrays and generics don't play well together
            @SuppressWarnings({"unchecked", "rawtypes"})
            List<XRGlyphCacheEntry>[] tmp =
                (List<XRGlyphCacheEntry>[]) (new List[] { grayGlyphs, lcdGlyphs });
            return tmp;
        }

}
