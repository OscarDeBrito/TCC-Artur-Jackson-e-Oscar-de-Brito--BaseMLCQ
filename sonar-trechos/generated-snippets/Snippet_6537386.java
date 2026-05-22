public class Snippet__6537386 {

    private static String getInputLabel(TranslationProvider i18nProvider, Bundle bundle, String moduleTypeUID,
                String inputName, String defaultLabel, Locale locale) {
            String key = I18nUtil.stripConstantOr(defaultLabel, () -> inferInputKey(moduleTypeUID, inputName, "label"));
            return i18nProvider.getText(bundle, key, defaultLabel, locale);
        }

}
