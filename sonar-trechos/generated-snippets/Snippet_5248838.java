public class CoordJobsXCommand extends CoordinatorXCommand<CoordinatorJobInfo> {
    private Map<String, List<String>> filter;
    private int start = 1;
    private int len = 50;

    public CoordJobsXCommand(Map<String, List<String>> filter, int start, int length) {
        super("coord.job.info", "coord.job.info", 1);
        this.filter = filter;
        this.start = start;
        this.len = length;
    }

    @Override
    protected boolean isLockRequired() {
        return false;
    }

    @Override
    public String getEntityKey() {
        return null;
    }

    @Override
    protected void loadState() throws CommandException {
    }

    @Override
    protected void verifyPrecondition() throws CommandException, PreconditionException {
    }

    @Override
    protected CoordinatorJobInfo execute() throws CommandException {
        try {
            JPAService jpaService = Services.get().get(JPAService.class);
            CoordinatorJobInfo coordInfo = null;
            if (jpaService != null) {
                coordInfo = jpaService.execute(new CoordJobInfoGetJPAExecutor(filter, start, len));
            }
            else {
                LOG.error(ErrorCode.E0610);
            }
            return coordInfo;
        }
        catch (XException ex) {
            throw new CommandException(ex);
        }
    }

}
