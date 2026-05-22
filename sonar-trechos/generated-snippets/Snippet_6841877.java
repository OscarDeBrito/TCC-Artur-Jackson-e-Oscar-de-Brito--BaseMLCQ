public class Snippet__6841877 {

    /* package */ abstract class AbstractJudgment {

    	@Inject
    	protected N4JSTypeSystem ts;
    	@Inject
    	protected TypeSystemHelper typeSystemHelper;
    	@Inject
    	protected ContainerTypesHelper containerTypesHelper;
    	@Inject
    	protected N4IDLVersionResolver n4idlVersionResolver;

    	protected static UnknownTypeRef unknown() {
    		return TypeRefsFactory.eINSTANCE.createUnknownTypeRef();
    	}
    }

}
