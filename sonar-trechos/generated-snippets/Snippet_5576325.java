public class ModelImplConverter extends AbstractModelConverter {
  @Override
  public JavaType doConvert(SwaggerToClassGenerator swaggerToClassGenerator, Object model) {
    ModelImpl modelImpl = (ModelImpl) model;

    JavaType javaType = ConverterMgr.findJavaType(modelImpl.getType(), modelImpl.getFormat());
    if (javaType != null) {
      return javaType;
    }

    if (modelImpl.getReference() != null) {
      return swaggerToClassGenerator.convertRef(modelImpl.getReference());
    }

    if (modelImpl.getAdditionalProperties() != null) {
      return MapPropertyConverter.findJavaType(swaggerToClassGenerator, modelImpl.getAdditionalProperties());
    }

    if (ObjectProperty.TYPE.equals(modelImpl.getType())
        && modelImpl.getProperties() == null
        && modelImpl.getName() == null) {
      return TypeFactory.defaultInstance().constructType(Object.class);
    }

    return getOrCreateType(swaggerToClassGenerator, modelImpl);
  }

  protected JavaType getOrCreateType(SwaggerToClassGenerator swaggerToClassGenerator, ModelImpl modelImpl) {
    String clsName = ClassUtils.getClassName(findVendorExtensions(modelImpl));
    clsName = ClassUtils.correctClassName(clsName);

    return getOrCreateType(swaggerToClassGenerator, modelImpl.getProperties(), clsName);
  }

  protected JavaType getOrCreateType(SwaggerToClassGenerator swaggerToClassGenerator,
      Map<String, Property> properties,
      String clsName) {
    Class<?> cls = ClassUtils.getClassByName(swaggerToClassGenerator.getClassLoader(), clsName);
    if (cls != null) {
      return swaggerToClassGenerator.getTypeFactory().constructType(cls);
    }

    CtClass ctClass = getOrCreateCtClass(swaggerToClassGenerator, properties, clsName);
    return new CtTypeJavaType(new CtType(ctClass));
  }

  private CtClass getOrCreateCtClass(SwaggerToClassGenerator swaggerToClassGenerator, Map<String, Property> properties,
      String clsName) {
    CtClass ctClass = swaggerToClassGenerator.getClassPool().getOrNull(clsName);
    if (ctClass != null) {
      return ctClass;
    }

    // must ensure already create CtClass, otherwise recursive dependency class will create failed.
    swaggerToClassGenerator.getClassPool().makeClass(clsName);

    ClassConfig classConfig = new ClassConfig();
    classConfig.setClassName(clsName);

    if (null != properties) {
      for (Entry<String, Property> entry : properties.entrySet()) {
        JavaType propertyJavaType = swaggerToClassGenerator.convert(entry.getValue());
        classConfig.addField(entry.getKey(), propertyJavaType);
      }
    }

    return JavassistUtils.createCtClass(swaggerToClassGenerator.getClassLoader(), classConfig);
  }
}
