public class Snippet__7539853 {

    public static int callSetAndGetInt(Field thiz, Object obj) throws IllegalArgumentException, IllegalAccessException {
    		thiz.setInt(obj, thiz.getInt(obj) + 1);
    		return thiz.getInt(obj);
    	}

}
