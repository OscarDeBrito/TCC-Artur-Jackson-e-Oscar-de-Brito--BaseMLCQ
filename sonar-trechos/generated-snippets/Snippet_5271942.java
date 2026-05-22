public class SentenceModelResourceImpl extends AbstractModelResource<SentenceModel>
    implements SentenceModelResource {

  public SentenceModel getModel() {
    return model;
  }

  @Override
  protected SentenceModel loadModel(InputStream in) throws IOException {
    return new SentenceModel(in);
  }
}
