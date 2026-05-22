@Value.Immutable
@BuckStyleImmutable
interface AbstractInstallResult {

  ExitCode getExitCode();

  Optional<Long> getLaunchedPid();
}
