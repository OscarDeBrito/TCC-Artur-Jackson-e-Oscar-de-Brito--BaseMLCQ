public class Snippet__9295088 {

    private static long openProcessToken(int access) {
            try {
                return OpenProcessToken(GetCurrentProcess(), access);
            } catch (WindowsException x) {
                return 0L;
            }
        }

}
