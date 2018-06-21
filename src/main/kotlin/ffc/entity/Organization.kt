/*
 * Copyright (c) 2018 NECTEC
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

import java.util.UUID

data class Organization(val uuid: UUID = UUID.randomUUID()) : Cloneable {

    constructor(uuid: UUID = UUID.randomUUID(), block: Organization.() -> Unit) : this(uuid) {
        apply(block)
    }

    var id: String = "-1"
    var pcuCode: String = "099912"
    var name: String = "NECTEC"
    var session: String? = null
    var lastKnownIp: String? = null
    var token: UUID? = null
    var socketUrl: String? = null
    var firebaseToken: String? = null

    override fun clone(): Organization {
        return super.clone() as Organization
    }
}
