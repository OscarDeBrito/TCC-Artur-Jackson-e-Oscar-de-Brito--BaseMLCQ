public class Snippet__7716650 {

    public static ClusterConfig parse(String file) throws IOException {
            List<String> lines = Files.readAllLines(Paths.get(file));
            lines = Linq.map(lines, ClusterConfig::removeComment);
            String contents = String.join(" ", lines);
            ClusterConfig result = IJson.gsonInstance.fromJson(contents, ClusterConfig.class);
            result.validate();
            return result;
        }

}
