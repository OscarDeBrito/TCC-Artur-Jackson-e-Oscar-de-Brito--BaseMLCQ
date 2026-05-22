public class IgnoreCaseNamespacesTestLanguageBaseColorSettingsPage extends AbstractColorSettingsPage {
	
	public IgnoreCaseNamespacesTestLanguageBaseColorSettingsPage() {
		IgnoreCaseNamespacesTestLanguageLanguage.INSTANCE.injectMembers(this);
	}

	@Override
	public String getDisplayName() {
		return IgnoreCaseNamespacesTestLanguageLanguage.INSTANCE.getDisplayName();
	}
}
