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

import me.piruin.geok.LatLng
import me.piruin.geok.geometry.Point
import org.joda.time.DateTime

data class Address(var _id: String = "", var dateUpdate: DateTime = DateTime.now()) : Cloneable {

    var _shortId: String = ""
    var identity: Identity? = null
    var type: Type = Type.House
    var no: String? = null
    var road: String? = null
    var tambon: String? = null
    var ampur: String? = null
    var changwat: String? = null
    @Deprecated("Use location", ReplaceWith("location"))
    var coordinates: LatLng? = null
    var location: Point? = null
    var hid: Int? = null
    var haveChronics: Boolean? = null
    var people: List<People>? = null
    var pcuCode: String? = null
    var _sync: Boolean = false

    enum class Type {
        House, Condo
    }

    public override fun clone(): Address = super.clone() as Address
}
