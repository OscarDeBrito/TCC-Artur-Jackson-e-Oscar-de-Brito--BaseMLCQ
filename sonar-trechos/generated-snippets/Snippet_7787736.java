public class Snippet__7787736 {

    public long getTo()
    		{
    			if (getOf() == 0)
    			{
    				return 0;
    			}
    			return Math.min(getOf(), getFrom() + pageable.getItemsPerPage() - 1);
    		}

}
