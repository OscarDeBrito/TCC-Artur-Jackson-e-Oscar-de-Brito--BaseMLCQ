public final class InnerClasses {
  private final TypeInfo outer;
  private final Map<TypeInfo, ClassData> innerClasses = new LinkedHashMap<>();
  private final Map<TypeInfo, Integer> innerClassesAccessModifiers = new LinkedHashMap<>();
  private final UniqueNameGenerator classNames = JbcSrcNameGenerators.forClassNames();

  public InnerClasses(TypeInfo outer) {
    this.outer = outer;
  }

  /** Returns all the {@link ClassData} for every InnerClass registered. */
  public ImmutableList<ClassData> getInnerClassData() {
    return ImmutableList.copyOf(innerClasses.values());
  }

  /**
   * Register the given name as an inner class with the given access modifiers.
   *
   * @return A {@link TypeInfo} with the full class name
   */
  public TypeInfo registerInnerClass(String simpleName, int accessModifiers) {
    classNames.claimName(simpleName);
    TypeInfo innerClass = outer.innerClass(simpleName);
    innerClassesAccessModifiers.put(innerClass, accessModifiers);
    return innerClass;
  }

  /**
   * Register the name (or a simpl mangling of it) as an inner class with the given access
   * modifiers.
   *
   * @return A {@link TypeInfo} with the full (possibly mangled) class name
   */
  public TypeInfo registerInnerClassWithGeneratedName(String simpleName, int accessModifiers) {
    simpleName = classNames.generateName(simpleName);
    TypeInfo innerClass = outer.innerClass(simpleName);
    innerClassesAccessModifiers.put(innerClass, accessModifiers);
    return innerClass;
  }

  /**
   * Adds the data for an inner class.
   *
   * @throws java.lang.IllegalArgumentException if the class wasn't previous registered via {@link
   *     #registerInnerClass(String, int)} or {@link #registerInnerClassWithGeneratedName(String,
   *     int)}.
   */
  public void add(ClassData classData) {
    checkRegistered(classData.type());
    innerClasses.put(classData.type(), classData);
  }

  private void checkRegistered(TypeInfo type) {
    if (!classNames.hasName(type.simpleName())) {
      throw new IllegalArgumentException(type + " wasn't registered");
    }
  }

  /**
   * Registers this factory as an inner class on the given class writer.
   *
   * <p>Registering an inner class is confusing. The inner class needs to call this and so does the
   * outer class. Confirmed by running ASMIfier. Also, failure to call visitInnerClass on both
   * classes either breaks reflective apis (like class.getSimpleName()/getEnclosingClass), or causes
   * verifier errors (like IncompatibleClassChangeError).
   */
  public void registerAsInnerClass(ClassVisitor visitor, TypeInfo innerClass) {
    checkRegistered(innerClass);
    doRegister(visitor, innerClass);
  }

  /** Registers all inner classes to the given outer class. */
  public void registerAllInnerClasses(ClassVisitor visitor) {
    for (Map.Entry<TypeInfo, Integer> entry : innerClassesAccessModifiers.entrySet()) {
      TypeInfo innerClass = entry.getKey();
      doRegister(visitor, innerClass);
    }
  }

  private void doRegister(ClassVisitor visitor, TypeInfo innerClass) {
    visitor.visitInnerClass(
        innerClass.internalName(), 
        outer.internalName(), 
        innerClass.simpleName(),
        innerClassesAccessModifiers.get(innerClass));
  }
}
