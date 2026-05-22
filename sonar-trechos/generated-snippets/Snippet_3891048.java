public class FieldSignatureVisitor extends BaseSignatureVisitor
{
  private Type fieldType;

  @Override
  public SignatureVisitor visitExceptionType()
  {
    throw new UnsupportedOperationException();
  }

  @Override
  public SignatureVisitor visitParameterType()
  {
    throw new UnsupportedOperationException();
  }

  @Override
  public SignatureVisitor visitReturnType()
  {
    throw new UnsupportedOperationException();
  }

  public Type getFieldType()
  {
    if (!visitingStack.isEmpty()) {
      fieldType = visitingStack.pop();
      visitingStack.push(fieldType);
      return fieldType;
    } else {
      return null;
    }
  }
}
