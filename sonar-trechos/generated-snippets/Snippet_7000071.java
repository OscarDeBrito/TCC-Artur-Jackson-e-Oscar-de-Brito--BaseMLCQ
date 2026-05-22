@Singleton public class XtextResourceFactory {
  @Inject private IResourceSetProvider resourceSetProvider;
  @Inject private IProjects projects;

  /**
   * Creates a new <code>{@link XtextResource}</code>.
   * @param uri the URI of the file containing the EMF model.
   * @param contents the contents of the file.
   * @return the created {@code XtextResource}.
   * @throws IOException if something goes wrong.
   */
  public XtextResource createResource(String uri, String contents) throws IOException {
    return createResource(createURI(uri), contents);
  }

  /**
   * Creates a new <code>{@link XtextResource}</code>.
   * @param uri the URI of the file containing the EMF model.
   * @param contents the contents of the file.
   * @return the created {@code XtextResource}.
   * @throws IOException if something goes wrong.
   */
  public XtextResource createResource(URI uri, String contents) throws IOException {
    // TODO get project from URI.
    ResourceSet resourceSet = resourceSetProvider.get(projects.activeProject());
    XtextResource resource = (XtextResource) resourceSet.createResource(uri, UNSPECIFIED_CONTENT_TYPE);
    resource.load(new StringInputStream(contents), singletonMap(OPTION_ENCODING, UTF_8));
    resolveLazyCrossReferences(resource, NullImpl);
    return resource;
  }
}
