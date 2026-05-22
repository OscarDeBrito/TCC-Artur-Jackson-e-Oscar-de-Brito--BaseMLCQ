public class Snippet__5262454 {

    public Snippet__5262454(String id, String appName) {
    		super(id);
    		setOutputMarkupPlaceholderTag(true);
    		add(new Label("appName", Strings.isEmpty(appName) ? "&nbsp;" : appName).setEscapeModelStrings(false));
    	}

}
