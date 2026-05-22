public class Snippet__3722097 {

    private final int register_mime_types(F filter, String prefix) {
            final Iterator<String> i = filter.typesIterator();
            if (i == null) {
                return 0;
            }

            int num = 0;
            while (i.hasNext()) {
                String name = i.next();
                num++;
                String baseName = name;
                final int slashpos = name.indexOf('/');
                if (slashpos > 0) {
                    baseName = name.substring(0, slashpos).intern();
                } else {
                    name = name + "/*";
                }

                addFilter(mTypeToFilter, name, filter);

                if (slashpos > 0) {
                    addFilter(mBaseTypeToFilter, baseName, filter);
                } else {
                    addFilter(mWildTypeToFilter, baseName, filter);
                }
            }

            return num;
        }

}
