public class Snippet__5319099 {

    private static final class Tree {
            final Node root = new Node();

            void fill(final int depth, final int path, final int value) throws IOException {
                Node current = root;

                for (int i = 0; i < depth; i++) {
                    int bitPos = depth - 1 - i;
                    boolean isSet = ((path >> bitPos) & 1) == 1;
                    Node next = current.walk(isSet);

                    if (next == null) {
                        next = new Node();

                        if (i == depth - 1) {
                            next.value = value;
                            next.isLeaf = true;
                        }

                        if (path == 0) {
                            next.canBeFill = true;
                        }

                        current.set(isSet, next);
                    }
                    else {
                        if (next.isLeaf) {
                            throw new IOException("node is leaf, no other following");
                        }
                    }

                    current = next;
                }
            }

            void fill(final int depth, final int path, final Node node) throws IOException {
                Node current = root;

                for (int i = 0; i < depth; i++) {
                    int bitPos = depth - 1 - i;
                    boolean isSet = ((path >> bitPos) & 1) == 1;
                    Node next = current.walk(isSet);

                    if (next == null) {
                        if (i == depth - 1) {
                            next = node;
                        }
                        else {
                            next = new Node();
                        }

                        if (path == 0) {
                            next.canBeFill = true;
                        }

                        current.set(isSet, next);
                    }
                    else {
                        if (next.isLeaf) {
                            throw new IOException("node is leaf, no other following");
                        }
                    }

                    current = next;
                }
            }
        }

}
