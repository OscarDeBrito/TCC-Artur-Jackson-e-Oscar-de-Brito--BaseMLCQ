public class BC_idiv extends JTTTest {

    public static int test(int a, int b) {
        return a / b;
    }

    @Test
    public void run0() throws Throwable {
        runTest("test", 1, 2);
    }

    @Test
    public void run1() throws Throwable {
        runTest("test", 11, 0);
    }

}
