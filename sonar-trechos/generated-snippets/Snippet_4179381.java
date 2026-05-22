public class CreateProcedureAction extends CayenneAction {

    public CreateProcedureAction(Application application) {
        super(getActionName(), application);
    }

    public static String getActionName() {
        return "Create Stored Procedure";
    }

    /**
     * Fires events when a procedure was added
     */
    static void fireProcedureEvent(Object src, ProjectController mediator, DataMap dataMap, Procedure procedure) {
        mediator.fireProcedureEvent(new ProcedureEvent(src, procedure, MapEvent.ADD));
        mediator.fireProcedureDisplayEvent(new ProcedureDisplayEvent(src, procedure, mediator.getCurrentDataMap(),
                (DataChannelDescriptor) mediator.getProject().getRootNode()));
    }

    public void performAction(ActionEvent e) {
        ProjectController mediator = getProjectController();
        DataMap map = mediator.getCurrentDataMap();

        Procedure procedure = new Procedure();
        procedure.setName(NameBuilder.builder(procedure, map).name());
        createProcedure(map, procedure);

        application.getUndoManager().addEdit(new CreateProcedureUndoableEdit(map, procedure));
    }

    public void createProcedure(DataMap map, Procedure procedure) {
        ProjectController mediator = getProjectController();
        procedure.setSchema(map.getDefaultSchema());
        procedure.setCatalog(map.getDefaultCatalog());
        map.addProcedure(procedure);
        fireProcedureEvent(this, mediator, map, procedure);
    }

    /**
     * Returns <code>true</code> if path contains a DataMap object.
     */
    public boolean enableForPath(ConfigurationNode object) {
        if (object == null) {
            return false;
        }

        return ((Procedure) object).getDataMap() != null;
    }

    public String getIconName() {
        return "icon-stored-procedure.png";
    }
}
