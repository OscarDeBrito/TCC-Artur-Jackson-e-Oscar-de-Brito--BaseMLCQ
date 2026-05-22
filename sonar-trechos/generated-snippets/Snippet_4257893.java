public class Snippet__4257893 {

    @Override
            public int compare(int[] c1,
                               int[] c2) {
                if (c1.length != k) {
                    throw new CombinatoricsException(CombinatoricsException.MISMATCH, c1.length, k);
                }
                if (c2.length != k) {
                    throw new CombinatoricsException(CombinatoricsException.MISMATCH, c2.length, k);
                }

                // Method "lexNorm" works with ordered arrays.
                final int[] c1s = Arrays.copyOf(c1, k);
                final int[] c2s = Arrays.copyOf(c2, k);
                Arrays.sort(c1s);
                Arrays.sort(c2s);

                final long v1 = lexNorm(c1s);
                final long v2 = lexNorm(c2s);

                if (v1 < v2) {
                    return -1;
                } else if (v1 > v2) {
                    return 1;
                } else {
                    return 0;
                }
            }

}
