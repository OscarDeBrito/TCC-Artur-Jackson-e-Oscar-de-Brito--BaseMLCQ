public class Snippet__4593309 {

    public void deleteJobsRelateToMeasure(Long measureId) throws
            SchedulerException {
            List<AbstractJob> jobs = jobRepo.findByMeasureIdAndDeleted(measureId,
                false);
            if (CollectionUtils.isEmpty(jobs)) {
                LOGGER.info("Measure id {} has no related jobs.", measureId);
                return;
            }
            for (AbstractJob job : jobs) {
                JobOperator op = getJobOperator(job);
                op.delete(job);
            }
        }

}
