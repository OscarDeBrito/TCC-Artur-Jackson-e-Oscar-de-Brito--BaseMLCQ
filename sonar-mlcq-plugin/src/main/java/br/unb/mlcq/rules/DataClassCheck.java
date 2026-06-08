package br.unb.mlcq.rules;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.Tree;

import java.util.Arrays;
import java.util.List;

@Rule(key = MlcqRulesDefinition.DATA_CLASS_RULE_KEY)
public class DataClassCheck extends IssuableSubscriptionVisitor {

    private static final double WOC_THRESHOLD = 1.0 / 3.0;

    private static final int DATA_EXPOSURE_LOWER_THRESHOLD = 3;
    private static final int DATA_EXPOSURE_HIGHER_THRESHOLD = 5;

    private static final int WMC_LOWER_THRESHOLD = 31;
    private static final int WMC_HIGHER_THRESHOLD = 47;

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return Arrays.asList(
                Tree.Kind.CLASS,
                Tree.Kind.RECORD
        );
    }

    @Override
    public void visitNode(Tree tree) {
        ClassTree classTree = (ClassTree) tree;

        int nopa = MetricsUtils.countPublicAttributes(classTree);
        int noam = MetricsUtils.countAccessorMethods(classTree);
        int dataExposure = nopa + noam;

        double woc = MetricsUtils.calculateWOC(classTree);
        int wmc = MetricsUtils.calculateWMC(classTree);

        boolean lowFunctionalWeight = woc < WOC_THRESHOLD;

        boolean moderateDataExposureWithLowComplexity =
                dataExposure > DATA_EXPOSURE_LOWER_THRESHOLD
                        && wmc < WMC_LOWER_THRESHOLD;

        boolean highDataExposureWithModerateComplexity =
                dataExposure > DATA_EXPOSURE_HIGHER_THRESHOLD
                        && wmc < WMC_HIGHER_THRESHOLD;

        if (lowFunctionalWeight
                && (moderateDataExposureWithLowComplexity
                || highDataExposureWithModerateComplexity)) {

            reportIssue(
                    classTree.simpleName(),
                    "MLCQ Data Class detected (WOC="
                            + round(woc)
                            + ", NOPA="
                            + nopa
                            + ", NOAM="
                            + noam
                            + ", NOPA+NOAM="
                            + dataExposure
                            + ", WMC="
                            + wmc
                            + ")."
            );
        }
    }

    private String round(double value) {
        return String.format(java.util.Locale.US, "%.2f", value);
    }
}
