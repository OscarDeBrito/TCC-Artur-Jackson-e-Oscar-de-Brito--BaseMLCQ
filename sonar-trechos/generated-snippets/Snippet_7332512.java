public class Snippet__7332512 {

    @Override
            public Image getColumnImage(final Object element, final int columnIndex) {
                if (!(element instanceof BuildDefinitionReference) || columnIndex > 0) {
                    return null;
                }

                final BuildDefinitionReference definition = (BuildDefinitionReference) element;
                return imageHelper.getBuildDefinitionImage(definition);
            }

}
