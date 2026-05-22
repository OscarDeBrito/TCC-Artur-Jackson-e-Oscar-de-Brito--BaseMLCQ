public class Snippet__7792356 {

    @Override
    	protected final Iterator<IModel<T>> getItemModels(long offset, long count)
    	{
    		return new ModelIterator<T>(internalGetDataProvider(), offset, count);
    	}

}
