public class Snippet__6094004 {

    public void copy(VisualizationResource otherResource) {

    		// if resource is null, return
    		if (otherResource == null) {
    			return;
    		}

    		// copy from super class
    		super.copy(otherResource);

    		// Copy current values
    		// These files are not cloned
    		this.file = otherResource.file;
    		this.path = otherResource.path;

    		// Iteratively clone the entries in properties
    		// These items are cloned
    		this.properties.clear();
    		for (int i = 0; i < otherResource.properties.size(); i++) {
    			this.properties.add((VizEntry) otherResource.getProperties().get(i)
    					.clone());
    		}

    		// Copy picture
    		this.isPicture = otherResource.isPicture;

    		// Copy everything else we need
    		fileSetTitle = otherResource.fileSetTitle;
    		host = otherResource.host;
    		childrenResources.clear();
    		childrenResources = (ArrayList<IVizResource>) otherResource.childrenResources
    				.clone();
    		fileSet = Arrays.copyOf(otherResource.fileSet,
    				otherResource.fileSet.length);

    	}

}
