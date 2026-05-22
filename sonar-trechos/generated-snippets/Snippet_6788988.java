protected class ColorPaletteView extends View {
        private static final int PALETTE_COLUMNS = 7;
        private static final int PALETTE_ROWS = 10;

        private static final int PALETTE_FIELD_WIDTH = 50;
        private static final int PALETTE_FIELD_HEIGHT = 50;
        private static final float GRID_STROKE_WIDTH = 5;

        private final BasicFieldColorView mParent;
        private final Paint mAreaPaint = new Paint();
        private final Paint mGridPaint = new Paint();

        // From https://github.com/google/closure-library/blob/master/closure/goog/ui/colorpicker.js
        // TODO(#70): move this table into resources.
        private final int[] mColorArray = new int[]{
                // grays
                0xffffffff, 0xffcccccc, 0xffc0c0c0, 0xff999999, 0xff666666, 0xff333333, 0xff000000,
                // reds
                0xffffcccc, 0xffff6666, 0xffff0000, 0xffcc0000, 0xff990000, 0xff660000, 0xff330000,
                // oranges
                0xffffcc99, 0xffff9966, 0xffff9900, 0xffff6600, 0xffcc6600, 0xff993300, 0xff663300,
                // yellows
                0xffffff99, 0xffffff66, 0xffffcc66, 0xffffcc33, 0xffcc9933, 0xff996633, 0xff663333,
                // olives
                0xffffffcc, 0xffffff33, 0xffffff00, 0xffffcc00, 0xff999900, 0xff666600, 0xff333300,
                // greens
                0xff99ff99, 0xff66ff99, 0xff33ff33, 0xff33cc00, 0xff009900, 0xff006600, 0xff003300,
                // turquoises
                0xff99ffff, 0xff33ffff, 0xff66cccc, 0xff00cccc, 0xff339999, 0xff336666, 0xff003333,
                // blues
                0xffccffff, 0xff66ffff, 0xff33ccff, 0xff3366ff, 0xff3333ff, 0xff000099, 0xff000066,
                // purples
                0xffccccff, 0xff9999ff, 0xff6666cc, 0xff6633ff, 0xff6600cc, 0xff333399, 0xff330099,
                // violets
                0xffffccff, 0xffff99ff, 0xffcc66cc, 0xffcc33cc, 0xff993399, 0xff663366, 0xff330033
        };

        ColorPaletteView(BasicFieldColorView parent) {
            super(parent.getContext());
            mParent = parent;

            mGridPaint.setColor(Color.DKGRAY);
            mGridPaint.setStrokeWidth(GRID_STROKE_WIDTH);
            mGridPaint.setStyle(Paint.Style.STROKE);
        }

        @Override
        public int getMinimumWidth() {
            return PALETTE_FIELD_WIDTH * PALETTE_COLUMNS;
        }

        @Override
        public int getMinimumHeight() {
            return PALETTE_FIELD_HEIGHT * PALETTE_ROWS;
        }

        @Override
        public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            setMeasuredDimension(getMinimumWidth(), getMinimumHeight());
        }

        @Override
        public void onDraw(Canvas canvas) {
            drawPalette(canvas);
            drawGrid(canvas);
        }

        @Override
        public boolean onTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                int i = Math.min(PALETTE_COLUMNS - 1,
                        (int) motionEvent.getX() / PALETTE_FIELD_WIDTH);
                int j = Math.min(PALETTE_ROWS - 1,
                        (int) motionEvent.getY() / PALETTE_FIELD_HEIGHT);

                int index = i + j * PALETTE_COLUMNS;
                mParent.setBackgroundColor(mColorArray[index]);
                mParent.mColorField.setColor(mColorArray[index]);
                mParent.mColorPopupWindow.dismiss();
                return true;
            }

            return false;
        }

        private void drawPalette(Canvas canvas) {
            int paletteIndex = 0;
            for (int j = 0; j < PALETTE_ROWS; ++j) {
                int y = j * PALETTE_FIELD_HEIGHT;
                for (int i = 0; i < PALETTE_COLUMNS; ++i, ++paletteIndex) {
                    int x = i * PALETTE_FIELD_WIDTH;

                    mAreaPaint.setColor(mColorArray[paletteIndex]);
                    canvas.drawRect(
                            x, y, x + PALETTE_FIELD_WIDTH, y + PALETTE_FIELD_HEIGHT, mAreaPaint);
                }
            }
        }

        private void drawGrid(Canvas canvas) {
            int width = getMeasuredWidth();
            int height = getMeasuredHeight();

            canvas.drawRect(0, 0, width - 1, height - 1, mGridPaint);
            for (int j = 0; j < PALETTE_ROWS; ++j) {
                int y = j * PALETTE_FIELD_HEIGHT;
                canvas.drawLine(0, y, width - 1, y, mGridPaint);
            }
            for (int i = 0; i < PALETTE_COLUMNS; ++i) {
                int x = i * PALETTE_FIELD_WIDTH;
                canvas.drawLine(x, 0, x, height - 1, mGridPaint);
            }
        }
    }
