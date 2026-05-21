package br.unb.mlcq.rules;

import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Modifier;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.VariableTree;

public final class MetricsUtils {

    private MetricsUtils() {
    }

    public static int countPublicAttributes(ClassTree classTree) {
        int count = 0;

        for (Tree member : classTree.members()) {
            if (member.is(Tree.Kind.VARIABLE)) {
                VariableTree variable = (VariableTree) member;

                if (variable.modifiers().modifiers().contains(Modifier.PUBLIC)) {
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

                if (method.modifiers().modifiers().contains(Modifier.PUBLIC)) {
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

                if (method.modifiers().modifiers().contains(Modifier.PUBLIC)
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

                if (method.modifiers().modifiers().contains(Modifier.PUBLIC)
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

        boolean accessorName =
                name.startsWith("get")
                        || name.startsWith("set")
                        || name.startsWith("is");

        if (!accessorName) {
            return false;
        }

        if (method.block() == null) {
            return false;
        }

        int startLine = method.firstToken().line();
        int endLine = method.lastToken().line();
        int loc = endLine - startLine + 1;

        return loc <= 6;
    }

    public static boolean isConstructor(MethodTree method) {
        return method.is(Tree.Kind.CONSTRUCTOR);
    }
}
