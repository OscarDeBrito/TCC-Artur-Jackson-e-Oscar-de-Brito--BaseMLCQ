public class Snippet__3775458 {

    private static short checkAndCastYear(int year)
        {
            if (year < 1 || year > 9999)
            {
                throw new IllegalArgumentException(String.format("Year %s must be between 1 and 9999 inclusive", year));
            }

            return (short) year;
        }

}
