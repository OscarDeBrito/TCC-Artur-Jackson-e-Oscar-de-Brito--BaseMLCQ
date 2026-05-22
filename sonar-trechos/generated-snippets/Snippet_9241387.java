public final class ClassNameSourceProvider implements SourceProvider {
    public static final String TYPE = "class";
    private final ClassLoader classLoader;

    public ClassNameSourceProvider(FileSupport fileSupport) {
        String classPath = System.getProperty("java.class.path");
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        if (classPath != null && !classPath.isEmpty()) {
            classLoader = systemClassLoader;
        } else {
            Path path = Paths.get(".").toAbsolutePath();
            classLoader = fileSupport.createClassLoader(path, systemClassLoader);
        }
    }

    @Override
    public ClassSource findSource(String name0, SearchPath searchPath) {
        String name = name0;
        Path path = Paths.get(name);
        if (ClassSource.pathIsClassFile(path)) {
            name = ClassSource.makeClassName(path);
        }
        try {
            classLoader.loadClass(name);
            return new ClassNameSource(name, classLoader);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    @Override
    public boolean supports(String type) {
        return TYPE.equals(type);
    }
}
