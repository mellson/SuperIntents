/**
 */
package intentmodel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Super Intent</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link intentmodel.SuperIntent#getDescription <em>Description</em>}</li>
 *   <li>{@link intentmodel.SuperIntent#getIntent <em>Intent</em>}</li>
 *   <li>{@link intentmodel.SuperIntent#getOutput <em>Output</em>}</li>
 * </ul>
 * </p>
 *
 * @see intentmodel.IntentmodelPackage#getSuperIntent()
 * @model
 * @generated
 */
public interface SuperIntent extends EObject {
	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see intentmodel.IntentmodelPackage#getSuperIntent_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link intentmodel.SuperIntent#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Intent</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Intent</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Intent</em>' containment reference.
	 * @see #setIntent(Intent)
	 * @see intentmodel.IntentmodelPackage#getSuperIntent_Intent()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Intent getIntent();

	/**
	 * Sets the value of the '{@link intentmodel.SuperIntent#getIntent <em>Intent</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Intent</em>' containment reference.
	 * @see #getIntent()
	 * @generated
	 */
	void setIntent(Intent value);

	/**
	 * Returns the value of the '<em><b>Output</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Output</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Output</em>' containment reference.
	 * @see #setOutput(Data)
	 * @see intentmodel.IntentmodelPackage#getSuperIntent_Output()
	 * @model containment="true"
	 * @generated
	 */
	Data getOutput();

	/**
	 * Sets the value of the '{@link intentmodel.SuperIntent#getOutput <em>Output</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Output</em>' containment reference.
	 * @see #getOutput()
	 * @generated
	 */
	void setOutput(Data value);

} // SuperIntent
