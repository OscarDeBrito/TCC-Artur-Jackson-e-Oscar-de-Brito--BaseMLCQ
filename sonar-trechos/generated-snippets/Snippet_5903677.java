public class Snippet__5903677 {

    public boolean equals(ExtendedType other)
        {
          try
          {
            return other.nodetype == this.nodetype &&
                    other.localName.equals(this.localName) &&
                    other.namespace.equals(this.namespace);
          }
          catch(NullPointerException e)
          {
            return false;
          }
        }

}
