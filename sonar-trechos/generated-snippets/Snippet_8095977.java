public class Snippet__8095977 {

    @Override
      public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
      {
        switch (featureID)
        {
          case ComplexrewritetestPackage.ADD__ADD_OPERANDS:
            return ((InternalEList<?>)getAddOperands()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
      }

}
