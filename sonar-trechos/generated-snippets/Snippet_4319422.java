public class Snippet__4319422 {

    private void createTriggerDependencies
    	(
            TriggerDescriptorList       tdl,
    		Dependent					dependent
    	)
    		throws StandardException
    	{
    		CompilerContext 			compilerContext = getCompilerContext();

            for (TriggerDescriptor td : tdl) {
                /*
                ** The dependent now depends on this trigger.
                ** The default dependent is the statement being compiled.
                */
                if (dependent == null) {
                    compilerContext.createDependency(td);
                } else {
                    compilerContext.createDependency(dependent, td);
                }
            }
    	}

}
