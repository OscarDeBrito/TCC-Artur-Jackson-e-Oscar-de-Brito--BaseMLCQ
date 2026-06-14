package br.unb.mlcq.rules;

import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.BinaryExpressionTree;
import org.sonar.plugins.java.api.tree.CaseLabelTree;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.ConditionalExpressionTree;
import org.sonar.plugins.java.api.tree.DoWhileStatementTree;
import org.sonar.plugins.java.api.tree.ForEachStatement;
import org.sonar.plugins.java.api.tree.ForStatementTree;
import org.sonar.plugins.java.api.tree.IfStatementTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Modifier;
import org.sonar.plugins.java.api.tree.ModifierKeywordTree;
import org.sonar.plugins.java.api.tree.ModifiersTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.VariableTree;
import org.sonar.plugins.java.api.tree.WhileStatementTree;

public final class MetricsUtils {

    private MetricsUtils() {
    }

    private static boolean hasPublicModifier(ModifiersTree modifiersTree) {
        for (ModifierKeywordTree modifierKeyword : modifiersTree.modifiers()) {
            if (modifierKeyword.modifier() == Modifier.PUBLIC) {
                return true;
            }
        }

        return false;
    }

    public static int countPublicAttributes(ClassTree classTree) {
        int count = 0;

        for (Tree member : classTree.members()) {
            if (member.is(Tree.Kind.VARIABLE)) {
                VariableTree variable = (VariableTree) member;

                if (hasPublicModifier(variable.modifiers())) {
                    count++;
                }
            }
        }

        return count;
    }

    public static int countPublicMethods(ClassTree classTree) {
        int count = 0;

        for (Tree member : classTree.members()) {
            if (member.is(Tree.Kind.METHOD)) {
                MethodTree method = (MethodTree) member;

                if (hasPublicModifier(method.modifiers())) {
                    count++;
                }
            }
        }

        return count;
    }

    public static int countAccessorMethods(ClassTree classTree) {
        int count = 0;

        for (Tree member : classTree.members()) {
            if (member.is(Tree.Kind.METHOD)) {
                MethodTree method = (MethodTree) member;

                if (hasPublicModifier(method.modifiers())
                        && isAccessor(method)) {
                    count++;
                }
            }
        }

        return count;
    }

    public static int countFunctionalPublicMethods(ClassTree classTree) {
        int count = 0;

        for (Tree member : classTree.members()) {
            if (member.is(Tree.Kind.METHOD)) {
                MethodTree method = (MethodTree) member;

                if (hasPublicModifier(method.modifiers())
                        && !isAccessor(method)
                        && !isConstructor(method)) {
                    count++;
                }
            }
        }

        return count;
    }

    public static double calculateWOC(ClassTree classTree) {
        int publicMethods = countPublicMethods(classTree);
        int publicAttributes = countPublicAttributes(classTree);
        int functionalPublicMethods = countFunctionalPublicMethods(classTree);

        int exposedMembers = publicMethods + publicAttributes;

        if (exposedMembers == 0) {
            return 1.0;
        }

        return (double) functionalPublicMethods / exposedMembers;
    }

    public static boolean isAccessor(MethodTree method) {
        String name = method.simpleName().name();

        if (isConstructor(method)) {
            return false;
        }

        boolean accessorName =
                name.startsWith("get")
                        || name.startsWith("set")
                        || name.startsWith("is");

        if (!accessorName) {
            return false;
        }

        if (method.block() == null) {
            return true;
        }

        int startLine = method.firstToken().line();
        int endLine = method.lastToken().line();
        int loc = endLine - startLine + 1;

        return loc <= 6;
    }

    public static boolean isConstructor(MethodTree method) {
        return method.is(Tree.Kind.CONSTRUCTOR);
    }

    public static int calculateWMC(ClassTree classTree) {
        int wmc = 0;

        for (Tree member : classTree.members()) {
            if (member.is(Tree.Kind.METHOD, Tree.Kind.CONSTRUCTOR)) {
                MethodTree method = (MethodTree) member;
                wmc += calculateCyclomaticComplexity(method);
            }
        }

        return wmc;
    }

    public static int calculateCyclomaticComplexity(MethodTree method) {
        ComplexityVisitor visitor = new ComplexityVisitor();
        method.accept(visitor);
        return visitor.getComplexity();
    }

    private static class ComplexityVisitor extends BaseTreeVisitor {

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
            if (tree.is(Tree.Kind.CONDITIONAL_AND)
                    || tree.is(Tree.Kind.CONDITIONAL_OR)) {
                complexity++;
            }

            super.visitBinaryExpression(tree);
        }

        public int getComplexity() {
            return complexity;
        }
    }
}