public class Snippet__5871338 {

    public void initializeDefaultPreferences() {
    		IPreferenceStore store = CasEditorIdePlugin.getDefault().getPreferenceStore();
    		store.setDefault(CasEditorIdePreferenceConstants.CAS_IMPORT_WIZARD_LAST_USED_LANG,
    		        CAS.DEFAULT_LANGUAGE_NAME);
		
    		store.setDefault(CasEditorIdePreferenceConstants.CAS_IMPORT_WIZARD_LAST_USED_ENCODINGS,
    		        Charset.defaultCharset().displayName());
		
        store.setDefault(CasEditorIdePreferenceConstants.CAS_EDITOR_REMEMBER_TYPESYSTEM, true);
		
    	}

}
