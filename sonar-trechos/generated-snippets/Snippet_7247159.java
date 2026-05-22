public class RowView implements Comparable<RowView> {
        
        private final ThreadData data;
        
        private int i = -1;
        
        
        RowView(ThreadData data) {
            this.data = data;
            if (getMaxIndex() >= 0) i = findLastIndex();
        }
        
        
        public int getLastIndex() {
            return i == Integer.MIN_VALUE || i == Integer.MAX_VALUE ? -1 : i;
        }
        
        public int getMaxIndex() {
            return data.size() - 1;
        }
        
        public long getTime(int index) {
            return data.getTimeStampAt(index);
        }
        
        public int getState(int index) {
            return data.getStateAt(index);
        }
        
        public int getPosition(long time) {
            return (int)((time - getFirstTime()) * zoom);
        }
        
        // TODO: should return end of last alive state for dead threads
        public int getMaxPosition() {
            return getViewWidth();
        }
        
        
        // TODO: optimize based on current offset / visible area
        private int findLastIndex() {
//            if (data.getThreadRecordsCount(row) == 0) return -1;
            
            if (isTrackingEnd() || isFit()) return getMaxIndex();
            
            i = Integer.MIN_VALUE;
            return findLastIndexLeft();
        }
        
        private int findLastIndexLeft() {
            // All indexes already on right
            if (i == Integer.MAX_VALUE) return i;
            
            int maxIndex = getMaxIndex();
            int newIndex = i == Integer.MIN_VALUE ? maxIndex : i;
            Position position = getIndexPosition(newIndex);
            while (newIndex > 0 && Position.RIGHT.equals(position))
                position = getIndexPosition(--newIndex);
            
            // All indexes on right
            if (Position.RIGHT.equals(position)) return Integer.MAX_VALUE;
            
            // All indexes on left
            if (Position.LEFT.equals(position) && newIndex == maxIndex &&
                getMaxPosition() - offset < 0) return Integer.MIN_VALUE;
            
            // Last visible index
            return newIndex;
        }
        
        private int findLastIndexRight() {
            // All indexes already on right
            if (i == Integer.MIN_VALUE) return i;
            
            int maxIndex = getMaxIndex();
            int newIndex = i == Integer.MAX_VALUE ? 0 : i;
            Position position = getIndexPosition(newIndex);
            while (newIndex < maxIndex && !Position.RIGHT.equals(position))
                position = getIndexPosition(++newIndex);
            
            // First invisible inedx or all indexes on right
            if (Position.RIGHT.equals(position))
                return newIndex == 0 ? Integer.MAX_VALUE : newIndex - 1;
            
            // All indexes on left
            if (Position.LEFT.equals(position) && newIndex == maxIndex &&
                getMaxPosition() - offset < 0) return Integer.MIN_VALUE;
            
            // Last visible index
            return newIndex;
        }
        
        private Position getIndexPosition(int index) {
            int position = getPosition(getTime(index)) - offset;
            if (position < 0) return Position.LEFT;
            else if (position >= width) return Position.RIGHT;
            else return Position.WITHIN;
        }
        
        
        private void offsetChanged(int oldOffset, int newOffset) {
            int maxIndex = getMaxIndex();
            if (maxIndex == -1) return;
            
            if (isTrackingEnd()) {
                i = maxIndex;
            } else {
                if (newOffset > oldOffset) {
                    i = i == -1 ? findLastIndex() : findLastIndexRight();
                } else {
                    i = i == -1 ? findLastIndex() : findLastIndexLeft();
                }
            }
        }
        
        private void widthChanged(int oldWidth, int newWidth) {
            int maxIndex = getMaxIndex();
            if (maxIndex == -1) return;
            
            if (isTrackingEnd() || isFit()) {
                i = maxIndex;
            } else {
                if (newWidth > oldWidth) {
                    i = i == -1 ? findLastIndex() : findLastIndexRight();
                } else {
                    i = i == -1 ? findLastIndex() : findLastIndexLeft();
                }
            }
        }
        
        private boolean lastMaxIn = true;
        private void preferredWidthChanged(int oldWidth, int newWidth) {
            int maxIndex = getMaxIndex();
            if (maxIndex == -1) return;
            
            int currPos = getMaxPosition() - offset;
            if (currPos >= 0 && currPos < width) { // TODO: verify
                i = maxIndex;
                lastMaxIn = true;
            } else {
                if (lastMaxIn && currPos >= width) {
                    // preferred width increases with new data
                    i = maxIndex;
                    findLastIndexLeft();
                }
                lastMaxIn = false;
            }
        }
        
        private void zoomChanged(double oldZoom, double newZoom) {
            int maxIndex = getMaxIndex();
            if (maxIndex == -1) return;
            
            if (isTrackingEnd() || isFit()) {
                i = maxIndex;
            } else {
                i = findLastIndex();
            }
        }

        public int compareTo(RowView view) {
            return Long.compare(data.getFirstTimeStamp(), view.data.getFirstTimeStamp());
        }
        
        public String toString() {
            return BUNDLE().getString("COL_Timeline"); // NOI18N
        }
        
    }
