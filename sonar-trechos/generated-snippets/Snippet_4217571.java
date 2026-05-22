public class Snippet__4217571 {

    private static String[] enumerationToArray(Enumeration<String> values) {
            ArrayList<String> list = new ArrayList<String>();

            while (values.hasMoreElements()) {
                list.add(values.nextElement());
            }

            return list.toArray(new String[list.size()]);
        }

}
