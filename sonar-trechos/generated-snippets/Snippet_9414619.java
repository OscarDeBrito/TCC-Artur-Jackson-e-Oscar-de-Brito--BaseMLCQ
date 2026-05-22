public class Snippet__9414619 {

    private static void relinkComposableInvoker(final CallSite cs, final CompiledFunction inv, final boolean constructor) {
            final HandleAndAssumptions handleAndAssumptions = inv.getValidOptimisticInvocation(new Supplier<MethodHandle>() {
                @Override
                public MethodHandle get() {
                    return inv.getInvokerOrConstructor(constructor);
                }
            });
            final MethodHandle handle = handleAndAssumptions.handle;
            final SwitchPoint assumptions = handleAndAssumptions.assumptions;
            final MethodHandle target;
            if(assumptions == null) {
                target = handle;
            } else {
                final MethodHandle relink = MethodHandles.insertArguments(RELINK_COMPOSABLE_INVOKER, 0, cs, inv, constructor);
                target = assumptions.guardWithTest(handle, MethodHandles.foldArguments(cs.dynamicInvoker(), relink));
            }
            cs.setTarget(target.asType(cs.type()));
        }

}
