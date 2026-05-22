public class Snippet__8785914 {

    @Override
        public void transition(JobImpl job, JobEvent event) {
          job.addDiagnostic(((JobDiagnosticsUpdateEvent) event)
              .getDiagnosticUpdate());
        }

}
