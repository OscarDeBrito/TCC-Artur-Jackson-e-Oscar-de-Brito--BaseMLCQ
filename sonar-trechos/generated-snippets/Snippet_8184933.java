public class Snippet__8184933 {

    public void add(Sequence value) {
            // TODO we can probably optimize this a bit
            for(long i=value.first; i<value.last+1; i++) {
                add(i);
            }
        }

}
