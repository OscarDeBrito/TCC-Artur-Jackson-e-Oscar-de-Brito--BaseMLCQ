public class Snippet__7622719 {

    public static String[][] suggestArgumentNamesWithProposals(IJavaProject project, IMethodBinding binding) {
    		int nParams = binding.getParameterTypes().length;
    		if (nParams > 0) {
    			try {
    				IMethod method = (IMethod) binding.getMethodDeclaration().getJavaElement();
    				if (method != null) {
    					String[] parameterNames = method.getParameterNames();
    					if (parameterNames.length == nParams) {
    						return suggestArgumentNamesWithProposals(project, parameterNames);
    					}
    				}
    			}
    			catch (JavaModelException e) {
    				// ignore
    			}
    		}
    		String[][] names = new String[nParams][];
    		for (int i = 0; i < names.length; i++) {
    			names[i] = new String[] { "arg" + i }; //$NON-NLS-1$
    		}
    		return names;
    	}

}
