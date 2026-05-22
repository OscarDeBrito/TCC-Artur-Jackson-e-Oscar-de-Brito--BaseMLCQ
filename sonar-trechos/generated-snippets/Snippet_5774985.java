public class Snippet__5774985 {

    @Override
        public int countExecutions(final String taskKey) {
            return getService(ReportService.class).
                    listExecutions(new ExecQuery.Builder().key(taskKey).page(1).size(1).build()).getTotalCount();
        }

}
