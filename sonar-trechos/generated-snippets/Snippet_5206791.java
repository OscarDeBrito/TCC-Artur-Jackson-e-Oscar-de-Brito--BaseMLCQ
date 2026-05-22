public class Snippet__5206791 {

    @Override
      protected void encodeBadge(FacesContext facesContext, AbstractUICommand command) throws IOException {
        final AbstractUIButton button = (AbstractUIButton) command;

        for (final UIComponent child : button.getChildren()) {
          if (child instanceof AbstractUIBadge) {
            child.encodeAll(facesContext);
          }
        }
      }

}
