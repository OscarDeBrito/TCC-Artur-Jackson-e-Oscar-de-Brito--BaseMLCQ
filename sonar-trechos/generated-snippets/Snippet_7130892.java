public class Snippet__7130892 {

    private static final class StringCharacterIterator implements CharacterIterator {
            int pos;
            String s;

            public StringCharacterIterator(String s) {
                this.s = s;
            }

            public boolean hasNext() {
                return pos < s.length();
            }

            public char next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                return s.charAt(pos++);
            }

            public char peek() {
                if (!hasNext())
                    throw new NoSuchElementException();

                return s.charAt(pos++);
            }

            public int pos() {
                if (pos == 0) return 0;
                return pos - 1;
            }

        }

}
