public class Snippet__4561905 {

    public boolean isCompatible(RenderingContext targetContext, Image image) {
            return (image == null || image instanceof ImageRawJPEG)
                    && targetContext instanceof PDFRenderingContext;
        }

}
