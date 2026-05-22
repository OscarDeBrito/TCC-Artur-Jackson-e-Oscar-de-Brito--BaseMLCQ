public class Snippet__5480114 {

    private void validateNodeAutoCreationPolicy(final NodeAutoCreationPolicy policy)
        {
            String pattern = policy.getPattern();
            if(pattern == null)
            {
                throw new IllegalArgumentException("The 'pattern' attribute of a NodeAutoCreationPolicy MUST be supplied: " + policy);
            }

            try
            {
                Pattern.compile(pattern);
            }
            catch (PatternSyntaxException e)
            {
                throw new IllegalArgumentException("The 'pattern' attribute of a NodeAutoCreationPolicy MUST be a valid "
                                                   + "Java Regular Expression Pattern, the value '" + pattern + "' is not: " + policy);

            }

            String nodeType = policy.getNodeType();
            Class<? extends ConfiguredObject> sourceClass = null;
            for (Class<? extends ConfiguredObject> childClass : getModel().getChildTypes(getCategoryClass()))
            {
                if (childClass.getSimpleName().equalsIgnoreCase(nodeType.trim()))
                {
                    sourceClass = childClass;
                    break;
                }
            }
            if(sourceClass == null)
            {
                throw new IllegalArgumentException("The node type of a NodeAutoCreationPolicy must be a valid child type "
                                                   + "of a VirtualHost, '" + nodeType + "' is not.");
            }
            if(policy.isCreatedOnConsume() && !MessageSource.class.isAssignableFrom(sourceClass))
            {
                throw new IllegalArgumentException("A NodeAutoCreationPolicy which creates nodes on consume must have a "
                                                   + "nodeType which implements MessageSource, '" + nodeType + "' does not.");
            }

            if(policy.isCreatedOnPublish() && !MessageDestination.class.isAssignableFrom(sourceClass))
            {
                throw new IllegalArgumentException("A NodeAutoCreationPolicy which creates nodes on publish must have a "
                                                   + "nodeType which implements MessageDestination, '" + nodeType + "' does not.");
            }
            if(!(policy.isCreatedOnConsume() || policy.isCreatedOnPublish()))
            {
                throw new IllegalArgumentException("A NodeAutoCreationPolicy must create on consume, create on publish or both.");
            }

        }

}
