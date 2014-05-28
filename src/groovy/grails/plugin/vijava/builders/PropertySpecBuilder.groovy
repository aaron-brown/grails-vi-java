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
package grails.plugin.vijava.builders

import com.vmware.vim25.PropertySpec

/**
 * Builder for creating PropertySpec collections.
 *
 * @author Aaron Brown
 */
class PropertySpecBuilder {

    /**
     * The PropertySpec collection.
     */
    List<PropertySpec> propertySpecs

    /**
     * Constructs the <b>PropertySpecBuilder</b>.
     */
    public PropertySpecBuilder() {
        propertySpecs = [] as PropertySpec[]
    }

    /**
     * Constructs the <b>PropertySpecBuilder</b> and directly runs a
     * <code>Closure</code>.
     */
    public PropertySpecBuilder(Closure closure) {
        this()

        Closure runClosure = closure.clone()
        runClosure.delegate = this
        runClosure.resolveStrategy = Closure.DELEGATE_FIRST
        runClosure()
    }

    /**
     * Add a PropertySpecs to the internal Builder structure.
     *
     * <p>
     * For mass-additions. The key of each map corresponds to the <b>type</b> parameter, and the
     * value is a List of the <b>properties</b>.
     * </p>
     *
     * <p>
     * Example:
     *
     * <code><pre>
     * // Equivalent to
     * //
     * // hostSystem('name', 'datastore')
     * // virtualMachine('name', 'config.uuid')
     *
     * addSpecs(
     *     HostSystem: ['name', 'datastore'],
     *     VirtualMachine: ['name', 'config.uuid']
     * )
     * </pre></code>
     * </p>
     *
     * @param   specDeclaration
     *
     * The declaration of PropertySpec in <code>Map</code>-form
     * (see method description for more details).
     *
     * @return
     *
     * The created PropertySpec, which is also added to the internal collection.
     *
     * @return
     *
     * The created PropertySpecs, which are also added to the internal collection.
     */
    public List<PropertySpec> addSpecs(Map<String, List<String>> specDeclaration) {
        return specDeclaration.collect { type, properties -> addSpec(type, properties) }
    }

    /**
     * Add a PropertySpec to the internal Builder structure.
     *
     * <p>
     * Other mnemonics, like <code>hostSystem</code> use this method. If you
     * are missing a mnemonic for the ManagedObject type you are looking for,
     * you can use this method in place of that, or contribute by adding the
     * mnemonic.
     * </p>
     *
     * @param   managedObjectType
     *
     * The Type of the Managed Object (i.e. VirtualMachine, HostSystem, etc.)
     *
     * @param   properties
     *
     * The Properties to search for.
     *
     * @return
     *
     * The created PropertySpec, which is also added to the internal collection.
     *
     *
     * @return
     *
     * The created PropertySpec, which is also added to the internal collection.
     */
    public PropertySpec addSpec(String managedObjectType, List<String> properties) {
        return addSpec(managedObjectType, properties as String[])
    }

    /**
     * Add a PropertySpec to the internal Builder structure.
     *
     * <p>
     * Other mnemonics, like <code>hostSystem</code> use this method. If you
     * are missing a mnemonic for the ManagedObject type you are looking for,
     * you can use this method in place of that, or contribute by adding the
     * mnemonic.
     * </p>
     *
     * @param   managedObjectType
     *
     * The Type of the Managed Object (i.e. VirtualMachine, HostSystem, etc.)
     *
     * @param   properties
     *
     * The Properties to search for.
     *
     * @return
     *
     * The created PropertySpec, which is also added to the internal collection.
     */
    public PropertySpec addSpec(String managedObjectType, String... properties) {
        PropertySpec propertySpec = new PropertySpec()
        propertySpec.with {
            type = managedObjectType
            all = false
            pathSet = properties
        }

        propertySpecs << propertySpec

        return propertySpec
    }

    // For ease of maintenance, please keep all mnemonics in alphabetical
    // order.

    /**
     * Build a PropertySpec for ClusterComputeResource Objects.
     *
     * @param   properties
     *
     * The Properties to search for.
     *
     * @return
     *
     * The created PropertySpec, which is also added to the internal collection.
     */
    public PropertySpec clusterComputeResource(List<String> properties) {
        return clusterComputeResource(properties as String[])
    }

    /**
     * Build a PropertySpec for ClusterComputeResource Objects.
     *
     * @param   properties
     *
     * The Properties to search for.
     *
     * @return
     *
     * The created PropertySpec, which is also added to the internal collection.
     */
    public PropertySpec clusterComputeResource(String... properties) {
        return addSpec('ClusterComputeResource', properties)
    }

    /**
     * Build a PropertySpec for Datastore Objects.
     *
     * @param   properties
     *
     * The Properties to search for.
     *
     * @return
     *
     * The created PropertySpec, which is also added to the internal collection.
     */
    public PropertySpec datastore(List<String> properties) {
        return datastore(properties as String[])
    }

    /**
     * Build a PropertySpec for Datastore Objects.
     *
     * @param   properties
     *
     * The Properties to search for.
     *
     * @return
     *
     * The created PropertySpec, which is also added to the internal collection.
     */
    public PropertySpec datastore(String... properties) {
        return addSpec('Datastore', properties)
    }

    /**
     * Build a PropertySpec for DistributedVirtualPortgroup Objects.
     *
     * @param   properties
     *
     * The Properties to search for.
     *
     * @return
     *
     * The created PropertySpec, which is also added to the internal collection.
     */
    public PropertySpec distributedVirtualPortgroup(List<String> properties) {
        return distributedVirtualPortgroup(properties as String[])
    }

    /**
     * Build a PropertySpec for DistributedVirtualPortgroup Objects.
     *
     * @param   properties
     *
     * The Properties to search for.
     *
     * @return
     *
     * The created PropertySpec, which is also added to the internal collection.
     */
    public PropertySpec distributedVirtualPortgroup(String... properties) {
        return addSpec('DistributedVirtualPortgroup', properties)
    }

    /**
     * Build a PropertySpec for HostStorageSystem Objects.
     *
     * @param   properties
     *
     * The Properties to search for.
     *
     * @return
     *
     * The created PropertySpec, which is also added to the internal collection.
     */
    public PropertySpec hostStorageSystem(List<String> properties) {
        return hostSystem(properties as String[])
    }

    /**
     * Build a PropertySpec for HostStorageSystem Objects.
     *
     * @param   properties
     *
     * The Properties to search for.
     *
     * @return
     *
     * The created PropertySpec, which is also added to the internal collection.
     */
    public PropertySpec hostStorageSystem(String... properties) {
        return addSpec('HostStorageSystem', properties)
    }

    /**
     * Build a PropertySpec for HostSystem Objects.
     *
     * @param   properties
     *
     * The Properties to search for.
     *
     * @return
     *
     * The created PropertySpec, which is also added to the internal collection.
     */
    public PropertySpec hostSystem(List<String> properties) {
        return hostSystem(properties as String[])
    }

    /**
     * Build a PropertySpec for HostSystem Objects.
     *
     * @param   properties
     *
     * The Properties to search for.
     *
     * @return
     *
     * The created PropertySpec, which is also added to the internal collection.
     */
    public PropertySpec hostSystem(String... properties) {
        return addSpec('HostSystem', properties)
    }

    /**
     * Build a PropertySpec for Network Objects.
     *
     * @param   properties
     *
     * The Properties to search for.
     *
     * @return
     *
     * The created PropertySpec, which is also added to the internal collection.
     */
    public PropertySpec network(List<String> properties) {
        return network(properties as String[])
    }

    /**
     * Build a PropertySpec for Network Objects.
     *
     * @param   properties
     *
     * The Properties to search for.
     *
     * @return
     *
     * The created PropertySpec, which is also added to the internal collection.
     */
    public PropertySpec network(String... properties) {
        return addSpec('Network', properties)
    }

    /**
     * Build a PropertySpec for VirtualMachine Objects.
     *
     * @param   properties
     *
     * The Properties to search for.
     *
     * @return
     *
     * The created PropertySpec, which is also added to the internal collection.
     */
    public PropertySpec virtualMachine(List<String> properties) {
        return virtualMachine(properties as String[])
    }

    /**
     * Build a PropertySpec for VirtualMachine Objects.
     *
     * @param   properties
     *
     * The Properties to search for.
     *
     * @return
     *
     * The created PropertySpec, which is also added to the internal collection.
     */
    public PropertySpec virtualMachine(String... properties) {
        return addSpec('VirtualMachine', properties)
    }
}
