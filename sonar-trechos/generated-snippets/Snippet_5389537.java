public class Snippet__5389537 {

    public static boolean is64bitJVM() {
            String arch = System.getProperties().getProperty("sun.arch.data.model",
                    System.getProperty("com.ibm.vm.bitmode"));
            return arch != null && arch.equals("64");
        }

}
