public abstract class Action<A extends Action, B extends ActionConfiguration> extends Primitive<A, B> {

    protected  static final String TYPE="ACTION";

    @Nonnull
    public String getType() {
        return TYPE;
    }

    /**
     * Indicates whether this action has any output or not. If this function
     * returns true, then {@link Action#outputKeys()} and {@link Action#outputFile()} ()}
     * needs to be implemented by the concrete class.
     *
     * @return - True if the action has output that any downstream control or action
     * can consume, false otherwise.
     */
    public abstract boolean hasOutput();

    /**
     * Are the keys that the action chooses to expose to the external world that
     * can either be used to manage the flow control or be used as an input in a subsequent
     * action within the context of a flow. Each key that may be output by this
     * action would have to declared along with its return type {@link java.sql.Types}.
     *
     * If an action output key isn't white listed through this with a specific type,
     * then default type is assumed {@link java.sql.Types#VARCHAR}. All output keys
     * are nullable. If null the flow control can assume default values (which may vary
     * depending on the data type. For ex: VARCHAR(""), NUMERIC(0), DATETIME(CURRENTTIME))
     *
     * @return - Output key name and its corresponding data type. If the output key
     * doesn't conform to this data type, the flow may fail at runtime or may safely assume
     * default value. The behavior is left to the implementation of the flow compiler
     * and scheduler.
     */
    @Nullable
    public Map<String, Integer> outputKeys() {
        if (hasOutput()) {
            throw new NotImplementedException(getClass() + "::outputKeys()");
        } else {
            return null;
        }
    }

    /**
     * Returns a file name as URI that can be mapped to generic hadoop file system
     * implementation.
     *
     * @return - Fully qualified file name uri as understood by the hadoop file system.
     */
    @Nullable
    public URI outputFile() {
        if (hasOutput()) {
            throw new NotImplementedException(getClass() + "::outputFile()");
        } else {
            return null;
        }
    }
}
