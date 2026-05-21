package br.unb.mlcq.rules;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.Tree;

import java.util.Collections;
import java.util.List;

@Rule(key = MlcqRulesDefinition.DATA_CLASS_RULE_KEY)
public class DataClassCheck extends IssuableSubscriptionVisitor {

    private static final double WOC_THRESHOLD = 1.0 / 3.0;
    private static final int DATA_EXPOSURE_THRESHOLD = 5;

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return Collections.singletonList(Tree.Kind.CLASS);
    }

    @Override
    public void visitNode(Tree tree) {
        ClassTree classTree = (ClassTree) tree;

        int nopa = MetricsUtils.countPublicAttributes(classTree);
        int noam = MetricsUtils.countAccessorMethods(classTree);
        double woc = MetricsUtils.calculateWOC(classTree);

        if (woc < WOC_THRESHOLD && (nopa + noam) > DATA_EXPOSURE_THRESHOLD) {
            reportIssue(
                    classTree.simpleName(),
                    "MLCQ Data Class detected (WOC="
                            + round(woc)
                            + ", NOPA="
                            + nopa
                            + ", NOAM="
                            + noam
                            + ")."
            );
        }
    }

    private String round(double value) {
        return String.format(java.util.Locale.US, "%.2f", value);
    }
}
