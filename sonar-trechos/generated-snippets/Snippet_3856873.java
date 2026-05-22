public class JarModuleFinder {
    private String pattern;

    private String filePattern;

    public JarModuleFinder(String pattern) {
        this.pattern = "file:///" + pattern;
        this.filePattern = pattern;
    }

    public JarModule[] findJarModules() {
        List<JarModule> ret = new ArrayList<>();
        URLLister lister = new FileURLLister();
        try {
            for (String org : ResolverHelper.listTokenValues(lister, pattern, "organisation")) {
                String orgPattern = IvyPatternHelper.substituteToken(pattern,
                        IvyPatternHelper.ORGANISATION_KEY, org);
                for (String module : ResolverHelper.listTokenValues(lister, orgPattern, "module")) {
                    String modPattern = IvyPatternHelper.substituteToken(orgPattern,
                            IvyPatternHelper.MODULE_KEY, module);
                    for (String rev : ResolverHelper.listTokenValues(lister, modPattern, "revision")) {
                        File jar = new File(IvyPatternHelper.substitute(filePattern, org,
                                module, rev, module, "jar", "jar"));
                        if (jar.exists()) {
                            ret.add(new JarModule(ModuleRevisionId.newInstance(org, module, rev), jar));
                        }
                    }
                }
            }

        } catch (Exception e) {
            Message.debug(e);
            // TODO: handle exception
        }
        return ret.toArray(new JarModule[ret.size()]);
    }

    public static void main(String[] args) {
        JarModule[] mods = new JarModuleFinder(
                "D:/temp/test2/ivyrep/[organisation]/[module]/[revision]/[artifact].[ext]")
                .findJarModules();
        for (JarModule mod : mods) {
            System.out.println(mod);
        }
    }
}
