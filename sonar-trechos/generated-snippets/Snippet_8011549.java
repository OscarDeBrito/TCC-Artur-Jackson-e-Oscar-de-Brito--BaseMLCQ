public class JvmUpperBoundImplCustom extends JvmUpperBoundImpl {

	private static final String EXTENDS = "extends ";

	@Override
	public String getIdentifier() {
		if (typeReference != null)
			return EXTENDS + super.getIdentifier();
		return null;
	}
	
	@Override
	public String getSimpleName() {
		if (typeReference != null)
			return EXTENDS + super.getSimpleName();
		return null;
	}
	
	@Override
	public String getQualifiedName(char innerClassDelimiter) {
		if (typeReference != null)
			return EXTENDS + super.getQualifiedName(innerClassDelimiter);
		return null;
	}
	
	@Override
	public String toString() {
		if (typeReference != null)
			return EXTENDS + super.toString();
		return eClass().getName();
	}
}
