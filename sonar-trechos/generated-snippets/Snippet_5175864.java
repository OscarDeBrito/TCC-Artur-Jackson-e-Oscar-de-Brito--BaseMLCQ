@SuppressWarnings("serial")
public class BasicLancopeParser extends BasicParser {
	// Sample Lancope Message
	// {"message":"<131>Jul 17 15:59:01 smc-01 StealthWatch[12365]: 2014-07-17T15:58:30Z 10.40.10.254 0.0.0.0 Minor High Concern Index The host's concern index has either exceeded the CI threshold or rapidly increased. Observed 36.55M points. Policy maximum allows up to 20M points.","@version":"1","@timestamp":"2014-07-17T15:56:05.992Z","type":"syslog","host":"10.122.196.201"}

	private static final Logger _LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Override
	public void configure(Map<String, Object> parserConfig) {

	}

	@Override
	public void init() {

	}

	//@SuppressWarnings("unchecked")
	@Override
	public List<JSONObject> parse(byte[] msg) {

		JSONObject payload = null;
		List<JSONObject> messages = new ArrayList<>();
		try {
			
			String raw_message = new String(msg, "UTF-8");
			
			payload = (JSONObject) JSONValue.parse(raw_message);
			
			

			String message = payload.get("message").toString();
			String[] parts = message.split(" ");
			payload.put("ip_src_addr", parts[6]);
			payload.put("ip_dst_addr", parts[7]);

			String fixed_date = parts[5].replace('T', ' ');
			fixed_date = fixed_date.replace('Z', ' ').trim();

			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");

			Date date;

			date = formatter.parse(fixed_date);
			long timestamp = date.getTime();
			payload.put("timestamp", timestamp);

			payload.remove("@timestamp");
			payload.remove("message");
			payload.put("original_string", message);

			messages.add(payload);
			return messages;
		} catch (Exception e) {

			_LOG.error("Unable to parse message: {}", payload.toJSONString());
			return null;
		}
	}

	
}
