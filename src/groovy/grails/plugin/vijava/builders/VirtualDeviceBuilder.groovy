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

import com.vmware.vim25.VirtualDisk
import com.vmware.vim25.VirtualDevice

/**
 * Builder for creating <code>VirtualDeviceConfigSpec</code> Objects.
 *
 * @author Aaron Brown
 */
class VirtualDeviceBuilder {

    private VirtualDevice getInstance(Class clazz, int key) {
        return getInstance((null as Map), clazz, key)
    }

    private VirtualDevice getInstance(Map<String, Object> optionalFields, Class clazz, int key) {
        VirtualDevice virtualDevice = clazz.newInstance()
        virtualDevice.key = key

        optionalFields = optionalFields ?: [:]
        virtualDevice.with {
            backing = optionalFields.backing,
            connectable = optionalFields.connectable
            controllerKey = optionalFields.controllerKey
            deviceInfo = optionalFields.deviceInfo
            slotInfo = optionalFields.slotInfo
            nitNumber = optionalFields.unitNumber
        }

        return virtualDevice
    }

    static virtualDisk(Map<String, Object> optionalFields, int key, long capacityInKB) {
        VirtualDisk virtualDisk = getInstance(optionalFields, VirtualDisk, key)
        virtualDisk.capacityInKB = capacityInKB
        virtualDisk.with {
            shares = optionalFields.shares
            storageIOAllocation = optionalFields.storageIOAllocation
        }

        return virtualDisk
    }
}
