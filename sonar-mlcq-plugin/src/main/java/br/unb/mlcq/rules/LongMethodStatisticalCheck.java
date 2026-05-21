package br.unb.mlcq.rules;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Rule(key = MlcqRulesDefinition.LONG_METHOD_STATISTICAL_RULE_KEY)
public class LongMethodStatisticalCheck extends IssuableSubscriptionVisitor {

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return Collections.singletonList(Tree.Kind.CLASS);
    }

    @Override
    public void visitNode(Tree tree) {
        ClassTree rootClass = (ClassTree) tree;

        List<ClassLongestMethod> longestMethods = new ArrayList<>();
        collectLongestMethodPerClass(rootClass, longestMethods);

        if (longestMethods.size() < 2) {
            return;
        }

        List<Integer> values = new ArrayList<>();
        for (ClassLongestMethod item : longestMethods) {
            values.add(item.loc());
        }

        StatisticalBoxPlotUtils.BoxPlotStats stats = StatisticalBoxPlotUtils.computeStats(values);

        for (ClassLongestMethod item : longestMethods) {
            double loc = item.loc();
            if (StatisticalBoxPlotUtils.isHighValue(loc, stats)) {
                reportIssue(
                        item.methodTree().simpleName(),
                        "MLCQ Long Method Statistical detected (LOC=" + item.loc()
                                + ", Q3=" + round(stats.getQ3())
                                + ", MaxBound=" + round(stats.getMaxBound())
                                + ", fuzziness=" + round(stats.getFuzziness()) + ")."
                );
            }
        }
    }

    private void collectLongestMethodPerClass(ClassTree currentClass, List<ClassLongestMethod> output) {
        MethodTree longestMethod = null;
        int longestLoc = -1;

        for (Tree member : currentClass.members()) {
            if (member.is(Tree.Kind.METHOD)) {
                MethodTree methodTree = (MethodTree) member;

                if (methodTree.block() == null) {
                    continue;
                }

                int startLine = methodTree.firstToken().line();
                int endLine = methodTree.lastToken().line();
                int loc = endLine - startLine + 1;

                if (loc > longestLoc) {
                    longestLoc = loc;
                    longestMethod = methodTree;
                }
            } else if (member.is(Tree.Kind.CLASS)) {
                collectLongestMethodPerClass((ClassTree) member, output);
            }
        }

        if (longestMethod != null) {
            output.add(new ClassLongestMethod(currentClass, longestMethod, longestLoc));
        }
    }

    private String round(double value) {
        return String.format(java.util.Locale.US, "%.2f", value);
    }

    private record ClassLongestMethod(ClassTree classTree, MethodTree methodTree, int loc) {
    }
}
