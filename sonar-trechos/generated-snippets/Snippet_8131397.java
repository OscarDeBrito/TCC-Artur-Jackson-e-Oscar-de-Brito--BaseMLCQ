public class MatcherTransition extends AbstractNFATransition<MatcherState, MatcherTransition> {

	protected Set<IElementPattern> commonPatterns;

	protected Map<MatcherState, Set<IElementPattern>> guardedPatterns;

	public MatcherTransition(MatcherState source, MatcherState target, boolean ruleCall, AbstractElement loopCenter) {
		super(source, target, ruleCall, loopCenter);
	}

	public void addPattern(IElementPattern pattern) {
		if (commonPatterns == null)
			commonPatterns = Sets.newHashSet();
		commonPatterns.add(pattern);
	}

	public void addPattern(MatcherState guard, IElementPattern pattern) {
		if (guardedPatterns == null)
			guardedPatterns = Maps.newHashMap();
		Set<IElementPattern> patterns = guardedPatterns.get(guard);
		if (patterns == null)
			guardedPatterns.put(guard, patterns = Sets.<IElementPattern> newHashSet());
		patterns.add(pattern);
	}

	public Set<IElementPattern> getCommonPatterns() {
		return commonPatterns == null ? Collections.<IElementPattern> emptySet() : commonPatterns;
	}

	public Map<MatcherState, Set<IElementPattern>> getGuardPatterns() {
		return guardedPatterns == null ? Collections.<MatcherState, Set<IElementPattern>> emptyMap() : guardedPatterns;
	}

	public List<IElementPattern> getPatterns(Collection<MatcherState> from) {
		if (guardedPatterns == null && commonPatterns == null)
			return Collections.<IElementPattern> emptyList();
		List<IElementPattern> result = Lists.newArrayList();
		if (commonPatterns != null)
			result.addAll(commonPatterns);
		if (guardedPatterns != null)
			for (MatcherState state : from) {
				Set<IElementPattern> guard = guardedPatterns.get(state);
				if (guard != null)
					result.addAll(guard);
			}
		return result;
	}

}
