public class Snippet__6212569 {

    public Element replaceWith(NewElement newElement) {
        checkNotRemoved();
        notPermittedOnRootElement();
        requireNonNull(newElement, "Required not null new element");
        insertAfter(newElement);
        final Element inserted = getNextSibling();
        remove();
        return inserted;
      }

}
