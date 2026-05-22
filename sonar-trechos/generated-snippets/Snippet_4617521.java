public class Snippet__4617521 {

    public static double multiply(int op1, float op2) {
            if (instance.int_multiply) {
                return multiplySlow(op1, op2);
            } else {
                return ((double) op1) * ((double) op2);
            }
        }

}
