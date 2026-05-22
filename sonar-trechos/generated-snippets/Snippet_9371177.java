public class Snippet__9371177 {

    public static final class CompositeBitSpec extends BitSpec {
            private final BitSpec left;
            private final int leftWidth;
            private final BitSpec right;
            private final int rightWidth;
            private final int width;

            public CompositeBitSpec(BitSpec left, BitSpec right) {
                super(left.isSignExtend());
                assert !right.isSignExtend() : String.format("Right field %s must not be sign extended", right);
                this.left = left;
                this.leftWidth = left.getWidth();
                this.right = right;
                this.rightWidth = right.getWidth();
                this.width = leftWidth + rightWidth;
            }

            @Override
            public int getBits(int word) {
                int l = left.getBits(word);
                int r = right.getBits(word);
                return (l << rightWidth) | r;
            }

            @Override
            public int setBits(int word, int value) {
                int l = leftBits(value);
                int r = rightBits(value);
                return left.setBits(right.setBits(word, r), l);
            }

            private int leftBits(int value) {
                return getBits(value, width - 1, rightWidth, signExtend);
            }

            private int rightBits(int value) {
                return getBits(value, rightWidth - 1, 0, false);
            }

            @Override
            public int getWidth() {
                return width;
            }

            @Override
            public String toString() {
                return String.format("CompositeBitSpec[%s, %s]", left, right);
            }

            @Override
            public boolean valueFits(int value) {
                int l = leftBits(value);
                int r = rightBits(value);
                return left.valueFits(l) && right.valueFits(r);
            }

            private static int getBits(int inst, int hiBit, int lowBit, boolean signExtended) {
                int shifted = inst >> lowBit;
                if (signExtended) {
                    return shifted;
                } else {
                    return shifted & ((1 << (hiBit - lowBit + 1)) - 1);
                }
            }
        }

}
