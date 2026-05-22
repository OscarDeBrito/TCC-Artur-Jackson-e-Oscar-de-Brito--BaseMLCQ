public class SQLResultStage implements TranslationStage {

    @Override
    public void perform(TranslatorContext context) {
        if(context.getParentContext() != null || !context.getQuery().needsResultSetMapping()) {
            return;
        }

        // optimization, resolve metadata result components here too, as it have same logic as this extractor...
        List<Object> resultSetMapping = context.getSqlResult().getResolvedComponents(context.getResolver());
        context.getMetadata().setResultSetMapping(resultSetMapping);
    }
}
