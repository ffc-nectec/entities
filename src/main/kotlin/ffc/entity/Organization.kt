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
import me.piruin.geok.geometry.Point

class Organization(id: String = generateTempId()) : Entity(id), Cloneable {

    var name: String = "NECTEC"
    var displayName: String? = null
        get() = if (field != null) field else name
    var avatarUrl: String? = null
        set(url) {
            if (url != null) checkValidUrl(url)
            field = url
        }

    var tel: String? = null
    var address: String? = null
    var location: Point? = null
    var users: MutableList<User> = mutableListOf()

    var link: Link? = null
}
