package br.unb.mlcq.rules;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree;

import java.util.Collections;
import java.util.List;

@Rule(key = MlcqRulesDefinition.LONG_METHOD_RULE_KEY)
public class LongMethodCheck extends IssuableSubscriptionVisitor {

    private static final int LOC_THRESHOLD = 79;
    private static final int CYCLO_THRESHOLD = 9;

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return Collections.singletonList(Tree.Kind.METHOD);
    }

    @Override
    public void visitNode(Tree tree) {
        MethodTree methodTree = (MethodTree) tree;

        if (methodTree.block() == null) {
            return;
        }

        int loc = calculateLOC(methodTree);
        int cyclo = MetricsUtils.calculateCyclomaticComplexity(methodTree);

        if (loc >= LOC_THRESHOLD && cyclo >= CYCLO_THRESHOLD) {
            reportIssue(
                    methodTree.simpleName(),
                    "MLCQ Long Method detected (LOC="
                            + loc
                            + ", CYCLO="
                            + cyclo
                            + ", rule: LOC >= "
                            + LOC_THRESHOLD
                            + " and CYCLO >= "
                            + CYCLO_THRESHOLD
                            + ")."
            );
        }
    }

    private int calculateLOC(MethodTree methodTree) {
        int startLine = methodTree.firstToken().line();
        int endLine = methodTree.lastToken().line();

        return endLine - startLine + 1;
    }
}
