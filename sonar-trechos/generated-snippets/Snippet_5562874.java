public static class EmfPolyPolygon16 extends EmfPolyPolygon {
        @Override
        public HemfRecordType getEmfRecordType() {
            return HemfRecordType.polyPolygon16;
        }

        @Override
        protected long readPoint(LittleEndianInputStream leis, Point2D point) {
            return readPointS(leis, point);
        }
    }
