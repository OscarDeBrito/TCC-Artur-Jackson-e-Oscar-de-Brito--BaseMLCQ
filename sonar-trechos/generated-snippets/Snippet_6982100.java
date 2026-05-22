public class Tester929 {
  @SuppressWarnings("unchecked")
  static class C1 {
    C1() {}
    @SuppressWarnings("MissingOverride")
    public String get(Object value) {
      return "C1.get";
    }
  }

  @SuppressWarnings("unchecked")
  public static void test() {
    C1 s = new C1();
    assertTrue(s.get(new Object()).equals("C1.get"));
  }
}
