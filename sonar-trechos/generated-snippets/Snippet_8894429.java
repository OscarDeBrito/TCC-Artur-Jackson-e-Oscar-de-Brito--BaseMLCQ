public class Snippet__8894429 {

    protected FastCheckPrecisionScaleStatus fastCheckPrecisionScale(
          int maxPrecision, int maxScale) {
        return
            FastHiveDecimalImpl.fastCheckPrecisionScale(
                fastSignum, fast0, fast1, fast2,
                fastIntegerDigitCount, fastScale,
                maxPrecision, maxScale);
      }

}
