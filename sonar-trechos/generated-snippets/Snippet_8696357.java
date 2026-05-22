public class Snippet__8696357 {

    public ModelMenu getModelMenu(Map<String, Object> context) {
                String name = this.getName(context);
                String location = this.getLocation(context);
                ModelMenu modelMenu = null;
                try {
                    modelMenu = MenuFactory.getMenuFromLocation(location, name);
                } catch (Exception e) {
                    String errMsg = "Error rendering included menu named [" + name + "] at location [" + location + "]: ";
                    Debug.logError(e, errMsg, module);
                    throw new RuntimeException(errMsg + e);
                }
                return modelMenu;
            }

}
