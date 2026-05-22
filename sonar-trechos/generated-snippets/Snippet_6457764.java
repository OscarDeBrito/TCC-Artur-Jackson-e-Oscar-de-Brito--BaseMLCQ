public class Snippet__6457764 {

    public static Bundle[] getFragments(Bundle bundle) {
    		if (packageAdmin == null)
    			throw new IllegalStateException("Not started"); //$NON-NLS-1$

    		return packageAdmin.getFragments(bundle);
    	}

}
