public class Snippet__7995724 {

    @Override
      public boolean eIsSet(int featureID)
      {
        switch (featureID)
        {
          case Bug347012TestLanguagePackage.MY_BINDING__NAME:
            return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
          case Bug347012TestLanguagePackage.MY_BINDING__TYPE:
            return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
          case Bug347012TestLanguagePackage.MY_BINDING__EXPRESSION:
            return expression != null;
        }
        return super.eIsSet(featureID);
      }

}
