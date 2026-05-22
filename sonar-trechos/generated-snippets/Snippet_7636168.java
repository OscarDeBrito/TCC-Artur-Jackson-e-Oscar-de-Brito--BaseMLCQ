public class PreferencesInitializer extends AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = BootActivator.getDefault().getPreferenceStore();
		
		/*
		 * Note that line below cannot be moved to BootPreferencePage static
		 * init method because the class has BooleanFieldEditor2 import that in
		 * turn activate eclipse preferences debug plugin which throws exception
		 * because workbench may not be started in the case of a unit test
		 */
		store.setDefault(PREF_IGNORE_SILENT_EXIT, DEFAULT_PREF_IGNORE_SILENT_EXIT);
		
		store.setDefault(PREF_INITIALIZR_URL, StsProperties.getInstance().get("spring.initializr.json.url"));
		
		store.setDefault(PREF_BOOT_FAST_STARTUP_DEFAULT, true);
		store.setDefault(PREF_BOOT_FAST_STARTUP_REMIND_MESSAGE, true);
		store.setDefault(PREF_BOOT_FAST_STARTUP_JVM_ARGS, BOOT_FAST_STARTUP_DEFAULT_JVM_ARGS);
	}
		

}
