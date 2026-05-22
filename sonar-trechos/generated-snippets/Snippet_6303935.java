public class Snippet__6303935 {

    public void directUpgrade()
        {
            if (state != State.PREFACE)
                throw new IllegalStateException();
            prefaceParser.directUpgrade();
        }

}
