public class ListEditDistanceSearchModifierFactory implements IInvertedIndexSearchModifierFactory {

    private static final long serialVersionUID = 1L;

    private final int edThresh;

    public ListEditDistanceSearchModifierFactory(int edThresh) {
        this.edThresh = edThresh;
    }

    @Override
    public IInvertedIndexSearchModifier createSearchModifier() {
        return new ListEditDistanceSearchModifier(edThresh);
    }
}
