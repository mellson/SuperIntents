/**
 */
package intentmodel.impl;

import intentmodel.Data;
import intentmodel.Intent;
import intentmodel.IntentmodelPackage;
import intentmodel.StringToObject;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Intent</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link intentmodel.impl.IntentImpl#getCategories <em>Categories</em>}</li>
 *   <li>{@link intentmodel.impl.IntentImpl#getAction <em>Action</em>}</li>
 *   <li>{@link intentmodel.impl.IntentImpl#getComponent <em>Component</em>}</li>
 *   <li>{@link intentmodel.impl.IntentImpl#getData <em>Data</em>}</li>
 *   <li>{@link intentmodel.impl.IntentImpl#getExtras <em>Extras</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IntentImpl extends EObjectImpl implements Intent {
	/**
	 * The cached value of the '{@link #getCategories() <em>Categories</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCategories()
	 * @generated
	 * @ordered
	 */
	protected EList<String> categories;

	/**
	 * The default value of the '{@link #getAction() <em>Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAction()
	 * @generated
	 * @ordered
	 */
	protected static final String ACTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAction() <em>Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAction()
	 * @generated
	 * @ordered
	 */
	protected String action = ACTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getComponent() <em>Component</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponent()
	 * @generated
	 * @ordered
	 */
	protected static final String COMPONENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getComponent() <em>Component</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponent()
	 * @generated
	 * @ordered
	 */
	protected String component = COMPONENT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getData() <em>Data</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getData()
	 * @generated
	 * @ordered
	 */
	protected Data data;

	/**
	 * The cached value of the '{@link #getExtras() <em>Extras</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtras()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> extras;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IntentmodelPackage.Literals.INTENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getCategories() {
		if (categories == null) {
			categories = new EDataTypeUniqueEList<String>(String.class, this, IntentmodelPackage.INTENT__CATEGORIES);
		}
		return categories;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAction() {
		return action;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAction(String newAction) {
		String oldAction = action;
		action = newAction;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IntentmodelPackage.INTENT__ACTION, oldAction, action));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getComponent() {
		return component;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComponent(String newComponent) {
		String oldComponent = component;
		component = newComponent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IntentmodelPackage.INTENT__COMPONENT, oldComponent, component));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Data getData() {
		return data;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetData(Data newData, NotificationChain msgs) {
		Data oldData = data;
		data = newData;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, IntentmodelPackage.INTENT__DATA, oldData, newData);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setData(Data newData) {
		if (newData != data) {
			NotificationChain msgs = null;
			if (data != null)
				msgs = ((InternalEObject)data).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - IntentmodelPackage.INTENT__DATA, null, msgs);
			if (newData != null)
				msgs = ((InternalEObject)newData).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - IntentmodelPackage.INTENT__DATA, null, msgs);
			msgs = basicSetData(newData, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IntentmodelPackage.INTENT__DATA, newData, newData));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getExtras() {
		if (extras == null) {
			extras = new EcoreEMap<String,String>(IntentmodelPackage.Literals.STRING_TO_OBJECT, StringToObjectImpl.class, this, IntentmodelPackage.INTENT__EXTRAS);
		}
		return extras;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case IntentmodelPackage.INTENT__DATA:
				return basicSetData(null, msgs);
			case IntentmodelPackage.INTENT__EXTRAS:
				return ((InternalEList<?>)getExtras()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IntentmodelPackage.INTENT__CATEGORIES:
				return getCategories();
			case IntentmodelPackage.INTENT__ACTION:
				return getAction();
			case IntentmodelPackage.INTENT__COMPONENT:
				return getComponent();
			case IntentmodelPackage.INTENT__DATA:
				return getData();
			case IntentmodelPackage.INTENT__EXTRAS:
				if (coreType) return getExtras();
				else return getExtras().map();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case IntentmodelPackage.INTENT__CATEGORIES:
				getCategories().clear();
				getCategories().addAll((Collection<? extends String>)newValue);
				return;
			case IntentmodelPackage.INTENT__ACTION:
				setAction((String)newValue);
				return;
			case IntentmodelPackage.INTENT__COMPONENT:
				setComponent((String)newValue);
				return;
			case IntentmodelPackage.INTENT__DATA:
				setData((Data)newValue);
				return;
			case IntentmodelPackage.INTENT__EXTRAS:
				((EStructuralFeature.Setting)getExtras()).set(newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case IntentmodelPackage.INTENT__CATEGORIES:
				getCategories().clear();
				return;
			case IntentmodelPackage.INTENT__ACTION:
				setAction(ACTION_EDEFAULT);
				return;
			case IntentmodelPackage.INTENT__COMPONENT:
				setComponent(COMPONENT_EDEFAULT);
				return;
			case IntentmodelPackage.INTENT__DATA:
				setData((Data)null);
				return;
			case IntentmodelPackage.INTENT__EXTRAS:
				getExtras().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case IntentmodelPackage.INTENT__CATEGORIES:
				return categories != null && !categories.isEmpty();
			case IntentmodelPackage.INTENT__ACTION:
				return ACTION_EDEFAULT == null ? action != null : !ACTION_EDEFAULT.equals(action);
			case IntentmodelPackage.INTENT__COMPONENT:
				return COMPONENT_EDEFAULT == null ? component != null : !COMPONENT_EDEFAULT.equals(component);
			case IntentmodelPackage.INTENT__DATA:
				return data != null;
			case IntentmodelPackage.INTENT__EXTRAS:
				return extras != null && !extras.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (categories: ");
		result.append(categories);
		result.append(", action: ");
		result.append(action);
		result.append(", component: ");
		result.append(component);
		result.append(')');
		return result.toString();
	}

} //IntentImpl
