public class Snippet__7787982 {

    public static Behavior findBehavior(Component component, Class<? extends Behavior> behaviorClass)
    	{
    		for (Behavior behavior : component.getBehaviors(behaviorClass))
    		{
    			return behavior;
    		}
    		return null;
    	}

}
