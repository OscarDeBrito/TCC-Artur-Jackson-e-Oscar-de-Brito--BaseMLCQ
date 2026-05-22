public class DescribedAsFacetNone extends DescribedAsFacetAbstract {

    public DescribedAsFacetNone(final FacetHolder holder) {
        super("", holder);
    }

    @Override
    public boolean isNoop() {
        return true;
    }

}
