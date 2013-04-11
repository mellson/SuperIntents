/**
 */
package intentmodel;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see intentmodel.IntentmodelPackage
 * @generated
 */
public interface IntentmodelFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	IntentmodelFactory eINSTANCE = intentmodel.impl.IntentmodelFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Super Intent</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Super Intent</em>'.
	 * @generated
	 */
	SuperIntent createSuperIntent();

	/**
	 * Returns a new object of class '<em>Intent</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Intent</em>'.
	 * @generated
	 */
	Intent createIntent();

	/**
	 * Returns a new object of class '<em>Data</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Data</em>'.
	 * @generated
	 */
	Data createData();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	IntentmodelPackage getIntentmodelPackage();

} //IntentmodelFactory
