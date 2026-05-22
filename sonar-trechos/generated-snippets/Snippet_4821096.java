public class Snippet__4821096 {

    @Override
    		public void actionPerformed(ActionEvent e) {
    			DefaultMutableTreeNode node = getSelectedNode();
    			String newValue;
    			if (example != null && example.length() > 0)
    				newValue = example;
    			else
    				newValue = NEW_VALUE;

    			@SuppressWarnings("unused")
    			DefaultMutableTreeNode added = addPojo(node, newValue, 0);
    			setStatus("Added new value.  Edit value on right.", null);
    		}

}
