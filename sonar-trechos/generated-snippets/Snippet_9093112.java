public class Snippet__9093112 {

    @Specialization
            protected Object callNamedFunction(VirtualFrame frame, String symbol, RArgsValuesAndNames args, @SuppressWarnings("unused") RMissing packageName,
                            @Cached("createRegisteredNativeSymbol(CallNST)") DLL.RegisteredNativeSymbol rns,
                            @Cached("create()") DLL.RFindSymbolNode findSymbolNode,
                            @Cached("createBinaryProfile()") ConditionProfile registeredProfile) {
                return callNamedFunctionWithPackage(frame, symbol, args, null, rns, findSymbolNode, registeredProfile);
            }

}
