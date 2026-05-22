public class Snippet__7540579 {

    public static int run() {
            List<Integer> integers = Arrays.asList(1, 2, 3, 4);
            List<Integer> mapped = integers.stream().map(n -> n).collect(Collectors.toList());
            return mapped.size();
        }

}
