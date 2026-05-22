public abstract class SubmitTransitionXCommand extends TransitionXCommand<String> {

    /**
     * The constructor for abstract class {@link SubmitTransitionXCommand}
     *
     * @param name the command name
     * @param type the command type
     * @param priority the command priority
     */
    public SubmitTransitionXCommand(String name, String type, int priority) {
        super(name, type, priority);
    }

    /**
     * The constructor for abstract class {@link SubmitTransitionXCommand}
     *
     * @param name the command name
     * @param type the command type
     * @param priority the command priority
     * @param dryrun true if dryrun is enable
     */
    public SubmitTransitionXCommand(String name, String type, int priority, boolean dryrun) {
        super(name, type, priority, dryrun);
    }

    /**
     * Submit the job
     *
     * @return the id
     * @throws CommandException thrown if unable to submit
     */
    protected abstract String submit() throws CommandException;

    @Override
    public void transitToNext() {
        if (job == null) {
            job = this.getJob();
        }
        job.setStatus(Job.Status.PREP);
        job.resetPending();
    }

    @Override
    protected String execute() throws CommandException {
        try {
            transitToNext();
            String jobId = submit();
            return jobId;
        }
        finally {
            notifyParent();
        }
    }
}
