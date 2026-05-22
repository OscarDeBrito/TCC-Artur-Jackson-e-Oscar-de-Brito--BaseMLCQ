public class Snippet__7907397 {

    @Override
      public Optional<JavacOptionsProvider> createToolchain(
          ToolchainProvider toolchainProvider, ToolchainCreationContext context) {
        JavaBuckConfig javaConfig = context.getBuckConfig().getView(JavaBuckConfig.class);
        JavacOptions defaultJavacOptions = javaConfig.getDefaultJavacOptions();

        return Optional.of(JavacOptionsProvider.of(defaultJavacOptions));
      }

}
