public class Snippet__5428702 {

    @Override
        public int compareTo(Cost other) {
            if (isUnknown() && other.isUnknown()) {
                return 0;
            } else if (isUnknown() && !other.isUnknown()) {
                return 1;
            } else if (!isUnknown() && other.isUnknown()) {
                return -1;
            }

            double d = this.io - other.io;
            return d == 0 ? 0 : (d > 0 ? 1 : -1);
        }

}
