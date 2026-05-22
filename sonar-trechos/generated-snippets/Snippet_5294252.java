public class Snippet__5294252 {

    protected Snippet__5294252(InputFile file) {
          this.readSupport = null;
          this.file = checkNotNull(file, "file");
          this.path = null;
          if (file instanceof HadoopInputFile) {
            this.conf = ((HadoopInputFile) file).getConfiguration();
          } else {
            this.conf = new Configuration();
          }
          optionsBuilder = HadoopReadOptions.builder(conf);
        }

}
