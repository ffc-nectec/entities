package ffc.entity.gson

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import ffc.entity.healthcare.Disease
import ffc.entity.healthcare.Icd10
import java.lang.reflect.Type

class DiseaseJsonAdapter : JsonDeserializer<Disease>, JsonSerializer<Disease> {

    override fun serialize(src: Disease, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        return context.serialize(src)
    }

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Disease {
        return when (json.asJsonObject.get("type").asString) {
            Icd10::class.java.simpleName -> context.deserialize<Icd10>(json)
            else -> context.deserialize(json)
        }
    }
}
