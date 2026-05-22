public class Snippet__7045316 {

    public static MetaImage getMetaImage(Vector<Object> src)
      {
        if (src == null) return globalImageCache.get(null);
        if (globalImageCache.containsKey(src))
        {
          MetaImage rv = globalImageCache.get(src);
          return rv;
        }
        {
          // use tmpSrc to avoid needing warning suppression of
          // unchecked conversion at function scope
          @SuppressWarnings("unchecked")
          Vector<Object>  tmpSrc = (Vector<Object>) src.clone();
          src = tmpSrc;
        }
        if (DEBUG_MI) System.out.println("first getMetaImage src=" + src);
        MetaImage rv = new MetaImage(src);
        MetaImage srcImage = (MetaImage) src.get(0);
        rv.initDataStructures(1);
        rv.width[0] = srcImage.getWidth(0);
        rv.height[0] = srcImage.getHeight(0);
        synchronized (globalImageCache)
        {
          if (globalImageCache.containsKey(src)) return globalImageCache.get(src);
          globalImageCache.put(src, rv);
        }
        if (DEBUG_MI) System.out.println("DONE first getMetaImage src=" + src);
        return rv;
      }

}
