public class Snippet__7454233 {

    private static OptionClass getOptionClass(OptionDescriptor descriptor) {
            if (descriptor.getOptionKey() instanceof RuntimeOptionKey) {
                return OptionClass.VM;
            }
            return OptionClass.Compiler;
        }

}
