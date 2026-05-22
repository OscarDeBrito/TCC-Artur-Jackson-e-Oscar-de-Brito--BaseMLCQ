public class Snippet__8785814 {

    @Override
      public int run(String[] args) throws IOException {
        if (args.length == 0) {
          printUsage(false);
          return 1;
        }
        final Command command = determineCommand(args[0]);
        if (command == null) {
          System.err.println("Can't understand command '" + args[0] + "'");
          if (!args[0].startsWith("-")) {
            System.err.println("Command names must start with dashes.");
          }
          printUsage(false);
          return 1;
        }
        final List<String> argsList = new LinkedList<String>();
        for (int j = 1; j < args.length; j++) {
          argsList.add(args[j]);
        }
        try {
          return command.run(getConf(), argsList);
        } catch (IllegalArgumentException e) {
          System.err.println(prettifyException(e));
          return -1;
        }
      }

}
