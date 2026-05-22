public class Snippet__7795065 {

    @Override
    	protected void onReAdd()
    	{
    		if (this.fence != null)
    		{
    			// The fence mark is removed when the feedback panel is removed from the hierarchy.
    			// see onRemove().
    			// when the panel is re-added, we recreate the fence mark.
    			incrementFenceCount();
    		}
    		super.onReAdd();
    	}

}
