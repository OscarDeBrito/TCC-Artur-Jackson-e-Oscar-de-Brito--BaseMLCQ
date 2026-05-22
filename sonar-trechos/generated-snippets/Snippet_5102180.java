public class Snippet__5102180 {

    @Override
        protected CloseableIteration<? extends BindingSet, QueryEvaluationException> evaluateInternal(TupleExpr tupleExpr, Dataset dataset, BindingSet bindings, boolean includeInferred) throws SailException {
            // Clone the tuple expression to allow for more aggressive optimizations
            tupleExpr = tupleExpr.clone();

            if (!(tupleExpr instanceof QueryRoot)) {
                // Add a dummy root node to the tuple expressions to allow the
                // optimizers to modify the actual root node
                tupleExpr = new QueryRoot(tupleExpr);
            }

            try {
                KiWiTripleSource tripleSource = new KiWiTripleSource(this,includeInferred);
                EvaluationStrategy strategy = new EvaluationStrategyImpl(tripleSource, dataset);

                new BindingAssigner().optimize(tupleExpr, dataset, bindings);
                new ConstantOptimizer(strategy).optimize(tupleExpr, dataset, bindings);
                new CompareOptimizer().optimize(tupleExpr, dataset, bindings);
                new ConjunctiveConstraintSplitter().optimize(tupleExpr, dataset, bindings);
                new DisjunctiveConstraintOptimizer().optimize(tupleExpr, dataset, bindings);
                new SameTermFilterOptimizer().optimize(tupleExpr, dataset, bindings);
                new QueryModelNormalizer().optimize(tupleExpr, dataset, bindings);
                new QueryJoinOptimizer(new KiWiEvaluationStatistics()).optimize(tupleExpr, dataset, bindings);
                new IterativeEvaluationOptimizer().optimize(tupleExpr, dataset, bindings);
                new FilterOptimizer().optimize(tupleExpr, dataset, bindings);
                new OrderLimitOptimizer().optimize(tupleExpr, dataset, bindings);

                return strategy.evaluate(tupleExpr, EmptyBindingSet.getInstance());

            } catch (QueryEvaluationException e) {
                throw new SailException(e);
            }
        }

}
