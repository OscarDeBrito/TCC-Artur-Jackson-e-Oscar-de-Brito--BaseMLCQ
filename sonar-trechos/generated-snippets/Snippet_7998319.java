public interface SuperPackageFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  SuperPackageFactory eINSTANCE = org.eclipse.xtext.generator.ecore.superPackage.impl.SuperPackageFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Super Main</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Super Main</em>'.
   * @generated
   */
  SuperMain createSuperMain();

  /**
   * Returns a new object of class '<em>Another Super Main</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Another Super Main</em>'.
   * @generated
   */
  AnotherSuperMain createAnotherSuperMain();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  SuperPackagePackage getSuperPackagePackage();

} //SuperPackageFactory
