public class Snippet__5551982 {

    public void setFieldValue(_Fields field, Object value) {
        switch (field) {
        case TYPE:
          if (value == null) {
            unsetType();
          } else {
            setType((TPrivilegePrincipalType)value);
          }
          break;

        case VALUE:
          if (value == null) {
            unsetValue();
          } else {
            setValue((String)value);
          }
          break;

        }
      }

}
