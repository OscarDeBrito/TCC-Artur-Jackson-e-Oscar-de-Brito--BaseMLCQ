public class Snippet__9324338 {

    public String getMessage() {
            if (minor == 0)
                return (getMajorString());

            return (getMajorString()
                    + " (Mechanism level: " + getMinorString() + ")");
        }

}
