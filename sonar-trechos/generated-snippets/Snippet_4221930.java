@Command(name = "removenode", description = "Show status of current node removal, force completion of pending removal or remove provided ID")
public class RemoveNode extends NodeToolCmd
{
    @Arguments(title = "remove_operation", usage = "<status>|<force>|<ID>", description = "Show status of current node removal, force completion of pending removal, or remove provided ID", required = true)
    private String removeOperation = EMPTY;

    @Override
    public void execute(NodeProbe probe)
    {
        switch (removeOperation)
        {
            case "status":
                System.out.println("RemovalStatus: " + probe.getRemovalStatus(printPort));
                break;
            case "force":
                System.out.println("RemovalStatus: " + probe.getRemovalStatus(printPort));
                probe.forceRemoveCompletion();
                break;
            default:
                probe.removeNode(removeOperation);
                break;
        }
    }
}
