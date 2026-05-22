public class Snippet__7599926 {

    static JwtHeader create(Signer signer, Map<String, String> params) {
    		Map<String, String> map = new LinkedHashMap<String, String>(params);
    		map.put("alg", sigAlg(signer.algorithm()));
    		HeaderParameters p = new HeaderParameters(map);
    		return new JwtHeader(serializeParams(p), p);
    	}

}
