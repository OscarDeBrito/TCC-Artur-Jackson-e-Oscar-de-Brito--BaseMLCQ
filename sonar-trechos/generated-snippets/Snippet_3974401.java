public class NotEmptyIterable implements IValueValidator<Iterable<?>> {
  @Override
  public void validate(String name, Iterable<?> value) throws ParameterException {
    if (Iterables.isEmpty(value)) {
      throw new ParameterException(String.format("%s must not be empty", name));
    }
  }
}
