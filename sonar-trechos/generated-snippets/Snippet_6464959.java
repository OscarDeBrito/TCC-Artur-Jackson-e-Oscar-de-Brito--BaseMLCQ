public class Snippet__6464959 {

    @Override
    	public void reportClass(CheckError error, J9ClassPointer clazz, String prefix)
    	{
    		String prefixString = prefix == null ? "" : prefix;
		
    		if(!shouldReport(error)) {
    			return;
    		}		
		
    		out.println(String.format("  <gc check (%d): %sClass %x>", error._errorNumber, prefixString, clazz.getAddress()));
    	}

}
