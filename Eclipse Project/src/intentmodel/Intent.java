/**
 */
package intentmodel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Intent</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link intentmodel.Intent#getCategories <em>Categories</em>}</li>
 *   <li>{@link intentmodel.Intent#getAction <em>Action</em>}</li>
 *   <li>{@link intentmodel.Intent#getComponent <em>Component</em>}</li>
 *   <li>{@link intentmodel.Intent#getData <em>Data</em>}</li>
 *   <li>{@link intentmodel.Intent#getExtras <em>Extras</em>}</li>
 * </ul>
 * </p>
 *
 * @see intentmodel.IntentmodelPackage#getIntent()
 * @model
 * @generated
 */
public interface Intent extends EObject {
	/**
	 * Returns the value of the '<em><b>Categories</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Categories</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Categories</em>' attribute list.
	 * @see intentmodel.IntentmodelPackage#getIntent_Categories()
	 * @model
	 * @generated
	 */
	EList<String> getCategories();

	/**
	 * Returns the value of the '<em><b>Action</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Action</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Action</em>' attribute.
	 * @see #setAction(String)
	 * @see intentmodel.IntentmodelPackage#getIntent_Action()
	 * @model
	 * @generated
	 */
	String getAction();

	/**
	 * Sets the value of the '{@link intentmodel.Intent#getAction <em>Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action</em>' attribute.
	 * @see #getAction()
	 * @generated
	 */
	void setAction(String value);

	/**
	 * Returns the value of the '<em><b>Component</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component</em>' attribute.
	 * @see #setComponent(String)
	 * @see intentmodel.IntentmodelPackage#getIntent_Component()
	 * @model
	 * @generated
	 */
	String getComponent();

	/**
	 * Sets the value of the '{@link intentmodel.Intent#getComponent <em>Component</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component</em>' attribute.
	 * @see #getComponent()
	 * @generated
	 */
	void setComponent(String value);

	/**
	 * Returns the value of the '<em><b>Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data</em>' containment reference.
	 * @see #setData(Data)
	 * @see intentmodel.IntentmodelPackage#getIntent_Data()
	 * @model containment="true"
	 * @generated
	 */
	Data getData();

	/**
	 * Sets the value of the '{@link intentmodel.Intent#getData <em>Data</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data</em>' containment reference.
	 * @see #getData()
	 * @generated
	 */
	void setData(Data value);

	/**
	 * Returns the value of the '<em><b>Extras</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extras</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extras</em>' map.
	 * @see intentmodel.IntentmodelPackage#getIntent_Extras()
	 * @model mapType="intentmodel.StringToObject<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>"
	 * @generated
	 */
	EMap<String, String> getExtras();

} // Intent
