public class Snippet__4433943 {

    public static Set<String> getAllColos() {
            if (DeploymentUtil.isEmbeddedMode()) {
                return DeploymentUtil.getDefaultColos();
            }
            String[] colos = RuntimeProperties.get().getProperty("all.colos", DeploymentUtil.getDefaultColo()).split(",");
            for (int i = 0; i < colos.length; i++) {
                colos[i] = colos[i].trim();
            }
            return new HashSet<String>(Arrays.asList(colos));
        }

}
