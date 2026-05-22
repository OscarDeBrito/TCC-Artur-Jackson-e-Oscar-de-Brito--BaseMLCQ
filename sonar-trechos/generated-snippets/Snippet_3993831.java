public class Snippet__3993831 {

    public WritableRaster copyData(WritableRaster wr) {
            // Get my source.
            CachableRed srcRed = (CachableRed)getSources().get(0);

            SampleModel sm = srcRed.getSampleModel();
            if (sm.getNumBands() == 1)
                // Already one band of data so we just use it...
                return srcRed.copyData(wr);

            // Two band case so we need to multiply them...
            // Note: Our source will always have either one or two bands
            // since we insert an Any2Lum transform before ourself in the
            // rendering chain.

            Raster srcRas = srcRed.getData(wr.getBounds());
            PixelInterleavedSampleModel srcSM;
            srcSM = (PixelInterleavedSampleModel)srcRas.getSampleModel();

            DataBufferByte srcDB = (DataBufferByte)srcRas.getDataBuffer();
            byte []        src   = srcDB.getData();
        
            PixelInterleavedSampleModel dstSM;
            dstSM = (PixelInterleavedSampleModel)wr.getSampleModel();

            DataBufferByte dstDB = (DataBufferByte)wr.getDataBuffer();
            byte []        dst   = dstDB.getData();

            int srcX0 = srcRas.getMinX()-srcRas.getSampleModelTranslateX();
            int srcY0 = srcRas.getMinY()-srcRas.getSampleModelTranslateY();

            int dstX0 = wr.getMinX()-wr.getSampleModelTranslateX();
            int dstX1 = dstX0+wr.getWidth()-1;
            int dstY0 = wr.getMinY()-wr.getSampleModelTranslateY();

            int    srcStep = srcSM.getPixelStride();
            int [] offsets = srcSM.getBandOffsets();
            int    srcLOff = offsets[0];
            int    srcAOff = offsets[1];

            if (srcRed.getColorModel().isAlphaPremultiplied()) {
                // Lum is already multiplied by alpha so we just copy lum channel.
                for (int y=0; y<srcRas.getHeight(); y++) {
                    int srcI  = srcDB.getOffset() + srcSM.getOffset(srcX0,  srcY0);
                    int dstI  = dstDB.getOffset() + dstSM.getOffset(dstX0,  dstY0);
                    int dstE  = dstDB.getOffset() + dstSM.getOffset(dstX1+1,dstY0);

                    srcI += srcLOff; // Go to Lum Channel (already mult by alpha).

                    while (dstI < dstE) {
                        dst[dstI++] = src[srcI];
                            srcI += srcStep; // Go to next pixel
                    }
                    srcY0++;
                    dstY0++;
                }
            }
            else {
                // This allows me to pre-adjust my index by srcLOff
                // Then only add the offset for srcAOff
                srcAOff = srcAOff-srcLOff;

                for (int y=0; y<srcRas.getHeight(); y++) {
                    int srcI  = srcDB.getOffset() + srcSM.getOffset(srcX0,  srcY0);
                    int dstI  = dstDB.getOffset() + dstSM.getOffset(dstX0,  dstY0);
                    int dstE  = dstDB.getOffset() + dstSM.getOffset(dstX1+1,dstY0);

                    srcI += srcLOff;

                    while (dstI < dstE) {
                        int sl = (src[srcI])&0xFF; // LOff already included
                        int sa = (src[srcI+srcAOff])&0xFF;
                        // the + 0x80 forces proper rounding.
                        dst[dstI++] = (byte)((sl*sa+0x80)>>8);

                        srcI+= srcStep; //  next pixel
                    }
                    srcY0++;
                    dstY0++;
                }
            }

            return wr;
        }

}
