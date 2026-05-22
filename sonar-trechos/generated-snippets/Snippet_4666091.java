public class Snippet__4666091 {

    public Snippet__4666091(ResourceID resourceId, int slotNumber) {
    		checkArgument(0 <= slotNumber, "Slot number must be positive.");
    		this.resourceId = checkNotNull(resourceId, "ResourceID must not be null");
    		this.slotNumber = slotNumber;
    	}

}
