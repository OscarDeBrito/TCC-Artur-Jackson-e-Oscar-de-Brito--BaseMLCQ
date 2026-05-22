@Parameters(commandDescription = "print out non-default configuration settings")
  static class DumpConfigCommand {
    @Parameter(names = {"-a", "--all"},
        description = "print the system and all table configurations")
    boolean allConfiguration = false;
    @Parameter(names = {"-d", "--directory"}, description = "directory to place config files")
    String directory = null;
    @Parameter(names = {"-s", "--system"}, description = "print the system configuration")
    boolean systemConfiguration = false;
    @Parameter(names = {"-n", "--namespaces"}, description = "print the namespace configuration")
    boolean namespaceConfiguration = false;
    @Parameter(names = {"-t", "--tables"}, description = "print per-table configuration")
    List<String> tables = new ArrayList<>();
    @Parameter(names = {"-u", "--users"},
        description = "print users and their authorizations and permissions")
    boolean users = false;
  }
