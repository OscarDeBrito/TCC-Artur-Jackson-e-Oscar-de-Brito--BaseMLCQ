package br.unb.mlcq.rules;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.IdentifierTree;
import org.sonar.plugins.java.api.tree.MemberSelectExpressionTree;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.VariableTree;

import java.util.HashSet;
import java.util.Set;
import java.util.Collections;
import java.util.List;

@Rule(key = MlcqRulesDefinition.FEATURE_ENVY_RULE_KEY)
public class FeatureEnvyCheck extends IssuableSubscriptionVisitor {

    private static final int ATFD_THRESHOLD = 5;
    private static final double LAA_THRESHOLD = 1.0 / 3.0;
    private static final int FDP_THRESHOLD = 5;

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return Collections.singletonList(Tree.Kind.CLASS);
    }

    @Override
    public void visitNode(Tree tree) {
        ClassTree classTree = (ClassTree) tree;

        Set<String> classFields = collectClassFields(classTree);

        for (Tree member : classTree.members()) {
            if (member.is(Tree.Kind.METHOD)) {
                MethodTree methodTree = (MethodTree) member;

                if (methodTree.block() == null) {
                    continue;
                }

                FeatureEnvyMetrics metrics = calculateMetrics(methodTree, classFields);

                if (metrics.atfd > ATFD_THRESHOLD
                        && metrics.laa < LAA_THRESHOLD
                        && metrics.fdp <= FDP_THRESHOLD) {

                    reportIssue(
                            methodTree.simpleName(),
                            "Feature Envy detected (ATFD="
                                    + metrics.atfd
                                    + ", LAA="
                                    + round(metrics.laa)
                                    + ", FDP="
                                    + metrics.fdp
                                    + ")."
                    );
                }
            }
        }
    }

    private Set<String> collectClassFields(ClassTree classTree) {
        Set<String> fields = new HashSet<>();

        for (Tree member : classTree.members()) {
            if (member.is(Tree.Kind.VARIABLE)) {
                VariableTree variableTree = (VariableTree) member;
                fields.add(variableTree.simpleName().name());
            }
        }

        return fields;
    }

    private FeatureEnvyMetrics calculateMetrics(MethodTree methodTree, Set<String> classFields) {
        FeatureEnvyVisitor visitor = new FeatureEnvyVisitor(classFields);
        methodTree.accept(visitor);

        int atfd = visitor.foreignDataAccesses;
        int atld = visitor.localAttributeAccesses;
        int totalAttributeAccesses = atfd + atld;

        double laa = totalAttributeAccesses == 0
                ? 1.0
                : (double) atld / totalAttributeAccesses;

        int fdp = visitor.foreignDataProviders.size();

        return new FeatureEnvyMetrics(atfd, atld, laa, fdp);
    }

    private String round(double value) {
        return String.format(java.util.Locale.US, "%.2f", value);
    }

    private static final class FeatureEnvyMetrics {
        private final int atfd;
        private final int atld;
        private final double laa;
        private final int fdp;

        private FeatureEnvyMetrics(int atfd, int atld, double laa, int fdp) {
            this.atfd = atfd;
            this.atld = atld;
            this.laa = laa;
            this.fdp = fdp;
        }
    }

    private static final class FeatureEnvyVisitor extends BaseTreeVisitor {

        private final Set<String> classFields;
        private final Set<String> foreignDataProviders = new HashSet<>();

        private int foreignDataAccesses = 0;
        private int localAttributeAccesses = 0;

        private FeatureEnvyVisitor(Set<String> classFields) {
            this.classFields = classFields;
        }

        @Override
        public void visitIdentifier(IdentifierTree tree) {
            String name = tree.name();

            if (classFields.contains(name)) {
                localAttributeAccesses++;
            }

            super.visitIdentifier(tree);
        }

        @Override
        public void visitMemberSelectExpression(MemberSelectExpressionTree tree) {
            String selectedName = tree.identifier().name();
            String receiverName = tree.expression().toString();

            if ("this".equals(receiverName) && classFields.contains(selectedName)) {
                localAttributeAccesses++;
            }

            super.visitMemberSelectExpression(tree);
        }

        @Override
        public void visitMethodInvocation(MethodInvocationTree tree) {
            if (tree.methodSelect().is(Tree.Kind.MEMBER_SELECT)) {
                MemberSelectExpressionTree memberSelect =
                        (MemberSelectExpressionTree) tree.methodSelect();

                String methodName = memberSelect.identifier().name();
                String receiverName = memberSelect.expression().toString();

                if (isGetterLike(methodName)
                        && !"this".equals(receiverName)
                        && !"super".equals(receiverName)
                        && !receiverName.isBlank()
                        && !looksLikeStaticAccess(receiverName)) {

                    foreignDataAccesses++;
                    foreignDataProviders.add(receiverName);
                }
            }

            super.visitMethodInvocation(tree);
        }

        private boolean isGetterLike(String methodName) {
            return methodName.startsWith("get") || methodName.startsWith("is");
        }

        private boolean looksLikeStaticAccess(String receiverName) {
            if (receiverName == null || receiverName.isBlank()) {
                return false;
            }

            char first = receiverName.charAt(0);
            return Character.isUpperCase(first);
        }
    }
}
