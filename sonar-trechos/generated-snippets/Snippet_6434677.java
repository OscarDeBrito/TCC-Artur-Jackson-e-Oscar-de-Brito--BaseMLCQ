public abstract class GetRejectedListMethod extends AbstractMethodInvocationHandler {
    public static final Argument CERTIFICATES = new Argument(
        "Certificates",
        NodeId.parse("ns=0;i=15"),
        ValueRanks.OneDimension,
        new UInteger[]{Unsigned.uint(0)},
        new LocalizedText("", "")
    );

    public GetRejectedListMethod(UaMethodNode node) {
        super(node);
    }

    @Override
    public Argument[] getInputArguments() {
        return new Argument[]{};
    }

    @Override
    public Argument[] getOutputArguments() {
        return new Argument[]{CERTIFICATES};
    }

    @Override
    protected Variant[] invoke(InvocationContext context,
                               Variant[] inputValues) throws UaException {
        Out<ByteString[]> certificates = new Out<ByteString[]>();
        invoke(context, certificates);
        return new Variant[]{new Variant(certificates.get())};
    }

    protected abstract void invoke(InvocationContext context,
                                   Out<ByteString[]> certificates) throws UaException;
}
