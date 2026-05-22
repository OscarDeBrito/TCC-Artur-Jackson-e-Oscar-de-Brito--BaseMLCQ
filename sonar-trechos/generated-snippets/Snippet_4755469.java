public class Snippet__4755469 {

    private void printWithBanner(String s, char c) {
        this.outputBufferPrintStream.print(s);
        this.outputBufferPrintStream.print(' ');
        for (int i = 0; i < (CONSOLE_WIDTH - s.length() - 1); i++) {
          this.outputBufferPrintStream.print(c);
        }
        this.outputBufferPrintStream.println();
      }

}
