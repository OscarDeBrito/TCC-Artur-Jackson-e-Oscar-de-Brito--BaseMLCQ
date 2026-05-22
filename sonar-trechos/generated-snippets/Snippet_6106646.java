public class Snippet__6106646 {

    public static <T> boolean removeIf(List<T> list, Predicate<? super T> predicate, Procedure<? super T> procedure)
        {
            boolean changed = false;
            for (int i = 0; i < list.size(); i++)
            {
                T each = list.get(i);
                if (predicate.accept(each))
                {
                    procedure.value(each);
                    list.remove(i--);
                    changed = true;
                }
            }
            return changed;
        }

}
