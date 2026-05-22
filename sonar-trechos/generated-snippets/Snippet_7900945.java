public class Snippet__7900945 {

    private void printRemove(ParsedRuleKeyFile file, Value value) {
            diffPrinter.printRemove(
                String.format(
                    "%s: %s", String.join("/", pathComponents), valueAsReadableString(file, value)));
          }

}
