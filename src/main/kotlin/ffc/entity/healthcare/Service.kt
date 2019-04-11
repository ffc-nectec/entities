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

package ffc.entity.healthcare

import ffc.entity.Entity
import ffc.entity.Link
import ffc.entity.util.generateTempId
import me.piruin.geok.geometry.Point
import org.joda.time.DateTime

abstract class Service(
    val providerId: String,
    val patientId: String,
    id: String = generateTempId()
) : Entity(id) {
    var time: DateTime = DateTime.now()
        set(value) {
            field = value
            endTime = value.plusMinutes(5)
        }
    var endTime: DateTime = time.plusMinutes(5)
        set(value) {
            require(value.isAfter(time)) { "endTime must be after time" }
            field = value
        }
    var location: Point? = null
    var link: Link? = null

    fun mapTo(other: Service) {
        other.let {
            it.time = time
            it.endTime = endTime
            it.location = location
        }
    }
}
