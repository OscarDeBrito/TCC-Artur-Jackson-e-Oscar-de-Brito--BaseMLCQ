public class Snippet__8104990 {

    @Override
      public void eUnset(int featureID)
      {
        switch (featureID)
        {
          case BacktrackingContentAssistTestPackage.PREFIX_EXP__OP:
            setOp(OP_EDEFAULT);
            return;
          case BacktrackingContentAssistTestPackage.PREFIX_EXP__SOURCE:
            setSource((Expression)null);
            return;
        }
        super.eUnset(featureID);
      }

}
