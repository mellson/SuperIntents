/**
 */
package intentmodel.impl;

import intentmodel.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class IntentmodelFactoryImpl extends EFactoryImpl implements IntentmodelFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static IntentmodelFactory init() {
		try {
			IntentmodelFactory theIntentmodelFactory = (IntentmodelFactory)EPackage.Registry.INSTANCE.getEFactory("platform:/resource/IntentModel/model/IntentModel.ecore"); 
			if (theIntentmodelFactory != null) {
				return theIntentmodelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new IntentmodelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntentmodelFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case IntentmodelPackage.SUPER_INTENT: return createSuperIntent();
			case IntentmodelPackage.INTENT: return createIntent();
			case IntentmodelPackage.DATA: return createData();
			case IntentmodelPackage.STRING_TO_OBJECT: return createStringToObject();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SuperIntent createSuperIntent() {
		SuperIntentImpl superIntent = new SuperIntentImpl();
		return superIntent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Intent createIntent() {
		IntentImpl intent = new IntentImpl();
		return intent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Data createData() {
		DataImpl data = new DataImpl();
		return data;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StringToObject createStringToObject() {
		StringToObjectImpl stringToObject = new StringToObjectImpl();
		return stringToObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntentmodelPackage getIntentmodelPackage() {
		return (IntentmodelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static IntentmodelPackage getPackage() {
		return IntentmodelPackage.eINSTANCE;
	}

} //IntentmodelFactoryImpl
