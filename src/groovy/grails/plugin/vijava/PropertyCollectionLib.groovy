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

import grails.plugin.vijava.builders.PropertySpecBuilder
import grails.plugin.vijava.builders.PropertyFilterSpecBuilder

import com.vmware.vim25.InvalidType
import com.vmware.vim25.PropertySpec
import com.vmware.vim25.RuntimeFault
import com.vmware.vim25.SelectionSpec
import com.vmware.vim25.ObjectContent
import com.vmware.vim25.InvalidProperty
import com.vmware.vim25.PropertyFilterSpec
import com.vmware.vim25.ManagedObjectNotFound
import com.vmware.vim25.ManagedObjectReference

import com.vmware.vim25.mo.HostSystem
import com.vmware.vim25.mo.ManagedEntity
import com.vmware.vim25.mo.ServiceInstance

import java.rmi.RemoteException

/**
 * The library for quick and convenient Property Collection.
 *
 * @author Aaron Brown
 */
class PropertyCollectionLib {

    /**
     * Retrieve a Collection of Properties.
     *
     * <p>
     * Short-hand for retrieving properties under one root.
     * </p>
     *
     * <p>
     * The key of each map corresponds to the <b>type</b> parameter, and the
     * value is a List of the <b>properties</b>.
     * </p>
     *
     * @param   specDeclaration
     *
     * The declaration of PropertySpec in <code>Map</code>-form
     * (see method description for more details).
     *
     * @param   root
     *
     * The root ManagedEntity to start the search under.
     *
     * @param   selectionSpecs
     *
     * A SelectionSpec, usually generated by <b>PropertyCollectorUtils</b> provided
     * by the VI JAVA Library.
     *
     * @return
     *
     * The ObjectContent collection (to be parsed and interpreted).
     *
     * @throws  InvalidType
     *
     * Thrown if there is an invalid ManagedObject type in the PropertySpec collection.
     *
     * @throws  InvalidProperty
     *
     * Thrown if there exists a property requested for a particular type that does not exist for that
     * type within the PropertySpec collection.
     */
    static List<ObjectContent> collectProperties(Map<String, List<String>> specDeclaration, ManagedEntity root, SelectionSpec[] selectionSpecs) throws InvalidType, InvalidProperty {
        PropertyFilterSpec[] propertyFilterSpecs = new PropertyFilterSpecBuilder({
            addPropertyFilterSpec(specDeclaration, root.MOR, selectionSpecs)
        }).propertyFilterSpecs

        return collectProperties(root.serverConnection.serviceInstance, propertyFilterSpecs)
    }

    /**
     * Retrieve a Collection of Properties.
     *
     * <p>
     * Short-hand for retrieving properties under one root.
     * </p>
     *
     * @param   root
     *
     * The root ManagedEntity to start the search under.
     *
     * @param   selectionSpecs
     *
     * A SelectionSpec, usually generated by <b>PropertyCollectorUtils</b> provided
     * by the VI JAVA Library.
     *
     * @param   propertySpecBuilderClosure
     *
     * The Closure for the <b>PropertySpecBuilder</b> to execute, which should contain
     * the instructions for building the PropertySpecs.
     *
     * @return
     *
     * The ObjectContent collection (to be parsed and interpreted).
     *
     * @throws  InvalidType
     *
     * Thrown if there is an invalid ManagedObject type in the PropertySpec collection.
     *
     * @throws  InvalidProperty
     *
     * Thrown if there exists a property requested for a particular type that does not exist for that
     * type within the PropertySpec collection.
     */
    static List<ObjectContent> collectProperties(ManagedEntity root, SelectionSpec[] selectionSpecs, Closure propertySpecBuilderClosure) throws InvalidType, InvalidProperty {
        PropertyFilterSpec[] propertyFilterSpecs = new PropertyFilterSpecBuilder({
            addPropertyFilterSpec(root.MOR, selectionSpecs, propertySpecBuilderClosure)
        }).propertyFilterSpecs

        return collectProperties(root.serverConnection.serviceInstance, propertyFilterSpecs)
    }

    /**
     * Retrieve a Collection of Properties.
     *
     * <p>
     * Short-hand for retrieving properties under one root.
     * </p>
     *
     * @param   root
     *
     * The root ManagedEntity to start the search under.
     *
     * @param   selectionSpecs
     *
     * A SelectionSpec, usually generated by <b>PropertyCollectorUtils</b> provided
     * by the VI JAVA Library.
     *
     * @param   propertySpecs
     *
     * The PropertySpec collection created outside of the Builder.
     *
     * @return
     *
     * The ObjectContent collection (to be parsed and interpreted).
     *
     * @throws  InvalidType
     *
     * Thrown if there is an invalid ManagedObject type in the PropertySpec collection.
     *
     * @throws  InvalidProperty
     *
     * Thrown if there exists a property requested for a particular type that does not exist for that
     * type within the PropertySpec collection.
     */
    static List<ObjectContent> collectProperties(ManagedEntity root, SelectionSpec[] selectionSpecs, PropertySpec[] propertySpecs) throws InvalidType, InvalidProperty {
        PropertyFilterSpec[] propertyFilterSpecs = new PropertyFilterSpecBuilder({
            addPropertyFilterSpec(root.MOR, selectionSpecs, propertySpecs)
        }).propertyFilterSpecs

        return collectProperties(root.serverConnection.serviceInstance, propertyFilterSpecs)
    }

    /**
     * Retrieve a Collection of Properties.
     *
     * @param   serviceInstance
     *
     * The <code>ServiceInstance</code> into the vCenter.
     *
     * @param   propertyFilterSpecBuilderClosure
     *
     * The Closure for the <b>PropertyFilterSpecBuilder</b> to execute, which should contain
     * the instructions for building the PropertyFilterSpecs.
     *
     * @return
     *
     * The ObjectContent collection (to be parsed and interpreted).
     *
     * @throws  InvalidType
     *
     * Thrown if there is an invalid ManagedObject type in the PropertySpec collection.
     *
     * @throws  InvalidProperty
     *
     * Thrown if there exists a property requested for a particular type that does not exist for that
     * type within the PropertySpec collection.
     */
    static List<ObjectContent> collectProperties(ServiceInstance serviceInstance, Closure propertyFilterSpecBuilderClosure) throws InvalidType, InvalidProperty {
        PropertyFilterSpec[] propertyFilterSpecs = new PropertyFilterSpecBuilder(propertyFilterSpecBuilderClosure).propertyFilterSpecs

        return collectProperties(serviceInstance, propertyFilterSpecs)
    }

    /**
     * Retrieve a Collection of Properties.
     *
     * @param   serviceInstance
     *
     * The <code>ServiceInstance</code> into the vCenter.
     *
     * @param   propertyFilterSpecs
     *
     * The PropertyFilterSpec collection.
     *
     * @return
     *
     * The ObjectContent collection (to be parsed and interpreted).
     *
     * @throws  InvalidType
     *
     * Thrown if there is an invalid ManagedObject type in the PropertySpec collection.
     *
     * @throws  InvalidProperty
     *
     * Thrown if there exists a property requested for a particular type that does not exist for that
     * type within the PropertySpec collection.
     */
    static List<ObjectContent> collectProperties(ServiceInstance serviceInstance, PropertyFilterSpec[] propertyFilterSpecs) throws InvalidType, InvalidProperty {
        return serviceInstance.vimService.retrieveProperties(serviceInstance.propertyCollector.MOR, propertyFilterSpecs)
    }

    /**
     * Collate <code>ObjectContent</code> Objects by <code>ManagedEntity</code>.
     *
     * @param   objectContents
     *
     * The <code>ObjectContent</code> List to collate.
     *
     * @return
     *
     * A <code>List</code> of <code>Lists</code> of <code>ObjectContent</code> objects,
     * of which each sub-List contains only those <code>ObjectContent</code> Objects that
     * belong to the same <code>ManagedEntity</code>.
     */
    static List<List<ObjectContent>> collateObjectContent(List<ObjectContent>objectContents) {
        return objectContents.obj.unique(false).collect { managedEntity -> objectContents.findAll { it.obj == managedEntity } }
    }

    /**
     * Parse <code>ObjectContent</code> Objects into <code>Maps</code>.
     *
     * @param   objectContents
     *
     * The <code>ObjectContent</code> List to collate.
     *
     * @return
     *
     * A <code>List</code> of <code>Maps</code>, where each <code>Map</code> is a mutation
     * of the <code>ObjectProperty</code> Objects associated with a <code>ManagedEntity</code>; the
     * keys are the property name and the values are the property values.
     */
    static List<Map<String, Object>> parseObjectContent(List<ObjectContent> objectContent) {
        List parsed = objectContent.obj.unique(false).collect { managedEntity ->
            List related = objectContent.findAll { it.obj == managedEntity }

            Map properties = [:]
            related.each { objectProperty ->
                objectProperty.propSet.each { propertySet ->
                    properties << [(propertySet.name): propertySet.val]
                }
            }
            return properties
        }

        return parsed
    }
}
