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
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import ffc.entity.healthcare.analyze.HealthChecked
import ffc.entity.healthcare.analyze.HealthIssue
import ffc.entity.healthcare.analyze.HealthProblem
import java.lang.reflect.Type

class HealthIssueJsonAdapter : JsonDeserializer<HealthIssue>, JsonSerializer<HealthIssue> {

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): HealthIssue {
        return when (json.asJsonObject.get("type").asString) {
            HealthChecked::class.java.simpleName -> context.deserialize<HealthChecked>(json)
            HealthProblem::class.java.simpleName -> context.deserialize<HealthProblem>(json)
            else -> context.deserialize(json)
        }
    }

    override fun serialize(src: HealthIssue, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        return context.serialize(src)
    }
}
