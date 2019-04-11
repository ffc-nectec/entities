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

package ffc.entity.gson

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import ffc.entity.Place
import ffc.entity.place.Business
import ffc.entity.place.House
import ffc.entity.place.ReligiousPlace
import ffc.entity.place.School
import java.lang.reflect.Type

class PlaceJsonAdapter : JsonDeserializer<Place> {

    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext): Place {

        return when (json.asJsonObject.get("type").asString) {
            House::class.java.simpleName -> context.deserialize<House>(json)
            Business::class.java.simpleName -> context.deserialize<Business>(json)
            School::class.java.simpleName -> context.deserialize<School>(json)
            ReligiousPlace::class.java.simpleName -> context.deserialize<ReligiousPlace>(json)
            else -> context.deserialize(json)
        }
    }

    private inline fun <reified T> JsonDeserializationContext.deserialize(json: JsonElement): T {
        return this.deserialize(json, T::class.java)
    }
}
