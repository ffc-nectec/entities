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

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

interface Identity {
    val id: String
    val type: String
    fun isValid(): Boolean
}

class ThaiCitizenId(override val id: String) : Identity {
    override val type: String = "thailand-citizen-id"

    override fun isValid(): Boolean = id.length == 13
}

class ThaiHouseholdId(override val id: String) : Identity {
    override val type: String = "thailand-household-id"

    override fun isValid(): Boolean = id.length == 11
}

class IdentityDeserializer : JsonDeserializer<Identity>, JsonSerializer<Identity> {
    override fun serialize(src: Identity?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        return context!!.serialize(src)
    }

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Identity {
        val jsonObj = json.asJsonObject
        return when (jsonObj.get("type").asString) {
            "thailand-citizen-id" -> ThaiCitizenId(jsonObj.get("id").asString)
            "thailand-household-id" -> ThaiHouseholdId(jsonObj.get("id").asString)
            else -> throw IllegalArgumentException("Not support Identity type")
        }
    }
}
