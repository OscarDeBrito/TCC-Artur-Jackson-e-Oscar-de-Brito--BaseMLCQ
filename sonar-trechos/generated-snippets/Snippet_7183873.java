public interface Modification {

  void accept(QueryExpressionVisitor v);

  ModificationType getModificationType();
}
