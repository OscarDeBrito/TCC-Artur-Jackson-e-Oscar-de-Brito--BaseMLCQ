public final class OverrideFinalProblem extends CodegenProblem
{
    // TODO: ErrorMSG maybe "Cannot override a method marked final"?
    public static final String DESCRIPTION =
        "Cannot redefine a ${FINAL} method.";

    public static final int errorCode = 1025;

    public OverrideFinalProblem(IASNode site)
    {
        super(site);
    }
    
    // Prevent these from being localized.
    public final String FINAL = "final";
}
