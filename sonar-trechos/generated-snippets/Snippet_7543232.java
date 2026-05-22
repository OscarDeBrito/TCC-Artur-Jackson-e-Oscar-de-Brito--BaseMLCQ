public class Snippet__7543232 {

    @Override
    	@ManagedOperation
    	public void removeChannelMapping(String key) {
    		Map<String, String> newChannelMappings = new ConcurrentHashMap<String, String>(this.channelMappings);
    		newChannelMappings.remove(key);
    		this.channelMappings = newChannelMappings;
    	}

}
