public class Snippet__5883695 {

    @Override
        public Set<String> getCounterNames() throws Exception {
            Set<String> names = new TreeSet<String>( CASE_INSENSITIVE_ORDER );
            Set<String> nameSet = cast( getDictionaryAsSet( getApplicationRef(), Schema.DICTIONARY_COUNTERS ) );
            names.addAll( nameSet );
            return names;
        }

}
