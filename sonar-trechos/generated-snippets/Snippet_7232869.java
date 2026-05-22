public class Snippet__7232869 {

    public Snippet__7232869(
          RobolectricModel model,
          ProcessingEnvironment environment,
          String shadowPackage,
          boolean shouldInstrumentPackages,
          int priority) {
        this.messager = environment.getMessager();
        this.filer = environment.getFiler();
        this.model = model;
        this.shadowPackage = shadowPackage;
        this.shouldInstrumentPackages = shouldInstrumentPackages;
        this.priority = priority;
      }

}
