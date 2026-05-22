public abstract class AbstractBuilderTestLanguageValidator extends AbstractDeclarativeValidator {
	
	@Override
	protected List<EPackage> getEPackages() {
		List<EPackage> result = new ArrayList<EPackage>();
		result.add(org.eclipse.xtext.builder.tests.builderTestLanguage.BuilderTestLanguagePackage.eINSTANCE);
		return result;
	}
}
