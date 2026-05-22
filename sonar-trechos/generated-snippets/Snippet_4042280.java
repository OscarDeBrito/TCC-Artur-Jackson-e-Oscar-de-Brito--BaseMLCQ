public class Snippet__4042280 {

    @Override
        public PCollection<Message> expand(PBegin input) {

          org.apache.beam.sdk.io.Read.Unbounded<Message> unbounded =
              org.apache.beam.sdk.io.Read.from(
                  new SqsUnboundedSource(
                      this,
                      new SqsConfiguration(input.getPipeline().getOptions().as(AwsOptions.class))));

          PTransform<PBegin, PCollection<Message>> transform = unbounded;

          if (maxNumRecords() < Long.MAX_VALUE || maxReadTime() != null) {
            transform = unbounded.withMaxReadTime(maxReadTime()).withMaxNumRecords(maxNumRecords());
          }

          return input.getPipeline().apply(transform);
        }

}
