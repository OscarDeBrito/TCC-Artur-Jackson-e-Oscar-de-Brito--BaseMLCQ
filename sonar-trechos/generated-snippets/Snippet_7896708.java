public class Snippet__7896708 {

    public static Rop opInvokeDirect(Prototype meth) {
            return new Rop(RegOps.INVOKE_DIRECT,
                           meth.getParameterFrameTypes(),
                           StdTypeList.THROWABLE);
        }

}
