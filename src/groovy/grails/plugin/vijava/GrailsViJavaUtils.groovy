/*
 * Copyright 2014 Aaron Brown
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package grails.plugin.vijava

import com.vmware.vim25.RuntimeFault
import com.vmware.vim25.InvalidProperty
import com.vmware.vim25.ManagedObjectReference

import com.vmware.vim25.mo.ManagedEntity
import com.vmware.vim25.mo.ManagedObject
import com.vmware.vim25.mo.ServiceInstance
import com.vmware.vim25.mo.InventoryNavigator

import java.net.URL
import java.net.MalformedURLException

import java.rmi.RemoteException

/**
 * Utilities class, for very low-level functions.
 *
 * @author  Aaron Brown
 */
class GrailsViJavaUtils {

    /**
     * Get the ServiceInstance for a vCenter (Ignores SSL).
     *
     * @param   vCenter
     *
     * The DNS Name or IP Address of the vCenter.
     *
     * @param   username
     *
     * The Username to log in to the vCenter.
     *
     * @param   password
     *
     * The password to log in to the vCenter.
     *
     * @return
     *
     * A ServiceInstance into the vCenter.
     *
     * @throws RemoteException
     *
     * @throws MalformedURLException
     */
    static ServiceInstance getServiceInstance(String vCenter, String username, String password) throws RemoteException, MalformedURLException {
        return new ServiceInstance(new URL("https://${vCenter}/sdk"), username, password, true)
    }

    /**
     * Retrieve a List of ManagedObjects.
     *
     * @param   si
     *
     * The ServiceInstance.
     *
     * @param   managedObjectType
     *
     * The ManagedObject type to search for.
     *
     * @return
     *
     * A List of ManagedObjects of the specified type.
     */
    static List<ManagedEntity> searchManagedEntities(ServiceInstance si, String managedObjectType) throws InvalidProperty, RuntimeFault, RemoteException {
        return new InventoryNavigator(si.rootFolder).searchManagedEntities(managedObjectType)
    }

    /**
     * Retrieve a List of ManagedObjects.
     *
     * @param   root
     *
     * The root Managed Entity to search under.
     *
     * @param   managedObjectType
     *
     * The ManagedObject type to search for.
     *
     * @return
     *
     * A List of ManagedObjects of the specified type.
     */
    static List<ManagedEntity> searchManagedEntities(ManagedEntity root, String managedObjectType) throws InvalidProperty, RuntimeFault, RemoteException {
        return new InventoryNavigator(root).searchManagedEntities(managedObjectType)
    }

    /**
     * Retrieve a ManagedObject.
     *
     * @param   si
     *
     * The ServiceInstance.
     *
     * @param   managedObjectType
     *
     * The ManagedObject type.
     *
     * @param   name
     *
     * The name of the ManagedObject.
     *
     * @return
     *
     * The ManagedObject request, or <code>null</code> if not found.
     */
    static ManagedEntity searchManagedEntity(ServiceInstance si, String managedObjectType, String name) throws InvalidProperty, RuntimeFault, RemoteException {
        return new InventoryNavigator(si.rootFolder).searchManagedEntity(managedObjectType, name)
    }

    /**
     * Retrieve a ManagedObject.
     *
     * @param   root
     *
     * The root Managed Entity to search under.
     *
     * @param   managedObjectType
     *
     * The ManagedObject type.
     *
     * @param   name
     *
     * The name of the ManagedObject.
     *
     * @return
     *
     * The ManagedObject request, or <code>null</code> if not found.
     */
    static ManagedEntity searchManagedEntity(ManagedEntity root, String managedObjectType, String name) throws InvalidProperty, RuntimeFault, RemoteException {
        return new InventoryNavigator(root).searchManagedEntity(managedObjectType, name)
    }

    /**
     * Create a new ManagedObjectReference.
     *
     * @param   type
     *
     * The ManagedObject type.
     *
     * @param   value
     *
     * The vCenter identifier.
     *
     * @return
     *
     * A ManagedObjectReference for the ManagedObject.
     */
    static ManagedObjectReference createMOR(String type, String value) {
        ManagedObjectReference mor = new ManagedObjectReference()
        mor.type = type
        mor.val = value

        return mor
    }
}
