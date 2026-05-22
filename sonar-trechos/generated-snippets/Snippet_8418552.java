public class Snippet__8418552 {

    @Override
        public WorkflowDefinitionFactory getWorkflowDefinitionFactory(WorkflowType workflowType) {
            WorkflowTypeRegistrationOptions registrationOptions = configuration.getWorkflowTypeRegistrationOptions() != null
                    ? configuration.getWorkflowTypeRegistrationOptions() : new WorkflowTypeRegistrationOptions();
            DataConverter dataConverter = configuration.getDataConverter() != null
                    ? configuration.getDataConverter() : new JsonDataConverter();
            return new CamelWorkflowDefinitionFactory(swfWorkflowConsumer, workflowType, registrationOptions, dataConverter);
        }

}
