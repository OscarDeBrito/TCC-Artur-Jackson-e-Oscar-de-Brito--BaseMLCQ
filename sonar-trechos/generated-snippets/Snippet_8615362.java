public class Snippet__8615362 {

    public HadoopTaskOutput output(HadoopTaskContext taskCtx) throws IgniteCheckedException {
            switch (taskCtx.taskInfo().type()) {
                case MAP:
                    assert !job.info().hasCombiner() : "The output creation is allowed if combiner has not been defined.";

                case COMBINE:
                    return new PartitionedOutput(taskCtx);

                default:
                    throw new IllegalStateException("Illegal type: " + taskCtx.taskInfo().type());
            }
        }

}
