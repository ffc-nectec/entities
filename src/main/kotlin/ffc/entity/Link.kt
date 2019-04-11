/*
 * Copyright 2019 NECTEC
 *   National Electronics and Computer Technology Center, Thailand
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ffc.entity

import org.joda.time.DateTime
import java.util.concurrent.ConcurrentHashMap

data class Link(val system: System, var keys: MutableMap<String, Any>) {

    var isSynced: Boolean = true
        set(value) {
            field = value
            if (value == true)
                lastSync = DateTime.now()
        }

    var lastSync: DateTime? = DateTime.now()
        private set

    constructor(
        system: System,
        vararg keys: Pair<String, Any>,
        lastSync: DateTime = DateTime.now()
    ) : this(system, keys.toMap(ConcurrentHashMap()).toMutableMap()) {
        this.lastSync = lastSync
    }
}

enum class System {
    JHICS, HOSxP
}
