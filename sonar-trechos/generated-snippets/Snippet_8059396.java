public class HiddenRegionFormattingMerger implements IMerger<IHiddenRegionFormatting> {

	private final AbstractFormatter2 formatter;

	public HiddenRegionFormattingMerger(AbstractFormatter2 formatter) {
		super();
		this.formatter = formatter;
	}

	@Override
	public IHiddenRegionFormatting merge(List<? extends IHiddenRegionFormatting> conflicting) {
		if (conflicting.size() == 2) {
			// TODO: don't do this
			conflicting.get(1).mergeValuesFrom(conflicting.get(0));
			return conflicting.get(1);
		}
		IHiddenRegionFormatting result = formatter.createHiddenRegionFormatting();
		for (IHiddenRegionFormatting conflict : conflicting)
			result.mergeValuesFrom(conflict);
		return result;
	}

}
