@Slf4j
public class ConnectorDocGenerator {

    private static final String INDENT = "  ";

    private static Reflections newReflections() throws Exception {
        List<URL> urls = new ArrayList<>();
        ClassLoader[] classLoaders = new ClassLoader[] {
            ConnectorDocGenerator.class.getClassLoader(),
            Thread.currentThread().getContextClassLoader()
        };
        for (int i = 0; i < classLoaders.length; i++) {
            if (classLoaders[i] instanceof URLClassLoader) {
                urls.addAll(Arrays.asList(((URLClassLoader) classLoaders[i]).getURLs()));
            } else {
                throw new RuntimeException("ClassLoader '" + classLoaders[i] + " is not an instance of URLClassLoader");
            }
        }
        ConfigurationBuilder confBuilder = new ConfigurationBuilder();
        confBuilder.setUrls(urls);
        return new Reflections(confBuilder);
    }

    private final Reflections reflections;

    public ConnectorDocGenerator() throws Exception {
        this.reflections = newReflections();
    }

    private void generateConnectorYaml(Class configClass, PrintWriter writer) {
        log.info("Processing connector config class : {}", configClass);

        writer.println("configs:");

        Field[] fields = configClass.getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            FieldDoc fieldDoc = field.getDeclaredAnnotation(FieldDoc.class);
            if (null == fieldDoc) {
                throw new RuntimeException("Missing `FieldDoc` for field '" + field.getName() + "'");
            }
            writer.println(INDENT + "# " + fieldDoc.help());
            String fieldPrefix = "";
            if (!fieldDoc.required()) {
                fieldPrefix = "# ";
            }
            if (Strings.isNullOrEmpty(fieldDoc.defaultValue())) {
                writer.println(INDENT + fieldPrefix + field.getName() + ":");
            } else {
                writer.println(INDENT + fieldPrefix + field.getName() + ": " + fieldDoc.defaultValue());
            }
            writer.println();
        }
        writer.flush();
    }

    private void generateConnectorYaml(Class connectorClass, Connector connectorDef, PrintWriter writer) {
        log.info("Processing connector definition : {}", connectorDef);
        writer.println("# " + connectorDef.type() + " connector : " + connectorClass.getName());
        writer.println();
        writer.println("# " + connectorDef.help());
        writer.println();
        generateConnectorYaml(connectorDef.configClass(), writer);
    }

    private void generatorConnectorYamls(String outputDir) throws IOException  {
        Set<Class<?>> connectorClasses = reflections.getTypesAnnotatedWith(Connector.class);
        log.info("Retrieve all `Connector` annotated classes : {}", connectorClasses);

        for (Class<?> connectorClass : connectorClasses) {
            Connector connectorDef = connectorClass.getDeclaredAnnotation(Connector.class);
            try (FileWriter fileWriter = new FileWriter(
                Paths.get(
                    outputDir,
                    "pulsar-io-" + connectorDef.name()
                        + "-" + connectorDef.type().name().toLowerCase()).toString() + ".yml")) {
                PrintWriter pw = new PrintWriter(fileWriter);
                generateConnectorYaml(connectorClass, connectorDef, pw);
                pw.flush();
            }
        }
    }

    /**
     * Args for stats generator.
     */
    private static class MainArgs {

        @Parameter(
            names = {
                "-o", "--output-dir"
            },
            description = "The output dir to dump connector docs",
            required = true
        )
        String outputDir = null;

        @Parameter(
            names = {
                "-h", "--help"
            },
            description = "Show this help message")
        boolean help = false;

    }

    public static void main(String[] args) throws Exception {
        MainArgs mainArgs = new MainArgs();

        JCommander commander = new JCommander();
        try {
            commander.setProgramName("connector-doc-gen");
            commander.addObject(mainArgs);
            commander.parse(args);
            if (mainArgs.help) {
                commander.usage();
                Runtime.getRuntime().exit(0);
                return;
            }
        } catch (Exception e) {
            commander.usage();
            Runtime.getRuntime().exit(-1);
            return;
        }

        ConnectorDocGenerator docGen = new ConnectorDocGenerator();
        docGen.generatorConnectorYamls(mainArgs.outputDir);
    }

}
