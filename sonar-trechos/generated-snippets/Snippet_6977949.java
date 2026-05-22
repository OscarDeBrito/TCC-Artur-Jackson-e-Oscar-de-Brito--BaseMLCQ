@JsType(isNative = true, name = "?", namespace = JsPackage.GLOBAL)
  public interface FooFooType {
    @JsOverlay
    static InterfaceWithStructuralTypeImpl.FooFooType create() {
      return Js.uncheckedCast(JsPropertyMap.of());
    }

    @JsProperty
    String getFoo();

    @JsProperty
    void setFoo(String foo);
  }
