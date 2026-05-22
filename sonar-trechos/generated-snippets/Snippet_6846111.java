public class Snippet__6846111 {

    @Override
    	public void beginTask(String name, int totalWork) {
    		monitor.beginTask(name, totalWork);
    		fireMsgString(name);
    	}

}
