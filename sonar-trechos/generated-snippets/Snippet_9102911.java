@RBuiltin(name = "isatty", kind = INTERNAL, parameterNames = {"con"}, behavior = PURE)
public abstract class IsATTY extends RBuiltinNode.Arg1 {

    static {
        Casts.noCasts(IsATTY.class);
    }

    @Specialization
    @TruffleBoundary
    protected byte isATTYNonConnection(RAbstractIntVector con) {
        if (con.getLength() == 1) {
            RStringVector clazz = ClassHierarchyNode.getClassHierarchy(con);
            for (int i = 0; i < clazz.getLength(); i++) {
                if ("connection".equals(clazz.getDataAt(i))) {
                    RConnection connection = RContext.getInstance().stateRConnection.getConnection(con.getDataAt(0), false);
                    if (connection != null) {
                        return RRuntime.asLogical(connection instanceof StdConnection);
                    } else {
                        return RRuntime.LOGICAL_FALSE;
                    }
                }
            }
        }
        return RRuntime.LOGICAL_FALSE;
    }

    @Fallback
    @TruffleBoundary
    protected byte isATTYNonConnection(@SuppressWarnings("unused") Object con) {
        return RRuntime.LOGICAL_FALSE;
    }
}
