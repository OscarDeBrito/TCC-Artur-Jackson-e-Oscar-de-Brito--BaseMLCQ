public class Snippet__4953209 {

    @Override
       public boolean equals(Object obj) {
          if (this == obj) return true;
          if (obj == null || getClass() != obj.getClass()) return false;
          EmailAlias that = EmailAlias.class.cast(obj);
          return Objects.equal(this.alias, that.alias);
       }

}
