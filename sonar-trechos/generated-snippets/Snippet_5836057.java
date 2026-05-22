protected static class MultiEnumeration<T> implements Enumeration<T> {
        private final Enumeration<T>[] e;
        public MultiEnumeration(Enumeration<T>[] lists) {
            e = lists;
        }
        @Override
        public boolean hasMoreElements() {
            for ( int i=0; i<e.length; i++ ) {
                if ( e[i].hasMoreElements() ) return true;
            }
            return false;
        }
        @Override
        public T nextElement() {
            for ( int i=0; i<e.length; i++ ) {
                if ( e[i].hasMoreElements() ) return e[i].nextElement();
            }
            return null;

        }
    }
