public class Snippet__7496080 {

    @Override
    	public byte[] dump(byte[] key) {

    		Assert.notNull(key, "Key must not be null!");

    		return connection.getClusterCommandExecutor()
    				.executeCommandOnSingleNode((JedisClusterCommandCallback<byte[]>) client -> client.dump(key),
    						connection.getTopologyProvider().getTopology().getKeyServingMasterNode(key))
    				.getValue();
    	}

}
