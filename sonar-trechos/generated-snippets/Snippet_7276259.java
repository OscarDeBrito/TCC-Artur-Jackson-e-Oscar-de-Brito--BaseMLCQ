public class Snippet__7276259 {

    protected final void setBufferType(int bufferType) {
            if (this.bufferType == bufferType) return;
            if (bufferType == BUFFER_NONE ||
                bufferType == BUFFER_IMAGE ||
                bufferType == BUFFER_VOLATILE_IMAGE) {
                this.bufferType = bufferType;
                releaseOffscreenImage();
                repaintImpl(0, 0, getWidth(), getHeight());
            } else {
                throw new IllegalArgumentException("Unknown buffer type: " + bufferType); // NOI18N
            }
        }

}
