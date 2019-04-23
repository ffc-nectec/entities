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

import ffc.entity.util.checkValidUrl
import ffc.entity.util.generateTempId
import org.joda.time.DateTime

class User(id: String = generateTempId()) : Entity(id) {

    constructor(
        id: String = generateTempId(),
        name: String,
        password: String,
        vararg roles: Role
    ) : this(id) {
        this.name = name
        this.password = password
        this.roles.addAll(roles)
    }

    lateinit var name: String
    lateinit var password: String

    var orgId: String? = null
    var displayName: String? = null
        get() = if (field != null) field else name
    var avatarUrl: String? = null
        set(url) {
            if (url != null) checkValidUrl(url)
            field = url
        }

    var isActivated: Boolean = false
        private set
    var activateTime: DateTime? = null
        private set

    fun activate() {
        check(isActivated == false) { "User $name already activated" }
        update {
            isActivated = true
            activateTime = DateTime.now()
        }
    }


    val roles: MutableList<Role> = mutableListOf()
    var link: Link? = null

    enum class Role {
        ADMIN,
        PROVIDER,
        SURVEYOR,
        PATIENT
    }
}
