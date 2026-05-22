public class UpdatedExpressions {

    private List<List<GroovyExpression>> updatedChildren = new ArrayList<>();
    private boolean changed = false;

    public UpdatedExpressions(boolean changed, List<List<GroovyExpression>> updatedChildren) {
        this.changed = changed;
        this.updatedChildren = updatedChildren;
    }

    public List<List<GroovyExpression>> getUpdatedChildren() {
        return updatedChildren;
    }

    public boolean hasChanges() {
        return changed;
    }
}
