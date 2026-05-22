public class Snippet__6085688 {

    @Override
        @TypeInfo("ceylon.language.meta.model::CallableConstructor<Type,Arguments>|ceylon.language::Null")
        public CallableConstructor<Type, Arguments> getDefaultConstructor() {
            if (hasConstructors() || hasEnumerated()) {
                Object ctor = getConstructor($reifiedArguments, "");
                if (ctor instanceof CallableConstructor) {
                    return ((CallableConstructor<Type, Arguments>)ctor);
                } else {
                    return null;
                }
            } else {
                return new ClassInitializerConstructor<>(this);
            }
        }

}
