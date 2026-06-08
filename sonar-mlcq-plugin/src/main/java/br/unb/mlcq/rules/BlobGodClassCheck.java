package br.unb.mlcq.rules;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.*;

import java.util.*;

@Rule(key = MlcqRulesDefinition.BLOB_GOD_CLASS_RULE_KEY)
public class BlobGodClassCheck extends IssuableSubscriptionVisitor {

    private static final int WMC_THRESHOLD = 47;
    private static final int ATFD_THRESHOLD = 5;
    private static final double TCC_THRESHOLD = 1.0 / 3.0;

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return Collections.singletonList(Tree.Kind.CLASS);
    }

    @Override
    public void visitNode(Tree tree) {
        ClassTree classTree = (ClassTree) tree;

        List<MethodTree> methods = getConcreteMethods(classTree);

        if (methods.size() < 2) {
            return;
        }

        int wmc = calculateWmc(methods);
        int atfd = calculateClassAtfd(methods);
        double tcc = calculateTcc(classTree, methods);

        if (wmc >= WMC_THRESHOLD && atfd > ATFD_THRESHOLD && tcc < TCC_THRESHOLD) {
            reportIssue(
                    classTree.simpleName(),
                    "Blob/God Class detected (WMC="
                            + wmc
                            + ", ATFD="
                            + atfd
                            + ", TCC="
                            + round(tcc)
                            + ")."
            );
        }
    }

    private List<MethodTree> getConcreteMethods(ClassTree classTree) {
        List<MethodTree> methods = new ArrayList<>();

        for (Tree member : classTree.members()) {
            if (member.is(Tree.Kind.METHOD)) {
                MethodTree method = (MethodTree) member;

                if (method.block() != null && !method.is(Tree.Kind.CONSTRUCTOR)) {
                    methods.add(method);
                }
            }
        }

        return methods;
    }

    private int calculateWmc(List<MethodTree> methods) {
        int total = 0;

        for (MethodTree method : methods) {
            total += calculateCyclomaticComplexity(method);
        }

        return total;
    }

    private int calculateCyclomaticComplexity(MethodTree method) {
        CyclomaticVisitor visitor = new CyclomaticVisitor();
        method.accept(visitor);
        return visitor.complexity;
    }

    private int calculateClassAtfd(List<MethodTree> methods) {
        int total = 0;

        for (MethodTree method : methods) {
            ForeignAccessVisitor visitor = new ForeignAccessVisitor();
            method.accept(visitor);
            total += visitor.foreignDataAccesses;
        }

        return total;
    }

    private double calculateTcc(ClassTree classTree, List<MethodTree> methods) {
        Set<String> fields = collectClassFields(classTree);

        if (methods.size() < 2 || fields.isEmpty()) {
            return 0.0;
        }

        Map<MethodTree, Set<String>> methodToFields = new HashMap<>();

        for (MethodTree method : methods) {
            LocalFieldAccessVisitor visitor = new LocalFieldAccessVisitor(fields);
            method.accept(visitor);
            methodToFields.put(method, visitor.accessedFields);
        }

        int np = methods.size() * (methods.size() - 1) / 2;
        int ndc = 0;

        for (int i = 0; i < methods.size(); i++) {
            for (int j = i + 1; j < methods.size(); j++) {
                Set<String> first = methodToFields.get(methods.get(i));
                Set<String> second = methodToFields.get(methods.get(j));

                if (hasIntersection(first, second)) {
                    ndc++;
                }
            }
        }

        if (np == 0) {
            return 0.0;
        }

        return (double) ndc / np;
    }

    private Set<String> collectClassFields(ClassTree classTree) {
        Set<String> fields = new HashSet<>();

        for (Tree member : classTree.members()) {
            if (member.is(Tree.Kind.VARIABLE)) {
                VariableTree variable = (VariableTree) member;
                fields.add(variable.simpleName().name());
            }
        }

        return fields;
    }

    private boolean hasIntersection(Set<String> first, Set<String> second) {
        for (String item : first) {
            if (second.contains(item)) {
                return true;
            }
        }

        return false;
    }

    private String round(double value) {
        return String.format(java.util.Locale.US, "%.2f", value);
    }

    private static final class CyclomaticVisitor extends BaseTreeVisitor {
        private int complexity = 1;

        @Override
        public void visitIfStatement(IfStatementTree tree) {
            complexity++;
            super.visitIfStatement(tree);
        }

        @Override
        public void visitForStatement(ForStatementTree tree) {
            complexity++;
            super.visitForStatement(tree);
        }

        @Override
        public void visitForEachStatement(ForEachStatement tree) {
            complexity++;
            super.visitForEachStatement(tree);
        }

        @Override
        public void visitWhileStatement(WhileStatementTree tree) {
            complexity++;
            super.visitWhileStatement(tree);
        }

        @Override
        public void visitDoWhileStatement(DoWhileStatementTree tree) {
            complexity++;
            super.visitDoWhileStatement(tree);
        }

        @Override
        public void visitCaseLabel(CaseLabelTree tree) {
            complexity++;
            super.visitCaseLabel(tree);
        }

        @Override
        public void visitConditionalExpression(ConditionalExpressionTree tree) {
            complexity++;
            super.visitConditionalExpression(tree);
        }

        @Override
        public void visitBinaryExpression(BinaryExpressionTree tree) {
            if (tree.is(Tree.Kind.CONDITIONAL_AND) || tree.is(Tree.Kind.CONDITIONAL_OR)) {
                complexity++;
            }

            super.visitBinaryExpression(tree);
        }

        @Override
        public void visitCatch(CatchTree tree) {
            complexity++;
            super.visitCatch(tree);
        }
    }

    private static final class ForeignAccessVisitor extends BaseTreeVisitor {
        private int foreignDataAccesses = 0;

        @Override
        public void visitMethodInvocation(MethodInvocationTree tree) {
            if (tree.methodSelect().is(Tree.Kind.MEMBER_SELECT)) {
                MemberSelectExpressionTree memberSelect =
                        (MemberSelectExpressionTree) tree.methodSelect();

                String methodName = memberSelect.identifier().name();
                String receiverName = memberSelect.expression().toString();

                if (isForeignAccessor(methodName, receiverName)) {
                    foreignDataAccesses++;
                }
            }

            super.visitMethodInvocation(tree);
        }

        private boolean isForeignAccessor(String methodName, String receiverName) {
            return (methodName.startsWith("get") || methodName.startsWith("is"))
                    && !"this".equals(receiverName)
                    && !"super".equals(receiverName)
                    && !receiverName.isBlank()
                    && !looksLikeStaticAccess(receiverName);
        }

        private boolean looksLikeStaticAccess(String receiverName) {
            char first = receiverName.charAt(0);
            return Character.isUpperCase(first);
        }
    }

    private static final class LocalFieldAccessVisitor extends BaseTreeVisitor {
        private final Set<String> classFields;
        private final Set<String> accessedFields = new HashSet<>();

        private LocalFieldAccessVisitor(Set<String> classFields) {
            this.classFields = classFields;
        }

        @Override
        public void visitIdentifier(IdentifierTree tree) {
            if (classFields.contains(tree.name())) {
                accessedFields.add(tree.name());
            }

            super.visitIdentifier(tree);
        }

        @Override
        public void visitMemberSelectExpression(MemberSelectExpressionTree tree) {
            if ("this".equals(tree.expression().toString())
                    && classFields.contains(tree.identifier().name())) {
                accessedFields.add(tree.identifier().name());
            }

            super.visitMemberSelectExpression(tree);
        }
    }
}
