public class Snippet__8333175 {

    private void doAggressiveInner(LazyClassGen gen) {
    		// agressively generate the inner interface if any
    		// Note: we do so because of the bug #75442 that leads to have this interface implemented by all classes and not
    		// only those matched by the per clause, which fails under LTW since the very first class
    		// gets weaved and impl this interface that is still not defined.
    		if (!hasGeneratedInner) {
    			if (kind == PerClause.PEROBJECT) {// redundant test - see constructor, but safer
    				// inner class
    				UnresolvedType interfaceTypeX = AjcMemberMaker.perObjectInterfaceType(aspectType);
    				LazyClassGen interfaceGen = new LazyClassGen(interfaceTypeX.getName(), "java.lang.Object", null,
    						Constants.ACC_INTERFACE + Constants.ACC_PUBLIC + Constants.ACC_ABSTRACT, new String[0], getWorld());
    				interfaceGen.addMethodGen(makeMethodGen(interfaceGen, AjcMemberMaker.perObjectInterfaceGet(aspectType)));
    				interfaceGen.addMethodGen(makeMethodGen(interfaceGen, AjcMemberMaker.perObjectInterfaceSet(aspectType)));
    				// not really an inner class of it but that does not matter, we pass back to the LTW
    				gen.addGeneratedInner(interfaceGen);
    			}
    			hasGeneratedInner = true;
    		}
    	}

}
