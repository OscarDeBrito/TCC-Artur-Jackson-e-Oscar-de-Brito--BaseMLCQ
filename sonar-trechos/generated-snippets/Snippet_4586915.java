public class Snippet__4586915 {

    private static final class Corner {

            private static final Corner SQUARE = new Corner(0, 0, null, 0, 0, 0, 0);

            /** The radius of the elliptic corner in the x direction */
            private final int radiusX;

            /** The radius of the elliptic corner in the y direction */
            private final int radiusY;

            /** The start and end angles of the corner ellipse */
            private final CornerAngles angles;

            /** The offset in the x direction of the center of the ellipse relative to the starting point */
            private final int centerX;

            /** The offset in the y direction of the center of the ellipse relative to the starting point */
            private final int centerY;

            /** The value in the x direction that the corner extends relative to the starting point */
            private final int incrementX;

            /** The value in the y direction that the corner extends relative to the starting point */
            private final int incrementY;

            private Corner(int radiusX, int radiusY, CornerAngles angles, int ellipseOffsetX,
                    int ellipseOffsetY, int incrementX, int incrementY) {
                this.radiusX = radiusX;
                this.radiusY = radiusY;
                this.angles = angles;
                this.centerX = ellipseOffsetX;
                this.centerY = ellipseOffsetY;
                this.incrementX = incrementX;
                this.incrementY = incrementY;
            }

            private static int extentFromRadiusStart(BorderSegment border, double correctionFactor) {
                return extentFromRadius(border.getRadiusStart(), border, correctionFactor);
            }

            private static int extentFromRadiusEnd(BorderSegment border, double correctionFactor) {
                return extentFromRadius(border.getRadiusEnd(), border, correctionFactor);
            }

            private static int extentFromRadius(int radius, BorderSegment border, double correctionFactor) {
                return Math.max((int) (radius * correctionFactor) - border.getWidth(), 0);
            }

            public static Corner createBeforeEndCorner(BorderSegment before, BorderSegment end,
                    double correctionFactor) {
                int width = end.getRadiusStart();
                int height = before.getRadiusEnd();
                if (width == 0 || height == 0) {
                    return SQUARE;
                }
                int x = extentFromRadiusStart(end, correctionFactor);
                int y = extentFromRadiusEnd(before, correctionFactor);
                return new Corner(x, y, CornerAngles.BEFORE_END, 0, y, x, y);
            }

            public static Corner createEndAfterCorner(BorderSegment end, BorderSegment after,
                    double correctionFactor) {
                int width = end.getRadiusEnd();
                int height = after.getRadiusStart();
                if (width == 0 || height == 0) {
                    return SQUARE;
                }
                int x = extentFromRadiusEnd(end, correctionFactor);
                int y = extentFromRadiusStart(after, correctionFactor);
                return new Corner(x, y, CornerAngles.END_AFTER, -x, 0, -x, y);
            }

            public static Corner createAfterStartCorner(BorderSegment after, BorderSegment start,
                    double correctionFactor) {
                int width = start.getRadiusStart();
                int height = after.getRadiusEnd();
                if (width == 0 || height == 0) {
                    return SQUARE;
                }
                int x = extentFromRadiusStart(start, correctionFactor);
                int y = extentFromRadiusEnd(after, correctionFactor);
                return new Corner(x, y, CornerAngles.AFTER_START, 0, -y, -x, -y);
            }

            public static Corner createStartBeforeCorner(BorderSegment start, BorderSegment before,
                    double correctionFactor) {
                int width = start.getRadiusEnd();
                int height = before.getRadiusStart();
                if (width == 0 || height == 0) {
                    return SQUARE;
                }
                int x = extentFromRadiusEnd(start, correctionFactor);
                int y = extentFromRadiusStart(before, correctionFactor);
                return new Corner(x, y, CornerAngles.START_BEFORE, x, 0, x, -y);
            }
        }

}
