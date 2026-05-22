public class AlternativeYamlTraversal extends AbstractYamlTraversal {

	private YamlTraversal first;
	private YamlTraversal second;

	public AlternativeYamlTraversal(YamlTraversal first, YamlTraversal second) {
		Assert.isLegal(!first.isEmpty());
		Assert.isLegal(!second.isEmpty());
		this.first = first;
		this.second = second;
	}

	@Override
	public <T extends YamlNavigable<T>> Stream<T> traverseAmbiguously(T start) {
		return Stream.concat(
				first.traverseAmbiguously(start),
				second.traverseAmbiguously(start)
		);
	}

	@Override
	public boolean canEmpty() {
		return first.canEmpty() || second.canEmpty();
	}

	@Override
	public String toString() {
		return "Or("+first+", "+second+")";
	}

}
