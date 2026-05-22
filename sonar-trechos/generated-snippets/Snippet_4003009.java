protected class ColorNumber implements SVGNumber {

        /**
         * The value of this number, when detached.
         */
        protected float value;

        /**
         * Creates a new ColorNumber.
         */
        public ColorNumber(float f) {
            value = f;
        }

        /**
         * Implements {@link SVGNumber#getValue()}.
         */
        public float getValue() {
            if (iccColors == null) {
                return value;
            }
            int idx = iccColors.indexOf(this);
            if (idx == -1) {
                return value;
            }
            Value value = valueProvider.getValue().item(1);
            return ((ICCColor)value).getColor(idx);
        }

        /**
         * Implements {@link SVGNumber#setValue(float)}.
         */
        public void setValue(float f) {
            value = f;
            if (iccColors == null) {
                return;
            }
            int idx = iccColors.indexOf(this);
            if (idx == -1) {
                return;
            }
            if (handler == null) {
                throw new DOMException
                    (DOMException.NO_MODIFICATION_ALLOWED_ERR, "");
            } else {
                handler.colorReplaced(f, idx);
            }
        }
    }
