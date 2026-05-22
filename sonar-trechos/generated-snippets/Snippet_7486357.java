@ExportLibrary(value = LLVMNativeLibrary.class, receiverType = LLVMPointerImpl.class)
@ExportLibrary(value = InteropLibrary.class, receiverType = LLVMPointerImpl.class)
abstract class NativePointerLibraries extends CommonPointerLibraries {

    @ExportMessage
    static boolean isNull(LLVMPointerImpl receiver) {
        return receiver.isNull();
    }

    @ExportMessage
    @ImportStatic(LLVMLanguage.class)
    static class IsExecutable {

        @Specialization
        static boolean doNative(LLVMPointerImpl receiver,
                        @CachedContext(LLVMLanguage.class) LLVMContext context) {
            return context.getFunctionDescriptor(receiver) != null;
        }
    }

    @ExportMessage
    @ImportStatic(LLVMLanguage.class)
    static class Execute {

        @SuppressWarnings("unused")
        @Specialization(limit = "5", guards = {"value.asNative() == cachedAddress", "cachedDescriptor != null"})
        static Object doNativeCached(@SuppressWarnings("unused") LLVMPointerImpl value, Object[] args,
                        @Cached("value.asNative()") @SuppressWarnings("unused") long cachedAddress,
                        @Cached("getLLVMContextReference()") ContextReference<LLVMContext> ctxRef,
                        @Cached("getDescriptor(ctxRef, value)") LLVMFunctionDescriptor cachedDescriptor,
                        @CachedLibrary("cachedDescriptor") InteropLibrary interop) throws UnsupportedTypeException, ArityException, UnsupportedMessageException {
            return interop.execute(cachedDescriptor, args);
        }

        @Specialization(replaces = "doNativeCached")
        static Object doNative(LLVMPointerImpl value, Object[] args,
                        @CachedContext(LLVMLanguage.class) LLVMContext context,
                        @CachedLibrary(limit = "5") InteropLibrary interop) throws UnsupportedTypeException, ArityException, UnsupportedMessageException {
            LLVMFunctionDescriptor descriptor = context.getFunctionDescriptor(value);
            if (descriptor != null) {
                return interop.execute(descriptor, args);
            } else {
                throw UnsupportedMessageException.create();
            }
        }

        static LLVMFunctionDescriptor getDescriptor(ContextReference<LLVMContext> ctxRef, LLVMNativePointer value) {
            return ctxRef.get().getFunctionDescriptor(value);
        }
    }

    @ExportMessage(library = LLVMNativeLibrary.class)
    @ExportMessage(library = InteropLibrary.class)
    @SuppressWarnings("unused")
    static boolean isPointer(LLVMPointerImpl receiver) {
        return true;
    }

    @ExportMessage(library = LLVMNativeLibrary.class)
    @ExportMessage(library = InteropLibrary.class)
    static long asPointer(LLVMPointerImpl receiver) {
        return receiver.asNative();
    }

    @ExportMessage
    static LLVMNativePointer toNativePointer(LLVMPointerImpl receiver) {
        return receiver;
    }
}
