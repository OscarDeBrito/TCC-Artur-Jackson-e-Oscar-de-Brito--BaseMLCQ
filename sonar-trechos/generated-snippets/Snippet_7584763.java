public class Snippet__7584763 {

    @Override
    	public Transaction createTransaction(@Nullable String name, int timeout) throws NotSupportedException, SystemException {
    		if (timeout >= 0) {
    			this.transactionManager.setTransactionTimeout(timeout);
    		}
    		this.transactionManager.begin();
    		return new ManagedTransactionAdapter(this.transactionManager);
    	}

}
