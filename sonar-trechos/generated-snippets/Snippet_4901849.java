public class Snippet__4901849 {

    @Override
            public void propertyChanged(PropertyState before, PropertyState after) throws CommitFailedException {
                String propertyName = after.getName();
                if (TOKEN_ATTRIBUTE_KEY.equals(propertyName)) {
                    String msg = "Attempt to change reserved token property " + propertyName;
                    throw constraintViolation(61, msg);
                } else if (TOKEN_ATTRIBUTE_EXPIRY.equals(propertyName)) {
                    verifyCommitInfo();
                } else if (JcrConstants.JCR_PRIMARYTYPE.equals(propertyName)) {
                    if (TOKEN_NT_NAME.equals(after.getValue(Type.STRING))) {
                        throw constraintViolation(62, "Changing primary type of existing node to the reserved token node type.");
                    }
                    if (isTokensParent(parentAfter) && TOKENS_NT_NAME.equals(before.getValue(Type.STRING))) {
                        throw constraintViolation(69, "Cannot change the primary type of an existing .tokens node.");
                    }
                }
            }

}
