public class Snippet__5621210 {

    protected FileNode createFileNode(IFileSpecification specification)
        {
            // Only defer function body if the compilation unit is from an actual AS 
            // file, and the compilation unit is not "invisible" (currently not 
            // open in IDE). "isInvisible" means the compilation unit is invisible 
            // to semantic analyzer. It, however, is "visible" to the user in the 
            // IDE.
            final DeferFunctionBody deferFunctionBody;
            if(!isInvisible() && specification instanceof FileSpecification)
                deferFunctionBody = DeferFunctionBody.ENABLED;
            else
                deferFunctionBody = DeferFunctionBody.DISABLED;

            final IASProject flashProject;
            if(getProject() instanceof IASProject)
                flashProject = (IASProject)getProject();
            else
                flashProject = null;
        
            // Parse the AS file into an AST and build a symbol table for it.
            return ASParser.parseFile(
                    specification,
                    getFileSpecificationGetter(),
                    EnumSet.of(PostProcessStep.CALCULATE_OFFSETS),
                    this.getProject().getProjectConfigVariables(),
                    true,
                    this.getProject().isAssetEmbeddingSupported(),
                    includedFiles,
                    deferFunctionBody,
                    flashProject,
                    this);
        }

}
