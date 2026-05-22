public class FileTag extends TagSupport {

    /** The file to place into the context */
    private String name;

    /** The variable name to place the file into */
    private String var;

    // Tag interface
    //-------------------------------------------------------------------------
    public void doTag(final XMLOutput output) throws MissingAttributeException, JellyTagException {
        boolean available = false;

        if (name == null) {
            throw new MissingAttributeException("name must be specified");
        }

        if (var == null) {
            throw new MissingAttributeException("var must be specified");
        }

        File newFile = new File(name);
        getContext().setVariable(var, newFile);
    }

    /**
     * Name of the file to be placed into the context
     * @param name The fileName to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Name of the variable to contain the file
     * @param var The var to set
     */
    public void setVar(String var) {
        this.var = var;
    }

}
