public class Snippet__9294785 {

    private void setElem(final int index, final double elem) {
                try {
                    if (index < nb.limit()) {
                        nb.put(index, elem);
                    }
                } catch (final IndexOutOfBoundsException e) {
                    throw new ClassCastException();
                 }
            }

}
