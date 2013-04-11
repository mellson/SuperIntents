/**
 */
package intentmodel;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see intentmodel.IntentmodelFactory
 * @model kind="package"
 * @generated
 */
public interface IntentmodelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "intentmodel";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "platform:/resource/IntentModel/model/IntentModel.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "intentmodel";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	IntentmodelPackage eINSTANCE = intentmodel.impl.IntentmodelPackageImpl.init();

	/**
	 * The meta object id for the '{@link intentmodel.impl.SuperIntentImpl <em>Super Intent</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see intentmodel.impl.SuperIntentImpl
	 * @see intentmodel.impl.IntentmodelPackageImpl#getSuperIntent()
	 * @generated
	 */
	int SUPER_INTENT = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_INTENT__DESCRIPTION = 0;

	/**
	 * The feature id for the '<em><b>Intent</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_INTENT__INTENT = 1;

	/**
	 * The feature id for the '<em><b>Output</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_INTENT__OUTPUT = 2;

	/**
	 * The number of structural features of the '<em>Super Intent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_INTENT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link intentmodel.impl.IntentImpl <em>Intent</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see intentmodel.impl.IntentImpl
	 * @see intentmodel.impl.IntentmodelPackageImpl#getIntent()
	 * @generated
	 */
	int INTENT = 1;

	/**
	 * The feature id for the '<em><b>Categories</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT__CATEGORIES = 0;

	/**
	 * The feature id for the '<em><b>Action</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT__ACTION = 1;

	/**
	 * The feature id for the '<em><b>Component</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT__COMPONENT = 2;

	/**
	 * The feature id for the '<em><b>Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT__DATA = 3;

	/**
	 * The feature id for the '<em><b>Extras</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT__EXTRAS = 4;

	/**
	 * The number of structural features of the '<em>Intent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENT_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link intentmodel.impl.DataImpl <em>Data</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see intentmodel.impl.DataImpl
	 * @see intentmodel.impl.IntentmodelPackageImpl#getData()
	 * @generated
	 */
	int DATA = 2;

	/**
	 * The feature id for the '<em><b>MIME Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA__MIME_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link intentmodel.impl.StringToObjectImpl <em>String To Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see intentmodel.impl.StringToObjectImpl
	 * @see intentmodel.impl.IntentmodelPackageImpl#getStringToObject()
	 * @generated
	 */
	int STRING_TO_OBJECT = 3;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_OBJECT__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_OBJECT__VALUE = 1;

	/**
	 * The number of structural features of the '<em>String To Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_OBJECT_FEATURE_COUNT = 2;


	/**
	 * Returns the meta object for class '{@link intentmodel.SuperIntent <em>Super Intent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Super Intent</em>'.
	 * @see intentmodel.SuperIntent
	 * @generated
	 */
	EClass getSuperIntent();

	/**
	 * Returns the meta object for the attribute '{@link intentmodel.SuperIntent#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see intentmodel.SuperIntent#getDescription()
	 * @see #getSuperIntent()
	 * @generated
	 */
	EAttribute getSuperIntent_Description();

	/**
	 * Returns the meta object for the containment reference '{@link intentmodel.SuperIntent#getIntent <em>Intent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Intent</em>'.
	 * @see intentmodel.SuperIntent#getIntent()
	 * @see #getSuperIntent()
	 * @generated
	 */
	EReference getSuperIntent_Intent();

	/**
	 * Returns the meta object for the containment reference '{@link intentmodel.SuperIntent#getOutput <em>Output</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Output</em>'.
	 * @see intentmodel.SuperIntent#getOutput()
	 * @see #getSuperIntent()
	 * @generated
	 */
	EReference getSuperIntent_Output();

	/**
	 * Returns the meta object for class '{@link intentmodel.Intent <em>Intent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Intent</em>'.
	 * @see intentmodel.Intent
	 * @generated
	 */
	EClass getIntent();

	/**
	 * Returns the meta object for the attribute list '{@link intentmodel.Intent#getCategories <em>Categories</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Categories</em>'.
	 * @see intentmodel.Intent#getCategories()
	 * @see #getIntent()
	 * @generated
	 */
	EAttribute getIntent_Categories();

	/**
	 * Returns the meta object for the attribute '{@link intentmodel.Intent#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Action</em>'.
	 * @see intentmodel.Intent#getAction()
	 * @see #getIntent()
	 * @generated
	 */
	EAttribute getIntent_Action();

	/**
	 * Returns the meta object for the attribute '{@link intentmodel.Intent#getComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Component</em>'.
	 * @see intentmodel.Intent#getComponent()
	 * @see #getIntent()
	 * @generated
	 */
	EAttribute getIntent_Component();

	/**
	 * Returns the meta object for the containment reference '{@link intentmodel.Intent#getData <em>Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Data</em>'.
	 * @see intentmodel.Intent#getData()
	 * @see #getIntent()
	 * @generated
	 */
	EReference getIntent_Data();

	/**
	 * Returns the meta object for the map '{@link intentmodel.Intent#getExtras <em>Extras</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Extras</em>'.
	 * @see intentmodel.Intent#getExtras()
	 * @see #getIntent()
	 * @generated
	 */
	EReference getIntent_Extras();

	/**
	 * Returns the meta object for class '{@link intentmodel.Data <em>Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data</em>'.
	 * @see intentmodel.Data
	 * @generated
	 */
	EClass getData();

	/**
	 * Returns the meta object for the attribute '{@link intentmodel.Data#getMIMEType <em>MIME Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>MIME Type</em>'.
	 * @see intentmodel.Data#getMIMEType()
	 * @see #getData()
	 * @generated
	 */
	EAttribute getData_MIMEType();

	/**
	 * Returns the meta object for the attribute '{@link intentmodel.Data#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see intentmodel.Data#getValue()
	 * @see #getData()
	 * @generated
	 */
	EAttribute getData_Value();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>String To Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String To Object</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString"
	 *        valueDataType="org.eclipse.emf.ecore.EString"
	 * @generated
	 */
	EClass getStringToObject();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringToObject()
	 * @generated
	 */
	EAttribute getStringToObject_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringToObject()
	 * @generated
	 */
	EAttribute getStringToObject_Value();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	IntentmodelFactory getIntentmodelFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link intentmodel.impl.SuperIntentImpl <em>Super Intent</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see intentmodel.impl.SuperIntentImpl
		 * @see intentmodel.impl.IntentmodelPackageImpl#getSuperIntent()
		 * @generated
		 */
		EClass SUPER_INTENT = eINSTANCE.getSuperIntent();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUPER_INTENT__DESCRIPTION = eINSTANCE.getSuperIntent_Description();

		/**
		 * The meta object literal for the '<em><b>Intent</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUPER_INTENT__INTENT = eINSTANCE.getSuperIntent_Intent();

		/**
		 * The meta object literal for the '<em><b>Output</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUPER_INTENT__OUTPUT = eINSTANCE.getSuperIntent_Output();

		/**
		 * The meta object literal for the '{@link intentmodel.impl.IntentImpl <em>Intent</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see intentmodel.impl.IntentImpl
		 * @see intentmodel.impl.IntentmodelPackageImpl#getIntent()
		 * @generated
		 */
		EClass INTENT = eINSTANCE.getIntent();

		/**
		 * The meta object literal for the '<em><b>Categories</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTENT__CATEGORIES = eINSTANCE.getIntent_Categories();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTENT__ACTION = eINSTANCE.getIntent_Action();

		/**
		 * The meta object literal for the '<em><b>Component</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTENT__COMPONENT = eINSTANCE.getIntent_Component();

		/**
		 * The meta object literal for the '<em><b>Data</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTENT__DATA = eINSTANCE.getIntent_Data();

		/**
		 * The meta object literal for the '<em><b>Extras</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTENT__EXTRAS = eINSTANCE.getIntent_Extras();

		/**
		 * The meta object literal for the '{@link intentmodel.impl.DataImpl <em>Data</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see intentmodel.impl.DataImpl
		 * @see intentmodel.impl.IntentmodelPackageImpl#getData()
		 * @generated
		 */
		EClass DATA = eINSTANCE.getData();

		/**
		 * The meta object literal for the '<em><b>MIME Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA__MIME_TYPE = eINSTANCE.getData_MIMEType();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA__VALUE = eINSTANCE.getData_Value();

		/**
		 * The meta object literal for the '{@link intentmodel.impl.StringToObjectImpl <em>String To Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see intentmodel.impl.StringToObjectImpl
		 * @see intentmodel.impl.IntentmodelPackageImpl#getStringToObject()
		 * @generated
		 */
		EClass STRING_TO_OBJECT = eINSTANCE.getStringToObject();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_TO_OBJECT__KEY = eINSTANCE.getStringToObject_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_TO_OBJECT__VALUE = eINSTANCE.getStringToObject_Value();

	}

} //IntentmodelPackage
