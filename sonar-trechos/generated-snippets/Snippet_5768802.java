public class Snippet__5768802 {

    @Override
        public void delete(final String key) {
            ReportExec execution = find(key);
            if (execution == null) {
                return;
            }

            delete(execution);
        }

}
