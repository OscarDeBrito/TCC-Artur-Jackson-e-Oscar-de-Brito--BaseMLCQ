public class Snippet__8856936 {

    public void setFieldValue(_Fields field, Object value) {
          switch (field) {
          case SUCCESS:
            if (value == null) {
              unsetSuccess();
            } else {
              setSuccess((Boolean)value);
            }
            break;

          case IRE:
            if (value == null) {
              unsetIre();
            } else {
              setIre((org.apache.airavata.model.error.InvalidRequestException)value);
            }
            break;

          case ACE:
            if (value == null) {
              unsetAce();
            } else {
              setAce((org.apache.airavata.model.error.AiravataClientException)value);
            }
            break;

          case ASE:
            if (value == null) {
              unsetAse();
            } else {
              setAse((org.apache.airavata.model.error.AiravataSystemException)value);
            }
            break;

          case AE:
            if (value == null) {
              unsetAe();
            } else {
              setAe((org.apache.airavata.model.error.AuthorizationException)value);
            }
            break;

          }
        }

}
