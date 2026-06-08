package br.unb.mlcq.rules;

import org.sonar.api.rule.RuleStatus;
import org.sonar.api.server.rule.RulesDefinition;

public class MlcqRulesDefinition implements RulesDefinition {

    public static final String REPOSITORY_KEY = "custom-java-smells";
    public static final String REPOSITORY_NAME = "Custom Java Code Smell Rules";

    public static final String LONG_METHOD_RULE_KEY = "long-method";
    public static final String DATA_CLASS_RULE_KEY = "data-class";
    public static final String FEATURE_ENVY_RULE_KEY = "feature-envy";
    public static final String BLOB_GOD_CLASS_RULE_KEY = "blob-god-class";

    @Override
    public void define(Context context) {
        NewRepository repository = context
                .createRepository(REPOSITORY_KEY, "java")
                .setName(REPOSITORY_NAME);

        repository.createRule(LONG_METHOD_RULE_KEY)
                .setName("Long Method")
                .setHtmlDescription("Detects Long Method using LOC >= 80 and CYCLO >= 8.")
                .setStatus(RuleStatus.READY)
                .setSeverity("MAJOR");

        repository.createRule(DATA_CLASS_RULE_KEY)
                .setName("Data Class")
                .setHtmlDescription("Detects Data Class using WOC < 1/3 and (NOPA + NOAM > 5).")
                .setStatus(RuleStatus.READY)
                .setSeverity("MAJOR");

        repository.createRule(FEATURE_ENVY_RULE_KEY)
                .setName("Feature Envy")
                .setHtmlDescription("Detects Feature Envy using ATFD(m) > 5, LAA(m) < 1/3, and FDP(m) <= 5.")
                .setStatus(RuleStatus.READY)
                .setSeverity("MAJOR");

        repository.createRule(BLOB_GOD_CLASS_RULE_KEY)
                .setName("Blob/God Class")
                .setHtmlDescription("Detects Blob/God Class using WMC >= 47, ATFD > 5, and TCC < 1/3.")
                .setStatus(RuleStatus.READY)
                .setSeverity("MAJOR");

        repository.done();
    }
}
