/**
 */
package intentmodel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link intentmodel.Data#getMIMEType <em>MIME Type</em>}</li>
 *   <li>{@link intentmodel.Data#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see intentmodel.IntentmodelPackage#getData()
 * @model
 * @generated
 */
public interface Data extends EObject {
	/**
	 * Returns the value of the '<em><b>MIME Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>MIME Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>MIME Type</em>' attribute.
	 * @see #setMIMEType(String)
	 * @see intentmodel.IntentmodelPackage#getData_MIMEType()
	 * @model
	 * @generated
	 */
	String getMIMEType();

	/**
	 * Sets the value of the '{@link intentmodel.Data#getMIMEType <em>MIME Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>MIME Type</em>' attribute.
	 * @see #getMIMEType()
	 * @generated
	 */
	void setMIMEType(String value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see intentmodel.IntentmodelPackage#getData_Value()
	 * @model
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link intentmodel.Data#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

} // Data
