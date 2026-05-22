@NodeInfo(cycles = CYCLES_2, size = SIZE_2)
public final class CEntryPointUtilityNode extends FixedWithNextNode implements Lowerable {

    public static final NodeClass<CEntryPointUtilityNode> TYPE = NodeClass.create(CEntryPointUtilityNode.class);

    /**
     * @see CurrentIsolate
     * @see CEntryPointActions
     */
    public enum UtilityAction {
        IsAttached(JavaKind.Boolean),
        FailFatally(JavaKind.Void);

        final JavaKind resultKind;

        UtilityAction(JavaKind resultKind) {
            this.resultKind = resultKind;
        }
    }

    protected final UtilityAction utilityAction;

    @OptionalInput protected ValueNode parameter0;
    @OptionalInput protected ValueNode parameter1;

    public CEntryPointUtilityNode(UtilityAction utilityAction, ValueNode parameter) {
        this(utilityAction, parameter, null);
    }

    public CEntryPointUtilityNode(UtilityAction utilityAction, ValueNode parameter0, ValueNode parameter1) {
        super(TYPE, StampFactory.forKind(utilityAction.resultKind));
        this.utilityAction = utilityAction;
        this.parameter0 = parameter0;
        this.parameter1 = parameter1;
    }

    public UtilityAction getUtilityAction() {
        return utilityAction;
    }

    public ValueNode getParameter0() {
        return parameter0;
    }

    public ValueNode getParameter1() {
        return parameter1;
    }

    @Override
    public void lower(LoweringTool tool) {
        tool.getLowerer().lower(this, tool);
    }
}
