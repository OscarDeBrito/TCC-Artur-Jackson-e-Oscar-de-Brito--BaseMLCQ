static class Registration<T extends PipelineOptions> {
    private final Class<T> proxyClass;
    private final List<PropertyDescriptor> propertyDescriptors;

    public Registration(Class<T> proxyClass, List<PropertyDescriptor> beanInfo) {
      this.proxyClass = proxyClass;
      this.propertyDescriptors = beanInfo;
    }

    List<PropertyDescriptor> getPropertyDescriptors() {
      return propertyDescriptors;
    }

    Class<T> getProxyClass() {
      return proxyClass;
    }
  }
