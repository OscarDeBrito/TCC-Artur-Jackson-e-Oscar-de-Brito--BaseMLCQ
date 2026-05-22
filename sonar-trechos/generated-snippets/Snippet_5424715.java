public class Snippet__5424715 {

    @Override
        public int add(final T item) {
            int index = -1;

            if (comparator == null) {
                index = getLength();
            } else {
                // Perform a binary search to find the insertion point
                index = Collections.binarySearch(list, item, comparator);
                if (index < 0) {
                    index = -(index + 1);
                }
            }

            list.add(index, item);
            listListeners.itemInserted(this, index);

            return index;
        }

}
