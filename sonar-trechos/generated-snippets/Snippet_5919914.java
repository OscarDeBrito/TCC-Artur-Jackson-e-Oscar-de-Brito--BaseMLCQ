public class Snippet__5919914 {

    private static final int twoDigit(CharSequence str, int index)
        {
            char ch1 = str.charAt(index);
            char ch2 = str.charAt(index + 1);
            if (!isDigit(ch1) || !isDigit(ch2))
                return 100; // not two digits
            return digitVal(ch1) * 10 + digitVal(ch2);
        }

}
