public class Snippet__7628568 {

    @Override
    		public boolean select(Viewer viewer, Object parentElement, Object element) {
    			if (matcher==null) {
    				return true;
    			} else {
    				String label = labelProvider.getText(element);
    				if (match(label)) {
    					return true;
    				} else if (element instanceof Describable) {
    					String description = ((Describable) element).getDescription();
    					return match(description);
    				}
    				return false;
    			}
    		}

}
