public class Snippet__8309635 {

    public static IJavaElement[] setMinus(IJavaElement[] setToRemoveFrom, IJavaElement[] elementsToRemove) {
    		Set<IJavaElement> setMinus= new HashSet<>(setToRemoveFrom.length - setToRemoveFrom.length);
    		setMinus.addAll(Arrays.asList(setToRemoveFrom));
    		setMinus.removeAll(Arrays.asList(elementsToRemove));
    		return setMinus.toArray(new IJavaElement[setMinus.size()]);
    	}

}
