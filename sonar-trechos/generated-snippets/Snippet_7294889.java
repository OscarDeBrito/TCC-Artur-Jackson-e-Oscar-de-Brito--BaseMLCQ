public class Snippet__7294889 {

    public static void schedule(Job job) {
    		job.setRule(ResourcesPlugin.getWorkspace().getRuleFactory().buildRule());
    		job.setPriority(Job.BUILD);
    		job.schedule();
    	}

}
