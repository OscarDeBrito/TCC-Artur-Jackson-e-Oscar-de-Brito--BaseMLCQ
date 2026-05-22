public class Snippet__5842829 {

    @Override
        public void write(String s) throws IOException {

            if (suspended) {
                return;
            }

            if (s == null) {
                s = "null";
            }
            write(s, 0, s.length());
        }

}
