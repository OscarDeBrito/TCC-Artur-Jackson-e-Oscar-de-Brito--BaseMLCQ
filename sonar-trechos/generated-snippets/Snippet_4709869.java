public class SQLPredicateVisitor implements PredicateVisitor {

  /**
   * The string builder.
   */
  private final StringBuilder stringBuilder = new StringBuilder();


  // ----- PredicateVisitor --------------------------------------------------

  @Override
  public void acceptComparisonPredicate(ComparisonPredicate predicate) {
    String propertyId = predicate.getPropertyId();

    String propertyCategory = PropertyHelper.getPropertyCategory(propertyId);
    if (propertyCategory != null) {
      stringBuilder.append(propertyCategory).append(".");
    }
    stringBuilder.append(PropertyHelper.getPropertyName(propertyId));

    stringBuilder.append(" ").append(predicate.getOperator()).append(" \"");
    stringBuilder.append(predicate.getValue());
    stringBuilder.append("\"");

  }

  @Override
  public void acceptArrayPredicate(ArrayPredicate predicate) {
    Predicate[] predicates = predicate.getPredicates();
    if (predicates.length > 0) {

      stringBuilder.append("(");
      for (int i = 0; i < predicates.length; i++) {
        if (i > 0) {
          stringBuilder.append(" ").append(predicate.getOperator()).append(" ");
        }
        PredicateHelper.visit(predicates[i], this);
      }
      stringBuilder.append(")");
    }
  }

  @Override
  public void acceptUnaryPredicate(UnaryPredicate predicate) {
    stringBuilder.append(predicate.getOperator()).append("(");
    PredicateHelper.visit(predicate.getPredicate(), this);
    stringBuilder.append(")");
  }

  @Override
  public void acceptAlwaysPredicate(AlwaysPredicate predicate) {
    stringBuilder.append("TRUE");
  }

  @Override
  public void acceptCategoryPredicate(CategoryPredicate predicate) {
    // Do nothing
  }


  // ----- SQLPredicateVisitor -----------------------------------------------

  public String getSQL() {
    return stringBuilder.toString();
  }
}
