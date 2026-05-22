public class Snippet__4059491 {

    @Override
        public void saw(T element) {
          long thisElementIndex = nextElementIndex;
          nextElementIndex++;
          if (thisElementIndex == nextIndexToReport) {
            nextIndexToReport = nextElementIndex;
            report(element);
          }
        }

}
