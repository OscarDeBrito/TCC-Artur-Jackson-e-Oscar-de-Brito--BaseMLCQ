@ResourceWrapper(handles =  AttachOrDettachConfigDriveCommand.class)
public final class CitrixAttachOrDettachConfigDriveCommandWrapper extends CommandWrapper<AttachOrDettachConfigDriveCommand, Answer, CitrixResourceBase> {

    private static final Logger s_logger = Logger.getLogger(CitrixAttachOrDettachConfigDriveCommandWrapper.class);

    @Override
    public Answer execute(final AttachOrDettachConfigDriveCommand command, final CitrixResourceBase citrixResourceBase) {
        final Connection conn = citrixResourceBase.getConnection();

        String vmName = command.getVmName();
        List<String[]> vmData = command.getVmData();
        String label = command.getConfigDriveLabel();
        Boolean isAttach = command.isAttach();

        try {
            Set<VM> vms = VM.getByNameLabel(conn, vmName);
            for (VM vm : vms) {
                if (isAttach) {
                    if (!citrixResourceBase.createAndAttachConfigDriveIsoForVM(conn, vm, vmData, label)) {
                        s_logger.debug("Failed to attach config drive iso to VM " + vmName);
                    }
                } else {
                    // delete the config drive iso attached to VM
                    Set<VDI> vdis = VDI.getByNameLabel(conn, vmName+".iso");
                    if (vdis != null && !vdis.isEmpty()) {
                        s_logger.debug("Deleting config drive for the VM " + vmName);
                        VDI vdi = vdis.iterator().next();
                        // Find the VM's CD-ROM VBD
                        Set<VBD> vbds = vdi.getVBDs(conn);

                        for (VBD vbd : vbds) {
                            VBD.Record vbdRec = vbd.getRecord(conn);

                            if (vbdRec.type.equals(Types.VbdType.CD) && !vbdRec.empty && !vbdRec.userdevice.equals(citrixResourceBase._attachIsoDeviceNum)) {
                                if (vbdRec.currentlyAttached) {
                                    vbd.eject(conn);
                                }
                                vbd.destroy(conn);
                            }
                        }
                        vdi.destroy(conn);
                    }

                    s_logger.debug("Successfully dettached config drive iso from the VM " + vmName);
                }
            }
        }catch (Types.XenAPIException ex) {
            s_logger.debug("Failed to attach config drive iso to VM " + vmName + " " + ex.getMessage() );
        }catch (XmlRpcException ex) {
            s_logger.debug("Failed to attach config drive iso to VM " + vmName + " "+ex.getMessage());
        }

        return new Answer(command, true, "success");


    }
}
