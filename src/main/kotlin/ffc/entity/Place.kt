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

import ffc.entity.util.generateTempId
import me.piruin.geok.LatLng
import me.piruin.geok.geometry.Point
import org.joda.time.DateTime

@Deprecated("use place instead", ReplaceWith("Place"), DeprecationLevel.ERROR)
open class Address(id: String = generateTempId()) : Entity(id), Cloneable {

    @Deprecated("use id", ReplaceWith("id"))
    var _id: String = ""

    @Deprecated("use timestamp", ReplaceWith("timestamp"))
    var dateUpdate: DateTime = DateTime.now()

    @Deprecated("Use location", ReplaceWith("location"))
    var coordinates: LatLng? = null

    var no: String? = null
    var road: String? = null
    var location: Point? = null
}

open class Place(id: String = generateTempId()) : Entity(id), Cloneable {
    var no: String? = null
    var road: String? = null
    var location: Point? = null
}

class House(id: String = generateTempId()) : Place(id) {
    var identity: ThaiHouseholdId? = null
    var people: MutableList<Person> = mutableListOf()
    var haveChronic: Boolean = false
        get() = if (people.isNotEmpty()) people.firstOrNull { it.haveChronic } != null else field
    var link: Link? = null
}
