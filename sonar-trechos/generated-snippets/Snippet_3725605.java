public class ClassStructureImplByJDK extends FamilyClassStructure {

    private final Class<?> clazz;
    private String javaClassName;

    public ClassStructureImplByJDK(final Class<?> clazz) {
        this.clazz = clazz;
    }

    private ClassStructure newInstance(final Class<?> clazz) {
        if (null == clazz) {
            return null;
        }
        return new ClassStructureImplByJDK(clazz);
    }

    private List<ClassStructure> newInstances(final Class[] classArray) {
        final List<ClassStructure> classStructures = new ArrayList<ClassStructure>();
        if (null != classArray) {
            for (final Class<?> clazz : classArray) {
                final ClassStructure classStructure = newInstance(clazz);
                if (null != classStructure) {
                    classStructures.add(classStructure);
                }
            }
        }
        return classStructures;
    }

    @Override
    public String getJavaClassName() {
        return null != javaClassName
                ? javaClassName
                : (javaClassName = getJavaClassName(clazz));
    }

    private String getJavaClassName(Class<?> clazz) {
        if (clazz.isArray()) {
            return getJavaClassName(clazz.getComponentType()) + "[]";
        }
        return clazz.getName();
    }


    @Override
    public ClassLoader getClassLoader() {
        return clazz.getClassLoader();
    }

    @Override
    public ClassStructure getSuperClassStructure() {
        // 过滤掉Object.class
        return Object.class.equals(clazz.getSuperclass())
                ? null
                : newInstance(clazz.getSuperclass());
    }

    @Override
    public List<ClassStructure> getInterfaceClassStructures() {
        return newInstances(clazz.getInterfaces());
    }

    private Class[] getAnnotationTypeArray(final Annotation[] annotationArray) {
        final Collection<Class> annotationTypes = new ArrayList<Class>();
        for (final Annotation annotation : annotationArray) {
            if (annotation.getClass().isAnnotation()) {
                annotationTypes.add(annotation.getClass());
            }
            for (final Class annotationInterfaceClass : annotation.getClass().getInterfaces()) {
                if (annotationInterfaceClass.isAnnotation()) {
                    annotationTypes.add(annotationInterfaceClass);
                }
            }
        }
        return annotationTypes.toArray(new Class[0]);
    }

    private final LazyGet<List<ClassStructure>> annotationTypeClassStructuresLazyGet
            = new LazyGet<List<ClassStructure>>() {
        @Override
        protected List<ClassStructure> initialValue() {
            return Collections.unmodifiableList(newInstances(getAnnotationTypeArray(clazz.getDeclaredAnnotations())));
        }
    };

    @Override
    public List<ClassStructure> getAnnotationTypeClassStructures() {
        return annotationTypeClassStructuresLazyGet.get();
    }

    private BehaviorStructure newBehaviorStructure(final Method method) {
        return new BehaviorStructure(
                new AccessImplByJDKBehavior(method),
                method.getName(),
                this,
                newInstance(method.getReturnType()),
                newInstances(method.getParameterTypes()),
                newInstances(method.getExceptionTypes()),
                newInstances(getAnnotationTypeArray(method.getDeclaredAnnotations()))
        );
    }

    private BehaviorStructure newBehaviorStructure(final Constructor constructor) {
        return new BehaviorStructure(
                new AccessImplByJDKBehavior(constructor),
                "<init>",
                this,
                this,
                newInstances(constructor.getParameterTypes()),
                newInstances(constructor.getExceptionTypes()),
                newInstances(getAnnotationTypeArray(constructor.getDeclaredAnnotations()))
        );
    }

    private final LazyGet<List<BehaviorStructure>> behaviorStructuresLazyGet
            = new LazyGet<List<BehaviorStructure>>() {
        @Override
        protected List<BehaviorStructure> initialValue() {
            final List<BehaviorStructure> behaviorStructures = new ArrayList<BehaviorStructure>();
            for (final Constructor<?> constructor : clazz.getDeclaredConstructors()) {
                behaviorStructures.add(newBehaviorStructure(constructor));
            }
            for (final Method method : clazz.getDeclaredMethods()) {
                behaviorStructures.add(newBehaviorStructure(method));
            }
            return Collections.unmodifiableList(behaviorStructures);
        }
    };

    @Override
    public List<BehaviorStructure> getBehaviorStructures() {
        return behaviorStructuresLazyGet.get();
    }

    @Override
    public Access getAccess() {
        return new AccessImplByJDKClass(clazz);
    }

    @Override
    public String toString() {
        return "ClassStructureImplByJDK{" + "javaClassName='" + javaClassName + '\'' + '}';
    }
}
