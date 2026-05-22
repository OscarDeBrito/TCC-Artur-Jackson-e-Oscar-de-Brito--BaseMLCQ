public class Snippet__7604836 {

    private void onFilter(ExpandableSection expandable, CheckBoxesSection<Dependency> checkboxesSection, String cat) {
    		Filter<Dependency> filter = this.filter.getValue();
    		checkboxesSection.applyFilter(filter);
    		boolean isTrivialFilter = filter==null || filter.isTrivial();
    		if (checkboxesSection.isCreated()) {
    			boolean hasVisible = checkboxesSection.hasVisible();
    			expandable.setVisible(hasVisible);
    			if (hasVisible) {
    				expandable.getExpansionState().setValue(!isTrivialFilter);
    			}
    		}
    		layout();
    	}

}
