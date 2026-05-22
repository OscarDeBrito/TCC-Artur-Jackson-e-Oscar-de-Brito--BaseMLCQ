public class SpringMetadata implements BeanMetadata {
        private final String beanName;

        public SpringMetadata(String beanName) {
            this.beanName = beanName;
        }

        public BeanDefinition getDefinition() {
            return getBeanDefinition(beanName);
        }

        @Override
        public String getId() {
            return beanName;
        }

        @Override
        public String getScope() {
            return getDefinition().isSingleton() ? SCOPE_SINGLETON : SCOPE_PROTOTYPE;
        }

        @Override
        public int getActivation() {
            return getDefinition().isLazyInit() ? ACTIVATION_LAZY : ACTIVATION_EAGER;
        }

        @Override
        public List<String> getDependsOn() {
            String[] dependson = getDefinition().getDependsOn();
            return dependson != null ? Arrays.asList(dependson) : Collections.<String>emptyList();
        }

        @Override
        public String getClassName() {
            return null;
        }

        @Override
        public String getInitMethod() {
            return null;
        }

        @Override
        public String getDestroyMethod() {
            return null;
        }

        @Override
        public List<BeanArgument> getArguments() {
            return Collections.<BeanArgument>singletonList(new BeanArgument() {
                @Override
                public Metadata getValue() {
                    return new ValueMetadata() {
                        @Override
                        public String getStringValue() {
                            return beanName;
                        }
                        @Override
                        public String getType() {
                            return null;
                        }
                    };
                }
                @Override
                public String getValueType() {
                    return null;
                }
                @Override
                public int getIndex() {
                    return -1;
                }
            });
        }

        @Override
        public List<BeanProperty> getProperties() {
            return Collections.emptyList();
        }

        @Override
        public String getFactoryMethod() {
            return "getBean";
        }

        @Override
        public Target getFactoryComponent() {
            return new RefMetadata() {
                @Override
                public String getComponentId() {
                    return BlueprintNamespaceHandler.SPRING_BEAN_FACTORY_ID;
                }
            };
        }
    }
