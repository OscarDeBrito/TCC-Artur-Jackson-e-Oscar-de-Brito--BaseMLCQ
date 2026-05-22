public class Snippet__4772031 {

    @Override
      public double probabilityOfAttributeValueGivenClass(double attVal,
          int classVal) {
        DoubleVector obs = this.attValDistPerClass.get(classVal);
        return obs != null ? (obs.getValue((int) attVal) + 1.0)
            / (obs.sumOfValues() + obs.numValues()) : 0.0;
      }

}
