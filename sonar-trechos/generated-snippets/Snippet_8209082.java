@SuppressWarnings("unused")
public class LinkingStaticTypeEquallyNamed {

	protected static String protectedField;
	
	public static String publicField;
	
	public static void fieldOverloadsMethod() {}
	public static void withArgument(Object o) {}
	public static void getWithArgument2(Object o) {}
	
	public static void withArgument(Object o, int i, int j) {}
	public static void fieldOverloadsMethod(int i, int j) {}
	
	protected static String getProtectedField() {
		return protectedField;
	}
	public static String getPublicField() {
		return publicField;
	}
}
