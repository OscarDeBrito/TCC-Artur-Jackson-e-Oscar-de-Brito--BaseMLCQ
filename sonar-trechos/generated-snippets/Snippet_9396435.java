public class Snippet__9396435 {

    public void XRCompositeRectangles(XRSurfaceData dst, GrowableRectArray rects) {
            int srcPict = getCurrentSource().picture;

            for(int i=0; i < rects.getSize(); i++) {
                int x = rects.getX(i);
                int y = rects.getY(i);
                int width = rects.getWidth(i);
                int height = rects.getHeight(i);

                con.renderComposite(compRule, srcPict, XRUtils.None, dst.picture, x, y, 0, 0, x, y, width, height);
            }
        }

}
