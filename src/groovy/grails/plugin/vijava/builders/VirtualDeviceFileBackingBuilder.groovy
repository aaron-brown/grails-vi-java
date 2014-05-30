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

import com.vmware.vim25.VirtualDiskMode
import com.vmware.vim25.VirtualDeviceFileBackingInfo
import com.vmware.vim25.VirtualCdromIsoBackingInfo
import com.vmware.vim25.VirtualDiskFlatVer1BackingInfo
import com.vmware.vim25.VirtualDiskFlatVer2BackingInfo
import com.vmware.vim25.VirtualDiskRawDiskMappingVer1BackingInfo
import com.vmware.vim25.VirtualDiskSeSparseBackingInfo
import com.vmware.vim25.VirtualDiskSparseVer1BackingInfo
import com.vmware.vim25.VirtualDiskSparseVer2BackingInfo
import com.vmware.vim25.VirtualFloppyImageBackingInfo
import com.vmware.vim25.VirtualParallelPortFileBackingInfo
import com.vmware.vim25.VirtualSerialPortFileBackingInfo

/**
 * Builder for creating <code>VirtualDeviceFileBackingInfo</code> Objects.
 *
 * @author Aaron Brown
 */
class VirtualDeviceFileBackingBuilder {

    private VirtualDeviceFileBackingInfo getInstance(Class clazz, String fileName) {
        VirtualDeviceFileBackingInfo backingInfo = clazz.newInstance()
        backingInfo.fileName = fileName
        return backingInfo
    }

    static virtualCdromIsoBackingInfo(String fileName) {
        return getInstance(VirtualCdromIsoBackingInfo, fileName)
    }

    static virtualDiskFlatVer1BackingInfo(String fileName, VirtualDiskMode diskMode) {
        return virtualDiskFlatVer1BackingInfo((null as Map), fileName, convertDiskMode(diskMode))
    }

    static virtualDiskFlatVer1BackingInfo(Map<String, Object> optionalFields, String fileName, VirtualDiskMode diskMode) {
        return virtualDiskFlatVer1BackingInfo(optionalFields, fileName, convertDiskMode(diskMode))
    }

    static virtualDiskFlatVer1BackingInfo(String fileName, String diskMode) {
        return virtualDiskFlatVer1BackingInfo((null as Map), fileName, diskMode)
    }

    static virtualDiskFlatVer1BackingInfo(Map<String, Object> optionalFields, String fileName, String diskMode) {
        VirtualDiskFlatVer1BackingInfo backingInfo = getInstance(VirtualDiskFlatVer1BackingInfo, fileName)

        optionalFields = optionalFields ?: [:]

        backingInfo.diskMode = diskMode
        backingInfo.with {
            contentId = optionalFields.contentId
            parent = optionalFields.parent
            split = optionalFields.split
            writeThrough = optionalFields.writeThrough
        }

        return backingInfo
    }

    static virtualDiskFlatVer2BackingInfo(String fileName, VirtualDiskMode diskMode) {
        return virtualDiskFlatVer2BackingInfo((null as Map), fileName, convertDiskMode(diskMode))
    }

    static virtualDiskFlatVer2BackingInfo(Map<String, Object> optionalFields, String fileName, VirtualDiskMode diskMode) {
        return virtualDiskFlatVer2BackingInfo(optionalFields, fileName, convertDiskMode(diskMode))
    }

    static virtualDiskFlatVer2BackingInfo(String fileName, String diskMode) {
        return virtualDiskFlatVer2BackingInfo((null as Map), fileName, diskMode)
    }

    static virtualDiskFlatVer2BackingInfo(Map<String, Object> optionalFields, String fileName, String diskMode) {
        VirtualDiskFlatVer2BackingInfo backingInfo = getInstance(VirtualDiskFlatVer2BackingInfo, fileName)

        optionalFields = optionalFields ?: [:]

        backingInfo.diskMode = diskMode
        backingInfo.with {
            changeId = optionalFields.changeId
            contentId = optionalFields.contentId
            deltaDiskFormat = optionalFields.deltaDiskFormat
            deltaGrainSize = optionalFields.deltaGrainSize
            digestEnabled = optionalFields.digestEnabled
            eagerlyScrub = optionalParams.eagerlyScrub
            parent = optionalFields.parent
            split = optionalFields.split
            thinProvisioned = optionalFields.thinProvisioned
            uuid = optionalFields.uuid
            writeThrough = optionalFields.writeThrough
        }

        return backingInfo
    }

    static virtualDiskRawDiskMappingVer1BackingInfo(String fileName) {
        return virtualDiskRawDiskMappingVer1BackingInfo((null as Map), fileName)
    }

    static virtualDiskRawDiskMappingVer1BackingInfo(Map<String, Object> optionalFields, String fileName) {
        VirtualDiskRawDiskMappingVer1BackingInfo backingInfo = getInstance(VirtualDiskRawDiskMappingVer1BackingInfo, fileName)

        optionalFields = optionalFields ?: [:]

        if (optionalFields.diskMode && optionalFields.diskMode.getClass() == VirtualDiskMode) {
            optionalFields.diskMode = convertDiskMode(optionalFields.diskMode)
        }

        backingInfo.with {
            changeId = optionalFields.changeId
            compatibilityMode = optionalFields.compatibilityMode
            contentId = optionalFields.contentId
            deviceName = optionalFields.deviceName
            diskMode = optionalFields.diskMode
            lunUuid = optionalFields.lunUuid
            parent = optionalFields.parent
            uuid = optionalFields.uuid
        }

        return backingInfo
    }

    static virtualDiskSeSparseBackingInfo(String fileName, VirtualDiskMode diskMode) {
        return virtualDiskSeSparseBackingInfo((null as Map), fileName, convertDiskMode(diskMode))
    }

    static virtualDiskSeSparseBackingInfo(Map<String, Object> optionalFields, String fileName, VirtualDiskMode diskMode) {
        return virtualDiskSeSparseBackingInfo(optionalFields, fileName, convertDiskMode(diskMode))
    }

    static virtualDiskSeSparseBackingInfo(String fileName, String diskMode) {
        return virtualDiskSeSparseBackingInfo((null as Map), fileName, diskMode)
    }

    static virtualDiskSeSparseBackingInfo(Map<String, Object> optionalFields, String fileName, String diskMode) {
        VirtualDiskSeSparseBackingInfo backingInfo = getInstance(VirtualDiskSeSparseBackingInfo, fileName)

        optionalFields = optionalFields ?: [:]

        backingInfo.diskMode = diskMode
        backingInfo.with {
            changeId = optionalFields.changeId
            contentId = optionalFields.contentId
            deltaDiskFormat = optionalFields.deltaDiskFormat
            deltaGrainSize = optionalFields.deltaGrainSize
            digestEnabled = optionalFields.digestEnabled
            grainSize = optionalFields.grainSize
            parent = optionalFields.parent
            uuid = optionalFields.uuid
            writeThrough = optionalFields.writeThrough
        }

        return backingInfo
    }

    static virtualDiskSparseVer1BackingInfo(String fileName, VirtualDiskMode diskMode) {
        return virtualDiskSparseVer1BackingInfo((null as Map), fileName, convertDiskMode(diskMode))
    }

    static virtualDiskSparseVer1BackingInfo(Map<String, Object> optionalFields, String fileName, VirtualDiskMode diskMode) {
        return virtualDiskSparseVer1BackingInfo(optionalFields, fileName, convertDiskMode(diskMode))
    }

    static virtualDiskSparseVer1BackingInfo(String fileName, String diskMode) {
        return virtualDiskSparseVer1BackingInfo((null as Map), fileName, diskMode)
    }

    static virtualDiskSparseVer1BackingInfo(Map<String, Object> optionalFields, String fileName, String diskMode) {
        VirtualDiskSparseVer1BackingInfo backingInfo = getInstance(VirtualDiskSparseVer1BackingInfo, fileName)

        optionalFields = optionalFields ?: [:]

        backingInfo.diskMode = diskMode
        backingInfo.with {
            contentId = optionalFields.contentId
            parent = optionalFields.parent
            spaceUsagedInKB = optionalFields.spaceUsedInKB
            split = optionalFields.split
            writeThrough = optionalFields.writeThrough
        }

        return backingInfo
    }

    static virtualDiskSparseVer2BackingInfo(String fileName, VirtualDiskMode diskMode) {
        return virtualDiskSparseVer2BackingInfo((null as Map), fileName, convertDiskMode(diskMode))
    }

    static virtualDiskSparseVer2BackingInfo(Map<String, Object> optionalFields, String fileName, VirtualDiskMode diskMode) {
        return virtualDiskSparseVer2BackingInfo(optionalFields, fileName, convertDiskMode(diskMode))
    }

    static virtualDiskSparseVer2BackingInfo(String fileName, String diskMode) {
        return virtualDiskSparseVer2BackingInfo((null as Map), fileName, diskMode)
    }

    static virtualDiskSparseVer2BackingInfo(Map<String, Object> optionalFields, String fileName, String diskMode) {
        VirtualDiskSparseVer2BackingInfo backingInfo = getInstance(VirtualDiskSparseVer2BackingInfo, fileName)

        optionalFields = optionalFields ?: [:]

        backingInfo.diskMode = diskMode
        backingInfo.with {
            changeId = optionalFields.changeId
            contentId = optionalFields.contentId
            parent = optionalFields.parent
            spaceUsagedInKB = optionalFields.spaceUsedInKB
            split = optionalFields.split
            uuid = optionalFields.uuid
            writeThrough = optionalFields.writeThrough
        }

        return backingInfo
    }

    static virtualFloppyImageBackingInfo(String fileName) {
        return getInstance(VirtualFloppyImageBackingInfo, fileName)
    }

    static virtualParallelPortFileBackingInfo(String fileName) {
        return getInstance(VirtualParallelPortFileBackingInfo, fileName)
    }

    static virtualSerialPortFileBackingInfo(String fileName) {
        return getInstance(VirtualSerialPortFileBackingInfo, fileName)
    }

    /**
     * Convert a <code>VirtualDiskMode</code> to the appropriate String value.
     *
     * <p>Since the enum doesn't have a way to get the value...</p>
     *
     * @param   diskMode
     *
     * The <code>VirtualDiskMode</code> enum Object to convert.
     *
     * @return
     *
     * The appropriate disk mode String, or <code>null</code> if <code>diskMode</code>
     * is <code>null</code>.
     */
    private String convertDiskMode(VirtualDiskMode diskMode) {
        switch (diskMode) {
            case (VirtualDiskMode.nonpersistent):
                return 'nonpersistent'
            case (VirtualDiskMode.undoable):
                return 'undoable'
            case (VirtualDiskMode.independent_persistent):
                return 'independent_persistent'
            case (VirtualDiskMode.independent_nonpersistent):
                return 'independent_nonpersistent'
            case (VirtualDiskMode.append):
                return 'append'
            case (VirtualDiskMode.persistent):
                return 'persistent'
            default:
                return null
        }
    }
}
