public class Snippet__5440125 {

    @Override
        public void clear() {
            if (length > 0) {
                items = new Object[items.length];
                length = 0;
                modificationCount++;

                if (listListeners != null) {
                    listListeners.listCleared(this);
                }
            }
        }

}
