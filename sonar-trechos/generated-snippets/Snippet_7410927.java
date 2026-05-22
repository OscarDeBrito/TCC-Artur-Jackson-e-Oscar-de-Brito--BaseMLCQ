public class Snippet__7410927 {

    private static final class BytecodeClassLoader extends ClassLoader {

    		public BytecodeClassLoader(ClassLoader loader) {
    			super(loader);
    		}

    		public Class<?> loadClass(String name, byte[] bytecode) {
    			return defineClass(name, bytecode, 0, bytecode.length);
    		}
    	}

}
