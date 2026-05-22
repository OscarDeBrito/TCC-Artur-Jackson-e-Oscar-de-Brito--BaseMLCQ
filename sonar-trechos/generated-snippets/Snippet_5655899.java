public class Snippet__5655899 {

    @Override
            public V setValue(final V value) {
                if (value != null) {
                    throw new UnsupportedOperationException();
                }
                final V old = get();
                dispose();
                return old;
            }

}
