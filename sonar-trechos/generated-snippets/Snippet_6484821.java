public class StackRoots 
{

	private ArrayList<J9ObjectPointer> _allStackRoots = new ArrayList<J9ObjectPointer>();
	private ArrayList<VoidPointer> _allAddresses = new ArrayList<VoidPointer>();
	private static StackRoots _singleton;

	private class StackWalkerCallbacks implements IStackWalkerCallbacks
	{
		public FrameCallbackResult frameWalkFunction(J9VMThreadPointer walkThread, WalkState walkState)
		{
			return FrameCallbackResult.KEEP_ITERATING;
		}
	
		public void objectSlotWalkFunction(J9VMThreadPointer walkThread, WalkState walkState, PointerPointer objectSlot, VoidPointer stackAddress)
		{
			if (walkState.method.isNull()){
				/* adding an object slot iterator causes us to be called for
				 * xxx methods. These were previously ignored, since the frame
				 *does not have a valid method. We should continue to do so now.
				 */
				return;
			}
			
			try {
				J9ObjectPointer object = J9ObjectPointer.cast(objectSlot.at(0));
				if (object.notNull()) {
					_allStackRoots.add(object);
					_allAddresses.add(VoidPointer.cast(objectSlot));
				}
			} catch (CorruptDataException e) {
				throw new UnsupportedOperationException("Corrupt objectSlot detected");
			}
		}
		
	
		public void fieldSlotWalkFunction(J9VMThreadPointer walkThread,
				WalkState walkState, ObjectReferencePointer objectSlot,
				VoidPointer stackLocation)
		{
			if (walkState.method.isNull()){
				/* adding an object slot iterator causes us to be called for
				 * xxx methods. These were previously ignored, since the frame
				 *does not have a valid method. We should continue to do so now.
				 */
				return;
			}
			
			try {
				J9ObjectPointer object = objectSlot.at(0);
				if (object.notNull()) {
					_allStackRoots.add(object);
					_allAddresses.add(VoidPointer.cast(objectSlot));
				}
			} catch (CorruptDataException e) {
				throw new UnsupportedOperationException("Corrupt objectSlot detected");
			}
	
		}
	
	}
	
	private StackRoots() throws CorruptDataException
	{
		_allStackRoots = new ArrayList<J9ObjectPointer>();
		_allAddresses = new ArrayList<VoidPointer>();
		walkStacks();
	}
	
	public static StackRoots from() throws CorruptDataException 
	{
		if (null != _singleton) {
			return _singleton;
		}

		_singleton = new StackRoots();
		return _singleton;
	}

	
	private void walkStacks() throws CorruptDataException
	{		
		GCVMThreadListIterator threadIterator = GCVMThreadListIterator.from();

		while (threadIterator.hasNext()) {
			J9VMThreadPointer next = threadIterator.next();
			
			WalkState walkState = new WalkState();
			walkState.walkThread = next;
			walkState.flags = J9_STACKWALK_SKIP_INLINES | J9_STACKWALK_ITERATE_O_SLOTS | J9_STACKWALK_ITERATE_METHOD_CLASS_SLOTS;
			

			walkState.callBacks = new StackWalkerCallbacks(); 		
			StackWalkResult result = StackWalkResult.STACK_CORRUPT;
			result = StackWalker.walkStackFrames(walkState);
			
			if (StackWalkResult.NONE != result) {
				throw new UnsupportedOperationException("Failed to walk stack");
			}						
		}
	}
	
	public static ArrayList<J9ObjectPointer> allRoots() throws CorruptDataException	
	{
		StackRoots stackRoots= new StackRoots();
		return stackRoots._allStackRoots;
	}
	
	public static GCIterator stackRootIterator() throws CorruptDataException
	{
		final StackRoots stackRootSet = StackRoots.from();
		final Iterator<J9ObjectPointer> rootSetIterator = stackRootSet._allStackRoots.iterator();
		final Iterator<VoidPointer> rootSetAddressIterator = stackRootSet._allAddresses.iterator();
		
		return new GCIterator() {
			public boolean hasNext() {
				return rootSetIterator.hasNext();
			}

			public VoidPointer nextAddress() {
				rootSetIterator.next();
				return rootSetAddressIterator.next();
			}

			public Object next() {
				rootSetAddressIterator.next();
				return rootSetIterator.next(); 
			}
		};
		
	}
	
}
