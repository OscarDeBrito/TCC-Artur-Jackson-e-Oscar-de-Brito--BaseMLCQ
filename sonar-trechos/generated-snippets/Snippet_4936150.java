public class Network {
   public static Builder builder() {
      return new Builder();
   }

   public static class Builder {
      protected String name;
      protected String description;

      /**
       * @see Network#getName
       */
      public Builder name(String name) {
         this.name = name;
         return this;
      }

      /**
       * @see Section#getDescription
       */
      public Builder description(String description) {
         this.description = description;
         return this;
      }

      public Network build() {
         return new Network(name, description);
      }

      public Builder fromNetwork(Network in) {
         return name(in.getName()).description(in.getDescription());
      }
   }

   private final String name;
   private final String description;

   public Network(String name, String description) {
      this.name = name;
      this.description = description;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((description == null) ? 0 : description.hashCode());
      result = prime * result + ((name == null) ? 0 : name.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      Network other = (Network) obj;
      if (description == null) {
         if (other.description != null)
            return false;
      } else if (!description.equals(other.description))
         return false;
      if (name == null) {
         if (other.name != null)
            return false;
      } else if (!name.equals(other.name))
         return false;
      return true;
   }

   @Override
   public String toString() {
      return "[name=" + name + ", description=" + description + "]";
   }

   public String getName() {
      return name;
   }

   public String getDescription() {
      return description;
   }
}
