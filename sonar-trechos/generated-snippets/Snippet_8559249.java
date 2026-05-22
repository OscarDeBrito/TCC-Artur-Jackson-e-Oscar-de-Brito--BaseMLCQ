public class Snippet__8559249 {

    private BackupCommand getCommand(String cmdStr) throws IOException {
          switch (cmdStr) {
            case SET_ADD_CMD:
              return BackupCommand.SET_ADD;
            case SET_REMOVE_CMD:
              return BackupCommand.SET_REMOVE;
            case SET_DELETE_CMD:
              return BackupCommand.SET_DELETE;
            case SET_DESCRIBE_CMD:
              return BackupCommand.SET_DESCRIBE;
            case SET_LIST_CMD:
              return BackupCommand.SET_LIST;
            default:
              System.out.println("ERROR: Unknown command for 'set' :" + cmdStr);
              printUsage();
              throw new IOException(INCORRECT_USAGE);
          }
        }

}
