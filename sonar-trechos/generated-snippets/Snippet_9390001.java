public class Snippet__9390001 {

    public int hashCode() {
                // Sum up the hash codes of the components.
                int hash = 0;

                // For each type/value pair...
                for (int i = 0; i < tvs.size(); i++) {
                    hash += tvs.elementAt(i).hashCode();
                }
                return hash;
            }

}
