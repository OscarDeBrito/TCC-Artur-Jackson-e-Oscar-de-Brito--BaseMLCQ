public class Snippet__9218768 {

    public Enumeration<K> keys() {
            Node<K,V>[] t;
            int f = (t = table) == null ? 0 : t.length;
            return new KeyIterator<K,V>(t, f, 0, f, this);
        }

}
