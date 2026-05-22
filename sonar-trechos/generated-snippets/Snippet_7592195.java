public class Snippet__7592195 {

    @CliCommand(value = "!g",
          help = "Passes a command directly through to the Felix shell infrastructure.")
      public void shell(
          @CliOption(
              key = "",
              mandatory = false,
              specifiedDefaultValue = "help",
              unspecifiedDefaultValue = "help",
              help = "The command to pass to Felix (WARNING: no validation or security checks are performed)."
                  + "Default: `help`.") final String commandLine) throws Exception {

        perform(commandLine);
      }

}
