@SuppressWarnings("serial")
public abstract class AbstractName extends CompositeName {
    public AbstractName(String name) {
        super(split(name));
    }

    protected static Enumeration<String> split(String name) {
        List<String> elements = new ArrayList<>();

        StringBuilder builder = new StringBuilder();

        int len = name.length();
        int count = 0;

        for (int i = 0; i < len; i++) {
            char c = name.charAt(i);

            if (c == '/' && count == 0) {
                elements.add(builder.toString());
                builder = new StringBuilder();
                continue;
            } else if (c == '(') count++;
            else if (c == ')') count++;

            builder.append(c);
        }

        elements.add(builder.toString());

        return Collections.enumeration(elements);
    }

    public String getScheme() {
        String part0 = get(0);
        int index = part0.indexOf(':');
        if (index > 0) {
            return part0.substring(0, index);
        } else {
            return null;
        }
    }

    public String getSchemePath() {
        String part0 = get(0);
        int index = part0.indexOf(':');

        String result;

        if (index > 0) {
            result = part0.substring(index + 1);
        } else {
            result = null;
        }

        return result;
    }
}
