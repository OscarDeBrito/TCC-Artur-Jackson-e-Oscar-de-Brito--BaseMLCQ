@Parameters(separators = "=")
public class GerritOptions implements Option {

  private static final Pattern CHANGE_ID_PATTERN = Pattern.compile("I[0-9a-f]{40}");
  protected final GeneralOptions generalOptions;
  protected GitOptions gitOptions;

  public GerritOptions(GeneralOptions generalOptions, GitOptions gitOptions) {
    this.generalOptions = generalOptions;
    this.gitOptions = gitOptions;
  }

  /** Validate that the argument is a valid Gerrit Change-id */
  public static final class ChangeIdValidator implements IParameterValidator {

    @Override
    public void validate(String name, String value) throws ParameterException {
      if (!Strings.isNullOrEmpty(value) && !CHANGE_ID_PATTERN.matcher(value).matches()) {
        throw new ParameterException(
            String.format("%s value '%s' does not match Gerrit Change ID pattern: %s",
                name, value, CHANGE_ID_PATTERN.pattern()));
      }
    }
  }

  @Parameter(names = "--gerrit-change-id",
      description = "ChangeId to use in the generated commit message. Use this flag if you want "
          + "to reuse the same Gerrit review for an export.",
      validateWith = ChangeIdValidator.class)
  protected String gerritChangeId = "";

  @Parameter(names = "--gerrit-new-change",
      description = "Create a new change instead of trying to reuse an existing one.")
  protected boolean newChange = false;

  @Parameter(names = "--gerrit-topic", description = "Gerrit topic to use")
  protected String gerritTopic = "";

  @Parameter(names = "--nogerrit-rev-id-label", description = "DEPRECATED. Use workflow set_rev_id"
      + " field instead.", hidden = true)
  @Deprecated
  protected boolean noRevIdDEPRECATED = false;

  /**
   * Returns a lazy supplier of {@link GerritApi}.
   */
  public LazyResourceLoader<GerritApi> newGerritApiSupplier(String url, @Nullable Checker checker) {
    return (console) ->
        checker == null ? newGerritApi(url) : newGerritApi(url, checker, console);
  }

  /**
   * Override this method in a class for a specific Gerrit implementation.
   */
  @VisibleForTesting
  public GerritApi newGerritApi(String url) throws RepoException, ValidationException {
    return new GerritApi(newGerritApiTransport(hostUrl(url)),
                         generalOptions.profiler());
  }

  /**
   * Creates a new {@link GerritApi} enforcing the given {@link Checker}.
   */
  public GerritApi newGerritApi(String url, Checker checker, Console console)
      throws ValidationException, RepoException {
    return new GerritApi(newGerritApiTransport(hostUrl(url), checker, console),
        generalOptions.profiler());
  }

  /**
   * Return the url removing the path part, since the API needs the host.
   */
  private static URI hostUrl(String url) throws ValidationException {
    URI result = asUri(url);
    try {
      checkCondition(result.getHost() != null, "Wrong url: %s", url);
      checkCondition(result.getScheme() != null, "Wrong url: %s", url);
      return new URI(result.getScheme(), result.getUserInfo(), result.getHost(), result.getPort(),
                     /*path=*/null, /*query=*/null, /*fragment=*/null);
    } catch (URISyntaxException e) {
      // Shouldn't happen
      throw new IllegalStateException(e);
    }
  }

  private static URI asUri(String url) throws ValidationException {
    try {
      return URI.create(url);
    } catch (IllegalArgumentException e) {
      throw new ValidationException("Invalid URL " + url);
    }
  }

  /**
   * Given a repo url, return the project part.
   *
   * <p>Not static on prupose, since we might introduce different behavior based on
   * other flags in the future.
   */
  @SuppressWarnings("MethodMayBeStatic")
  public String getProject(String url) throws ValidationException {
    String file = asUri(url).getPath();
    if (file.startsWith("/")) {
      file = file.substring(1);
    }
    if (file.endsWith("/")) {
      file = file.substring(0, file.length() - 1);
    }
    return file.replaceAll("[ \"'&]", "");
  }

  /**
   * Create a Gerrit http transport for a URI.
   */
  protected GerritApiTransport newGerritApiTransport(URI uri)
      throws RepoException, ValidationException {
    return new GerritApiTransportImpl(getCredentialsRepo(), uri, getHttpTransport());
  }

  /**
   * Create a Gerrit http transport for a URI and {@link Checker}.
   */
  protected GerritApiTransport newGerritApiTransport(URI uri, Checker checker, Console console)
      throws RepoException, ValidationException {
    return new GerritApiTransportWithChecker(newGerritApiTransport(uri), checker, console);
  }

  @VisibleForTesting
  protected GitRepository getCredentialsRepo() throws RepoException {
    return gitOptions.cachedBareRepoForUrl("just_for_github_api");
  }

  @VisibleForTesting
  protected HttpTransport getHttpTransport() {
    return new NetHttpTransport();
  }


  /** Validate if a {@link Checker} is valid to use with a Gerrit endpoint for repoUrl. */
  public void validateEndpointChecker(@Nullable Checker checker, String repoUrl)
      throws ValidationException {
    // Accept any by default
  }
}
