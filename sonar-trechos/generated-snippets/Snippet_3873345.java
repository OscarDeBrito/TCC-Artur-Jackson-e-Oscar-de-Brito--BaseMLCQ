public class Snippet__3873345 {

    @Override
      public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkAttributeFilter that = (LinkAttributeFilter) o;
        return isVisible() == that.isVisible() && Objects.equals(linkAttribute, that.linkAttribute);
      }

}
