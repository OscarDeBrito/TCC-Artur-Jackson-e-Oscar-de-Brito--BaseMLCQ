public final class LuhnCheckDigit extends ModulusCheckDigit {

    private static final long serialVersionUID = -2976900113942875999L;

    /** Singleton Luhn Check Digit instance */
    public static final CheckDigit LUHN_CHECK_DIGIT = new LuhnCheckDigit();

    /** weighting given to digits depending on their right position */
    private static final int[] POSITION_WEIGHT = new int[] {2, 1};

    /**
     * Construct a modulus 10 Luhn Check Digit routine.
     */
    public LuhnCheckDigit() {
        super(10); // CHECKSTYLE IGNORE MagicNumber
    }

    /**
     * <p>Calculates the <i>weighted</i> value of a charcter in the
     * code at a specified position.</p>
     *
     * <p>For Luhn (from right to left) <b>odd</b> digits are weighted
     * with a factor of <b>one</b> and <b>even</b> digits with a factor
     * of <b>two</b>. Weighted values &gt; 9, have 9 subtracted</p>
     *
     * @param charValue The numeric value of the character.
     * @param leftPos The position of the character in the code, counting from left to right
     * @param rightPos The positionof the character in the code, counting from right to left
     * @return The weighted value of the character.
     */
    @Override
    protected int weightedValue(int charValue, int leftPos, int rightPos) {
        int weight = POSITION_WEIGHT[rightPos % 2]; // CHECKSTYLE IGNORE MagicNumber
        int weightedValue = charValue * weight;
        return weightedValue > 9 ? (weightedValue - 9) : weightedValue; // CHECKSTYLE IGNORE MagicNumber
    }
}
