public class TargetConfigurationTypeInfo implements ValueTypeInfo<TargetConfiguration> {
  public static final ValueTypeInfo<TargetConfiguration> INSTANCE =
      new TargetConfigurationTypeInfo();

  @Override
  public <E extends Exception> void visit(TargetConfiguration value, ValueVisitor<E> visitor)
      throws E {
    visitor.visitTargetConfiguration(value);
  }

  @Override
  public <E extends Exception> TargetConfiguration create(ValueCreator<E> creator) throws E {
    return creator.createTargetConfiguration();
  }
}
