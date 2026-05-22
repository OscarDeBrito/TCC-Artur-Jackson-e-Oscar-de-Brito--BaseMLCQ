public class Snippet__7540312 {

    public String run() throws Exception {
    		Method m = Class.class.getMethod("getDeclaredMethods");
    		Method[] m2 = (Method[]) m.invoke(JLCGetDecMethods.class);
    		//		Method m2 = (Method) m.invoke(JLCGetDecMethod.class, "foo", new Class[] { String.class, Integer.TYPE });
    		return format(m2);
    	}

}
