public class Snippet__7876519 {

    public ImmutableList<BuildLogEntry> getBuildLogs() throws IOException {
        // Remove commands with unknown args or invocations of buck rage.
        // Sort the remaining logs based on time, reverse order.
        ImmutableList.Builder<BuildLogEntry> logEntries = ImmutableList.builder();
        for (Path logFile : getAllBuckLogFiles()) {
          BuildLogEntry entry = newBuildLogEntry(logFile);
          if (entry.getCommandArgs().isPresent()
              && entry.getCommandArgs().get().size() > 0
              && !entry.getCommandArgs().get().get(0).matches("^(rage|doctor|server|launch)$")) {
            logEntries.add(newBuildLogEntry(logFile));
          }
        }
        return logEntries
            .build()
            .stream()
            .sorted(Comparator.comparing(BuildLogEntry::getLastModifiedTime).reversed())
            .collect(ImmutableList.toImmutableList());
      }

}
