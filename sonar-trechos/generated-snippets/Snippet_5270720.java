public class POSTaggerNameFeatureGenerator implements AdaptiveFeatureGenerator {

  private POSTagger posTagger;

  private String[] cachedTokens;
  private String[] cachedTags;

  /**
   * Initializes a new instance.
   *
   * @param aPosTagger a POSTagger implementation.
   */
  public POSTaggerNameFeatureGenerator(POSTagger aPosTagger) {
    this.posTagger = aPosTagger;
  }

  /**
   * Initializes a new instance.
   *
   * @param aPosModel a POSTagger model.
   */
  public POSTaggerNameFeatureGenerator(POSModel aPosModel) {

    this.posTagger = new POSTaggerME(aPosModel);
  }


  public void createFeatures(List<String> feats, String[] toks, int index, String[] preds) {
    if (!Arrays.equals(this.cachedTokens, toks)) {
      this.cachedTokens = toks;
      this.cachedTags = this.posTagger.tag(toks);
    }

    feats.add("pos=" + this.cachedTags[index]);
  }


}
