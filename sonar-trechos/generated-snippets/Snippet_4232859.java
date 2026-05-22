public class Snippet__4232859 {

    public static <T> Predicate<T> orPredicate(final Predicate<? super T> predicate1,
                                                   final Predicate<? super T> predicate2) {
            return OrPredicate.orPredicate(predicate1, predicate2);
        }

}
