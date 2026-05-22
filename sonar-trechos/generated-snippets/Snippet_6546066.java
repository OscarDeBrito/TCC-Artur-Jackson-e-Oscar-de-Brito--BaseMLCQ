public class N4JSLambdaValidator extends AbstractN4JSDeclarativeValidator {

	@Inject
	ContainerTypesHelper containerTypesHelper;

	/**
	 * NEEEDED
	 *
	 * when removed check methods will be called twice once by N4JSValidator, and once by
	 * AbstractDeclarativeN4JSValidator
	 */
	@Override
	public void register(EValidatorRegistrar registrar) {
		// nop
	}

	/**
	 * A top-level arrow function can't include uses of <code>arguments</code> and <code>this</code> as they lack an
	 * outer lexical context that would provide bindings for them.
	 */
	@Check
	public void checkTopLevelLambda(ArrowFunction arrowFun) {
		if (LambdaUtils.isTopLevelLambda(arrowFun)) {
			rejectUsagesOfThisInTopLevelLambda(arrowFun);
		}
	}

	/**
	 * Rejects uses of 'this' in top-level functions, which due to their top-level nature can't capture any 'this' from
	 * the enclosing context (same goes for 'arguments', by the way).
	 * <p>
	 * Precondition: the argument is a top-level lambda.
	 */
	private void rejectUsagesOfThisInTopLevelLambda(ArrowFunction topLevelLambda) {
		assert LambdaUtils.isLambda(topLevelLambda);
		Iterator<EObject> thisUsages = LambdaUtils.thisLiterals(topLevelLambda.getBody());
		while (thisUsages.hasNext()) {
			EObject thisUsage = thisUsages.next();
			String message = IssueCodes.getMessageForKEY_THIS_REJECTED_IN_TOP_LEVEL_LAMBDA();
			addIssue(message, thisUsage, IssueCodes.KEY_THIS_REJECTED_IN_TOP_LEVEL_LAMBDA);
		}
	}
}
