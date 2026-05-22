public class Snippet__4745800 {

    private void checkAccess(int index) throws ArrayIndexOutOfBoundsException {
            if (index < 0 || index >= size) {
                throw new ArrayIndexOutOfBoundsException();
            }
        }

}
