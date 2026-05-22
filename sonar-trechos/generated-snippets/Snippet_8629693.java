public class RandomForestClassifierTrainer
    extends RandomForestTrainer<ObjectHistogram<BootstrappedVector>, GiniHistogram, RandomForestClassifierTrainer> {
    /** Label mapping. */
    private Map<Double, Integer> lblMapping = new HashMap<>();

    /**
     * Constructs an instance of RandomForestClassifierTrainer.
     *
     * @param meta Features meta.
     */
    public RandomForestClassifierTrainer(List<FeatureMeta> meta) {
        super(meta);
    }

    /** {@inheritDoc} */
    @Override protected RandomForestClassifierTrainer instance() {
        return this;
    }

    /**
     * Aggregates all unique labels from dataset and assigns integer id value for each label.
     * This id can be used as index in arrays or lists.
     *
     * @param dataset Dataset.
     * @return true if initialization was done.
     */
    @Override protected boolean init(Dataset<EmptyContext, BootstrappedDatasetPartition> dataset) {
        Set<Double> uniqLabels = dataset.compute(
            x -> {
                Set<Double> labels = new HashSet<>();
                for (int i = 0; i < x.getRowsCount(); i++)
                    labels.add(x.getRow(i).label());
                return labels;
            },
            (l, r) -> {
                if (l == null)
                    return r;
                if (r == null)
                    return l;
                Set<Double> lbls = new HashSet<>();
                lbls.addAll(l);
                lbls.addAll(r);
                return lbls;
            }
        );

        if(uniqLabels == null)
            return false;

        int i = 0;
        for (Double label : uniqLabels)
            lblMapping.put(label, i++);

        return super.init(dataset);
    }

    /** {@inheritDoc} */
    @Override protected ModelsComposition buildComposition(List<TreeRoot> models) {
        return new ModelsComposition(models, new OnMajorityPredictionsAggregator());
    }

    /** {@inheritDoc} */
    @Override protected ImpurityHistogramsComputer<GiniHistogram> createImpurityHistogramsComputer() {
        return new GiniHistogramsComputer(lblMapping);
    }

    /** {@inheritDoc} */
    @Override protected LeafValuesComputer<ObjectHistogram<BootstrappedVector>> createLeafStatisticsAggregator() {
        return new ClassifierLeafValuesComputer(lblMapping);
    }

    /** {@inheritDoc} */
    @Override public RandomForestClassifierTrainer withEnvironmentBuilder(LearningEnvironmentBuilder envBuilder) {
        return (RandomForestClassifierTrainer)super.withEnvironmentBuilder(envBuilder);
    }
}
