public final class EdmNull implements EdmPrimitiveType {

  private static final EdmNull instance = new EdmNull();

  public static EdmNull getInstance() {
    return instance;
  }

  @Override
  public String getNamespace() {
    return EDM_NAMESPACE;
  }

  @Override
  public String getName() {
    return getClass().getSimpleName().substring(3);
  }

  @Override
  public EdmTypeKind getKind() {
    return EdmTypeKind.PRIMITIVE;
  }

  @Override
  public Class<?> getDefaultType() {
    return Object.class;
  }

  @Override
  public FullQualifiedName getFullQualifiedName() {
    return new FullQualifiedName(getNamespace(), getName());
  }

  @Override
  public boolean isCompatible(final EdmPrimitiveType primitiveType) {
    return equals(primitiveType);
  }

  @Override
  public boolean validate(final String value,
      final Boolean isNullable, final Integer maxLength, final Integer precision, final Integer scale,
      final Boolean isUnicode) {
    return value == null && (isNullable == null || isNullable)
        || value.equals("null");
  }

  @Override
  public final <T> T valueOfString(final String value,
      final Boolean isNullable, final Integer maxLength, final Integer precision,
      final Integer scale, final Boolean isUnicode, final Class<T> returnType)
      throws EdmPrimitiveTypeException {
    if (value == null) {
      if (isNullable != null && !isNullable) {
        throw new EdmPrimitiveTypeException("The literal 'null' is not allowed.");
      }
      return null;
    }
    if (value.equals("null")) {
      return null;
    } else {
      throw new EdmPrimitiveTypeException("The literal '" + value + "' has illegal content.");
    }
  }

  @Override
  public final String valueToString(final Object value,
      final Boolean isNullable, final Integer maxLength, final Integer precision,
      final Integer scale, final Boolean isUnicode) throws EdmPrimitiveTypeException {
    if (value == null) {
      if (isNullable != null && !isNullable) {
        throw new EdmPrimitiveTypeException("The value NULL is not allowed.");
      }
      return null;
    }
    return "null";
  }

  @Override
  public String toUriLiteral(final String literal) {
    return literal == null ? null : literal;
  }

  @Override
  public String fromUriLiteral(final String literal) throws EdmPrimitiveTypeException {
    return literal == null ? null : literal;
  }

  @Override
  public String toString() {
    return new FullQualifiedName(getNamespace(), getName()).getFullQualifiedNameAsString();
  }

  @Override
  public boolean equals(final Object obj) {
    return this == obj || obj != null && getClass() == obj.getClass();
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
