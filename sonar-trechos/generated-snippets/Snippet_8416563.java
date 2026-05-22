@Generated("org.apache.camel.maven.packaging.SpringBootAutoConfigurationMojo")
@Configuration
@Conditional({ConditionalOnCamelContextAndAutoConfigurationBeans.class,
        ElsqlComponentAutoConfiguration.GroupConditions.class})
@AutoConfigureAfter(CamelAutoConfiguration.class)
@EnableConfigurationProperties({ComponentConfigurationProperties.class,
        ElsqlComponentConfiguration.class})
public class ElsqlComponentAutoConfiguration {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(ElsqlComponentAutoConfiguration.class);
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private CamelContext camelContext;
    @Autowired
    private ElsqlComponentConfiguration configuration;
    @Autowired(required = false)
    private List<ComponentCustomizer<ElsqlComponent>> customizers;

    static class GroupConditions extends GroupCondition {
        public GroupConditions() {
            super("camel.component", "camel.component.elsql");
        }
    }

    @Lazy
    @Bean(name = "elsql-component")
    @ConditionalOnMissingBean(ElsqlComponent.class)
    public ElsqlComponent configureElsqlComponent() throws Exception {
        ElsqlComponent component = new ElsqlComponent();
        component.setCamelContext(camelContext);
        Map<String, Object> parameters = new HashMap<>();
        IntrospectionSupport.getProperties(configuration, parameters, null,
                false);
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            Object value = entry.getValue();
            Class<?> paramClass = value.getClass();
            if (paramClass.getName().endsWith("NestedConfiguration")) {
                Class nestedClass = null;
                try {
                    nestedClass = (Class) paramClass.getDeclaredField(
                            "CAMEL_NESTED_CLASS").get(null);
                    HashMap<String, Object> nestedParameters = new HashMap<>();
                    IntrospectionSupport.getProperties(value, nestedParameters,
                            null, false);
                    Object nestedProperty = nestedClass.newInstance();
                    CamelPropertiesHelper.setCamelProperties(camelContext,
                            nestedProperty, nestedParameters, false);
                    entry.setValue(nestedProperty);
                } catch (NoSuchFieldException e) {
                }
            }
        }
        CamelPropertiesHelper.setCamelProperties(camelContext, component,
                parameters, false);
        if (ObjectHelper.isNotEmpty(customizers)) {
            for (ComponentCustomizer<ElsqlComponent> customizer : customizers) {
                boolean useCustomizer = (customizer instanceof HasId)
                        ? HierarchicalPropertiesEvaluator.evaluate(
                                applicationContext.getEnvironment(),
                                "camel.component.customizer",
                                "camel.component.elsql.customizer",
                                ((HasId) customizer).getId())
                        : HierarchicalPropertiesEvaluator.evaluate(
                                applicationContext.getEnvironment(),
                                "camel.component.customizer",
                                "camel.component.elsql.customizer");
                if (useCustomizer) {
                    LOGGER.debug("Configure component {}, with customizer {}",
                            component, customizer);
                    customizer.customize(component);
                }
            }
        }
        return component;
    }
}
