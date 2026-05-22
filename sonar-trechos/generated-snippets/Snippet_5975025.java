public static class LockHandler extends ConcurrentMethodHandler implements AnnotationHandler<Lock> {

            public LockHandler(final AssemblyDescriptor assemblyDescriptor,
                               final SessionBean bean) {
                this(assemblyDescriptor, bean, new HashMap<Object, ContainerConcurrency>());
            }

            public LockHandler(final AssemblyDescriptor assemblyDescriptor,
                               final SessionBean bean,
                               final Map<Object, ContainerConcurrency> methods) {
                super(assemblyDescriptor, bean, methods);
            }

            public void addClassLevelDeclaration(final Lock attribute, final Class type) {
                final ContainerConcurrency concurrency = getContainerConcurrency(type);
                concurrency.setLock(toLock(attribute));
            }

            public void addMethodLevelDeclaration(final Lock attribute, final Method method) {
                final ContainerConcurrency concurrency = getContainerConcurrency(method);
                concurrency.setLock(toLock(attribute));
            }

            private ConcurrentLockType toLock(final Lock annotation) {
                if (LockType.READ.equals(annotation.value())) {
                    return ConcurrentLockType.READ;
                } else if (LockType.WRITE.equals(annotation.value())) {
                    return ConcurrentLockType.WRITE;
                } else {
                    throw new IllegalArgumentException("Unknown lock annotation: " + annotation.value());
                }
            }

            public Class<Lock> getAnnotationClass() {
                return Lock.class;
            }

        }
