public class Snippet__7496459 {

    @Override
    	public void killClient(String host, int port) {

    		Assert.hasText(host, "Host for 'CLIENT KILL' must not be 'null' or 'empty'.");

    		String client = String.format("%s:%s", host, port);
    		try {
    			if (isPipelined()) {
    				pipeline(connection.newLettuceStatusResult(getAsyncConnection().clientKill(client)));
    				return;
    			}
    			getConnection().clientKill(client);
    		} catch (Exception e) {
    			throw convertLettuceAccessException(e);
    		}
    	}

}
