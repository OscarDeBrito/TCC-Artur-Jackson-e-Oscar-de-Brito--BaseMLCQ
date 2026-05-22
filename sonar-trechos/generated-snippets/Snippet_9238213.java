public class Snippet__9238213 {

    @Override
            public boolean equals(Object obj) {
                if (obj == null) {
                    return false;
                }
                if (getClass() != obj.getClass()) {
                    return false;
                }
                final AnnotationDescription other = (AnnotationDescription) obj;
                if (!Objects.equals(this.annotationType, other.annotationType)) {
                    return false;
                }
                if (!Objects.equals(this.values, other.values)) {
                    return false;
                }
                return true;
            }

}
