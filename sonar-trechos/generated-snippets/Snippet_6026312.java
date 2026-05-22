public final class CompositeModelQuery<T> implements BuildAction<Collection<T>> {

    private static final long serialVersionUID = 1L;

    private final Class<T> modelType;

    public CompositeModelQuery(Class<T> modelType) {
        this.modelType = modelType;
    }

    @Override
    public Collection<T> execute(BuildController controller) {
        Collection<T> models = new ArrayList<>();
        collectRootModels(controller, controller.getBuildModel(), models);
        return models;
    }

    private void collectRootModels(BuildController controller, GradleBuild build, Collection<T> models) {
        models.add(controller.getModel(build.getRootProject(), this.modelType));

        for (GradleBuild includedBuild : build.getIncludedBuilds()) {
            collectRootModels(controller, includedBuild, models);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.modelType);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CompositeModelQuery<?> other = (CompositeModelQuery<?>) obj;
        return Objects.equals(this.modelType, other.modelType);
    }


}
