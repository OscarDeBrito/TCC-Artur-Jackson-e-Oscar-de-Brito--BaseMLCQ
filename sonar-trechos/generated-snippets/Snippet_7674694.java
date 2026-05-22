public class Snippet__7674694 {

    private void addJobExecutionListener() {
    		// Add all job execution listeners available in the bean factory
    		// We won't have multiple batch job definitions on a given job module; hence all the job execution listeners
    		// available in the bean factory correspond to the job module's batch job.
    		Map<String, JobExecutionListener> listeners = this.beanFactory.getBeansOfType(JobExecutionListener.class);
    		this.jobExecutionListeners.addAll(listeners.values());
    	}

}
