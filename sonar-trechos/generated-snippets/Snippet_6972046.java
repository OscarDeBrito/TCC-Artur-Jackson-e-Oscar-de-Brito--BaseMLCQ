public class Snippet__6972046 {

    final TestSuite createSubsetSuite(
          final FeatureSpecificTestSuiteBuilder<
                  ?, ? extends OneSizeTestContainerGenerator<Collection<E>, E>>
              parentBuilder,
          final Bound from,
          final Bound to) {
        final TestSortedSetGenerator<E> delegate =
            (TestSortedSetGenerator<E>) parentBuilder.getSubjectGenerator().getInnerGenerator();

        List<Feature<?>> features = new ArrayList<>();
        features.addAll(parentBuilder.getFeatures());
        features.remove(CollectionFeature.ALLOWS_NULL_VALUES);
        features.add(CollectionFeature.SUBSET_VIEW);

        return newBuilderUsing(delegate, to, from)
            .named(parentBuilder.getName() + " subSet " + from + "-" + to)
            .withFeatures(features)
            .suppressing(parentBuilder.getSuppressedTests())
            .createTestSuite();
      }

}
