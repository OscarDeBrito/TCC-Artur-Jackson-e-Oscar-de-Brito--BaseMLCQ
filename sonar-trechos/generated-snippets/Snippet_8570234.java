public class Snippet__8570234 {

    public final static class Bucket {
        private long baseOffset;
        private int itemAllocationSize, sizeIndex;
        private int itemCount;
        private int freeList[];
        private int freeCount, usedCount;

        public Bucket(long offset) {
          baseOffset = offset;
          sizeIndex = -1;
        }

        void reconfigure(int sizeIndex, int[] bucketSizes, long bucketCapacity) {
          Preconditions.checkElementIndex(sizeIndex, bucketSizes.length);
          this.sizeIndex = sizeIndex;
          itemAllocationSize = bucketSizes[sizeIndex];
          itemCount = (int) (bucketCapacity / (long) itemAllocationSize);
          freeCount = itemCount;
          usedCount = 0;
          freeList = new int[itemCount];
          for (int i = 0; i < freeCount; ++i)
            freeList[i] = i;
        }

        public boolean isUninstantiated() {
          return sizeIndex == -1;
        }

        public int sizeIndex() {
          return sizeIndex;
        }

        public int getItemAllocationSize() {
          return itemAllocationSize;
        }

        public boolean hasFreeSpace() {
          return freeCount > 0;
        }

        public boolean isCompletelyFree() {
          return usedCount == 0;
        }

        public int freeCount() {
          return freeCount;
        }

        public int usedCount() {
          return usedCount;
        }

        public int getFreeBytes() {
          return freeCount * itemAllocationSize;
        }

        public int getUsedBytes() {
          return usedCount * itemAllocationSize;
        }

        public long getBaseOffset() {
          return baseOffset;
        }

        /**
         * Allocate a block in this bucket, return the offset representing the
         * position in physical space
         * @return the offset in the IOEngine
         */
        public long allocate() {
          assert freeCount > 0; // Else should not have been called
          assert sizeIndex != -1;
          ++usedCount;
          long offset = baseOffset + (freeList[--freeCount] * itemAllocationSize);
          assert offset >= 0;
          return offset;
        }

        public void addAllocation(long offset) throws BucketAllocatorException {
          offset -= baseOffset;
          if (offset < 0 || offset % itemAllocationSize != 0)
            throw new BucketAllocatorException(
                "Attempt to add allocation for bad offset: " + offset + " base="
                    + baseOffset + ", bucket size=" + itemAllocationSize);
          int idx = (int) (offset / itemAllocationSize);
          boolean matchFound = false;
          for (int i = 0; i < freeCount; ++i) {
            if (matchFound) freeList[i - 1] = freeList[i];
            else if (freeList[i] == idx) matchFound = true;
          }
          if (!matchFound)
            throw new BucketAllocatorException("Couldn't find match for index "
                + idx + " in free list");
          ++usedCount;
          --freeCount;
        }

        private void free(long offset) {
          offset -= baseOffset;
          assert offset >= 0;
          assert offset < itemCount * itemAllocationSize;
          assert offset % itemAllocationSize == 0;
          assert usedCount > 0;
          assert freeCount < itemCount; // Else duplicate free
          int item = (int) (offset / (long) itemAllocationSize);
          assert !freeListContains(item);
          --usedCount;
          freeList[freeCount++] = item;
        }

        private boolean freeListContains(int blockNo) {
          for (int i = 0; i < freeCount; ++i) {
            if (freeList[i] == blockNo) return true;
          }
          return false;
        }
      }

}
