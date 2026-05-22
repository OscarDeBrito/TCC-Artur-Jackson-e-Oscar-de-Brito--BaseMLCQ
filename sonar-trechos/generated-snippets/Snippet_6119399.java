public class Snippet__6119399 {

    @Override
        public boolean allSatisfy(Predicate<? super T> predicate)
        {
            return this.delegate.allSatisfy(new SelectAllSatisfyPredicate<>(this.predicate, predicate));
        }

}
