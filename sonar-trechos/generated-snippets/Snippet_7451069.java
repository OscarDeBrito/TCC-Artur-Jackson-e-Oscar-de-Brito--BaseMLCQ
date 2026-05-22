public class Snippet__7451069 {

    @Override
        @Uninterruptible(reason = "May be called from uninterruptible code.", mayBeInlined = true)
        public Pointer mapFile(PointerBase start, UnsignedWord nbytes, WordBase fileHandle, UnsignedWord offset, int access) {
            int flags = MAP_PRIVATE();
            if (start.isNonNull()) {
                flags |= MAP_FIXED();
            }
            int fd = (int) fileHandle.rawValue();
            Pointer result = mmap(start, nbytes, accessAsProt(access), flags, fd, offset.rawValue());
            return result.notEqual(MAP_FAILED()) ? result : WordFactory.nullPointer();
        }

}
