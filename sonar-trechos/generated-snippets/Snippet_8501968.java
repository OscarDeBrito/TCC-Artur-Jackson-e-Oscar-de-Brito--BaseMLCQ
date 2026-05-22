@ResourceWrapper(handles =  OvsFetchInterfaceCommand.class)
public final class CitrixOvsFetchInterfaceCommandWrapper extends CommandWrapper<OvsFetchInterfaceCommand, Answer, CitrixResourceBase> {

    private static final Logger s_logger = Logger.getLogger(CitrixOvsFetchInterfaceCommandWrapper.class);

    @Override
    public Answer execute(final OvsFetchInterfaceCommand command, final CitrixResourceBase citrixResourceBase) {
        String label = command.getLabel();
        //FIXME: this is a tricky to pass the network checking in XCP. I temporary get default label from Host.
        if (citrixResourceBase.isXcp()) {
            label = citrixResourceBase.getLabel();
        }
        s_logger.debug("Will look for network with name-label:" + label + " on host " + citrixResourceBase.getHost().getIp());
        final Connection conn = citrixResourceBase.getConnection();
        try {
            final XsLocalNetwork nw = citrixResourceBase.getNetworkByName(conn, label);
            if(nw == null) {
                throw new CloudRuntimeException("Unable to locate the network with name-label: " + label + " on host: " + citrixResourceBase.getHost().getIp());
            }
            s_logger.debug("Network object:" + nw.getNetwork().getUuid(conn));
            final PIF pif = nw.getPif(conn);
            final PIF.Record pifRec = pif.getRecord(conn);
            s_logger.debug("PIF object:" + pifRec.uuid + "(" + pifRec.device + ")");
            return new OvsFetchInterfaceAnswer(command, true, "Interface " + pifRec.device + " retrieved successfully", pifRec.IP, pifRec.netmask, pifRec.MAC);
        } catch (final BadServerResponse e) {
            s_logger.error("An error occurred while fetching the interface for " + label + " on host " + citrixResourceBase.getHost().getIp(), e);
            return new OvsFetchInterfaceAnswer(command, false, "EXCEPTION:" + e.getMessage());
        } catch (final XenAPIException e) {
            s_logger.error("An error occurred while fetching the interface for " + label + " on host " + citrixResourceBase.getHost().getIp(), e);
            return new OvsFetchInterfaceAnswer(command, false, "EXCEPTION:" + e.getMessage());
        } catch (final XmlRpcException e) {
            s_logger.error("An error occurred while fetching the interface for " + label + " on host " + citrixResourceBase.getHost().getIp(), e);
            return new OvsFetchInterfaceAnswer(command, false, "EXCEPTION:" + e.getMessage());
        }
    }
}
