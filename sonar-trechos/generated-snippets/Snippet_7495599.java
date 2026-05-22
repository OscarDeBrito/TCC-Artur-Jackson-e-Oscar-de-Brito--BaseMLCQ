public class Snippet__7495599 {

    @Override
    	public Long zRemRangeByScore(byte[] key, double min, double max) {
    		return convertAndReturn(delegate.zRemRangeByScore(key, min, max), identityConverter);
    	}

}
