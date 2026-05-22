public class Snippet__7131047 {

    @Override
        public boolean equals(Object obj)
        {
          if (this == obj)
          {
            return true;
          }

          if (obj == null)
          {
            return false;
          }

          if (getClass() != obj.getClass())
          {
            return false;
          }

          final MethodIdentity other = (MethodIdentity) obj;
          return new EqualsBuilder().
              append(_methodQualifiedName, other._methodQualifiedName).
              append(_parameterTypeNames, other._parameterTypeNames).
              isEquals();
        }

}
