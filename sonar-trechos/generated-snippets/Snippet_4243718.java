protected static class JFIFPieceSegment extends JFIFPiece {
        public final int marker;
        private final byte[] markerBytes;
        private final byte[] segmentLengthBytes;
        private final byte[] segmentData;

        public JFIFPieceSegment(final int marker, final byte[] segmentData) {
            this(marker,
                    ByteConversions.toBytes((short) marker, JPEG_BYTE_ORDER),
                    ByteConversions.toBytes((short) (segmentData.length + 2), JPEG_BYTE_ORDER),
                    segmentData);
        }

        JFIFPieceSegment(final int marker, final byte[] markerBytes,
                final byte[] segmentLengthBytes, final byte[] segmentData) {
            this.marker = marker;
            this.markerBytes = markerBytes;
            this.segmentLengthBytes = segmentLengthBytes;
            this.segmentData = segmentData; // TODO clone?
        }

        @Override
        public String toString() {
            return "[" + this.getClass().getName() + " (0x"
                    + Integer.toHexString(marker) + ")]";
        }

        @Override
        protected void write(final OutputStream os) throws IOException {
            os.write(markerBytes);
            os.write(segmentLengthBytes);
            os.write(segmentData);
        }

        public boolean isApp1Segment() {
            return marker == JpegConstants.JPEG_APP1_MARKER;
        }

        public boolean isAppSegment() {
            return marker >= JpegConstants.JPEG_APP0_MARKER && marker <= JpegConstants.JPEG_APP15_MARKER;
        }

        public boolean isExifSegment() {
            if (marker != JpegConstants.JPEG_APP1_MARKER) {
                return false;
            }
            if (!startsWith(segmentData, JpegConstants.EXIF_IDENTIFIER_CODE)) {
                return false;
            }
            return true;
        }

        public boolean isPhotoshopApp13Segment() {
            if (marker != JpegConstants.JPEG_APP13_MARKER) {
                return false;
            }
            if (!new IptcParser().isPhotoshopJpegSegment(segmentData)) {
                return false;
            }
            return true;
        }

        public boolean isXmpSegment() {
            if (marker != JpegConstants.JPEG_APP1_MARKER) {
                return false;
            }
            if (!startsWith(segmentData, JpegConstants.XMP_IDENTIFIER)) {
                return false;
            }
            return true;
        }

        public byte[] getSegmentData() {
            return segmentData; // TODO clone?
        }

    }
