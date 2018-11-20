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
