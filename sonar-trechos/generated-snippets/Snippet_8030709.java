public class Snippet__8030709 {

    protected IType[] getAllSuperTypes(IType type) throws JavaModelException {
    		/*
    		 * https://stackoverflow.com/questions/49611587/developing-a-eclipse-how-to-get-all-inherited-methods-from-a-icompilationunit
    		 */
    		ITypeHierarchy th = type.newTypeHierarchy(new NullProgressMonitor());
    		IType[] superTypes = th.getAllSuperclasses(type);
    		return superTypes;
    	}

}
