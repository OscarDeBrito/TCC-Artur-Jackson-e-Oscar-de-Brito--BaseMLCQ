public class Snippet__7117242 {

    public Snippet__7117242(int... pairs) {
            if ((pairs.length & 1) != 0) {
                throw new IllegalArgumentException("Must have even number of integers");
            }
            list = new int[pairs.length + 1]; // don't allocate extra space, because it is likely that this is a fixed set.
            len = list.length;
            int last = -1; // used to ensure that the results are monotonically increasing.
            int i = 0;
            while (i < pairs.length) {
                // start of pair
                int start = pairs[i];
                if (last >= start) {
                    throw new IllegalArgumentException("Must be monotonically increasing.");
                }
                list[i++] = last = start;
                // end of pair
                int end = pairs[i] + 1;
                if (last >= end) {
                    throw new IllegalArgumentException("Must be monotonically increasing.");
                }
                list[i++] = last = end;
            }
            list[i] = HIGH; // terminate
        }

}
