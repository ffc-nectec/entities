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

import ffc.entity.util.checkValidUrl
import ffc.entity.util.generateTempId

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

    @Deprecated("use name instead", ReplaceWith("name"))
    val username: String? = null

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

    @Deprecated("use rolse", ReplaceWith("roles"))
    var role: Role = Role.USER

    val roles: MutableList<Role> = mutableListOf()
    var link: Link? = null

    enum class Role {
        @Deprecated("Use admin")
        ORG,
        @Deprecated("Define as Provider, Surveyor or Patient")
        USER,
        ADMIN,
        PROVIDER,
        SURVEYOR,
        PATIENT
    }
}
