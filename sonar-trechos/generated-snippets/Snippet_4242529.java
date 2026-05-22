public class Snippet__4242529 {

    @Override
        public void interpretPixel(final ImageBuilder imageBuilder, final int[] samples, final int x,
                final int y) throws ImageReadException, IOException {

            final int sc = samples[0];
            final int sm = samples[1];
            final int sy = samples[2];
            final int sk = samples[3];

            final int rgb = ColorConversions.convertCMYKtoRGB(sc, sm, sy, sk);
            imageBuilder.setRGB(x, y, rgb);
        }

}
