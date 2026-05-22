public class Snippet__8332859 {

    public void showSourceLine(ISourceLocation sourceLocation, boolean highlight) {
        	try {
        		showSourceLine(sourceLocation.getSourceFile().getAbsolutePath(), sourceLocation.getLine(), highlight);
        	} catch (NullPointerException npe) {
        		Ajde.getDefault().getIdeUIAdapter().displayStatusInformation(" no corresponding source line to seek to");
        	}
        }

}
