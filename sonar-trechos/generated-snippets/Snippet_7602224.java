public class Snippet__7602224 {

    public void moveConfigDown(IBeansConfig config) {
    		String configName = config.getElementName();
    		if (configNames.contains(configName)) {
    			ArrayList<String> newConfigNames = new ArrayList<String>(
    					configNames);
    			int index = newConfigNames.indexOf(configName);
    			if (index < (configNames.size() - 1)) {
    				newConfigNames.remove(configName);
    				newConfigNames.add(index + 1, configName);
    				configNames.clear();
    				configNames.addAll(newConfigNames);
    				notifyListeners();
    			}
    		}
    	}

}
