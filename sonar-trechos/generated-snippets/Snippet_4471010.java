public class Snippet__4471010 {

    @Override
      public void computeScalePrecision(int leftPrecision, int leftScale, int rightPrecision, int rightScale) {

        // compute the output scale and precision here
        outputScale = Math.max(leftScale, rightScale);
        int leftIntegerDigits = leftPrecision - leftScale;

        outputPrecision = DRILL_REL_DATATYPE_SYSTEM.getMaxNumericPrecision();

        if (outputScale + leftIntegerDigits > outputPrecision) {
          outputScale = outputPrecision - leftIntegerDigits;
        }

        // Output precision should at least be greater or equal to the input precision
        outputPrecision = Math.min(outputPrecision, Math.max(leftPrecision, rightPrecision));
      }

}
