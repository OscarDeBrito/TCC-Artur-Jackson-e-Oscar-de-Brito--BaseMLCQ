public class Snippet__8636574 {

    private static List<Locale> getAvailableLocaleList() {
                TreeMap<String, Locale> localeMap = new TreeMap<>();
                String localesString = UtilProperties.getPropertyValue("general", "locales.available");
                if (UtilValidate.isNotEmpty(localesString)) {
                    List<String> idList = StringUtil.split(localesString, ",");
                    for (String id : idList) {
                        Locale curLocale = parseLocale(id);
                        localeMap.put(curLocale.getDisplayName(), curLocale);
                    }
                } else {
                    Locale[] locales = Locale.getAvailableLocales();
                    for (int i = 0; i < locales.length && locales[i] != null; i++) {
                        String displayName = locales[i].getDisplayName();
                        if (!displayName.isEmpty()) {
                            localeMap.put(displayName, locales[i]);
                        }
                    }
                }
                return Collections.unmodifiableList(new ArrayList<>(localeMap.values()));
            }

}
