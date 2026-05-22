public class Snippet__4188990 {

    public void setProcedureParams(String procedureParams) {
            this.procedureParams = procedureParams;
            if (procedureParams != null) {
                getCurrentPreference().put(PROCEDURE_PARAMS_PROPERTY, procedureParams);
            }
        }

}
