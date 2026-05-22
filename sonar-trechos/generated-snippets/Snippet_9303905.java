public class Snippet__9303905 {

    private static boolean isMetaName(byte[] name, int off, int len) {
                // Use the "oldest ASCII trick in the book"
                return len > 9                     // "META-INF/".length()
                    && name[off + len - 1] != '/'  // non-directory
                    && (name[off++] | 0x20) == 'm'
                    && (name[off++] | 0x20) == 'e'
                    && (name[off++] | 0x20) == 't'
                    && (name[off++] | 0x20) == 'a'
                    && (name[off++]       ) == '-'
                    && (name[off++] | 0x20) == 'i'
                    && (name[off++] | 0x20) == 'n'
                    && (name[off++] | 0x20) == 'f'
                    && (name[off]         ) == '/';
            }

}
