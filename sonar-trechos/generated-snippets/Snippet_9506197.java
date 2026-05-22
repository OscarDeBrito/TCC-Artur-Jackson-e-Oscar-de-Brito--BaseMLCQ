@InterfaceAudience.Public
@InterfaceStability.Stable
public class RegexMapper<K> extends Mapper<K, Text, Text, LongWritable> {

  public static String PATTERN = "mapreduce.mapper.regex";
  public static String GROUP = "mapreduce.mapper.regexmapper..group";
  private Pattern pattern;
  private int group;

  public void setup(Context context) {
    Configuration conf = context.getConfiguration();
    pattern = Pattern.compile(conf.get(PATTERN));
    group = conf.getInt(GROUP, 0);
  }

  public void map(K key, Text value,
                  Context context)
    throws IOException, InterruptedException {
    String text = value.toString();
    Matcher matcher = pattern.matcher(text);
    while (matcher.find()) {
      context.write(new Text(matcher.group(group)), new LongWritable(1));
    }
  }
}
