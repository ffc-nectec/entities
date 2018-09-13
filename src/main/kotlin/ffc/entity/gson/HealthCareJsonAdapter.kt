package ffc.entity.gson

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import ffc.entity.healthcare.HealthCareService
import ffc.entity.healthcare.HomeVisit
import java.lang.reflect.Type

class HealthCareJsonAdapter : JsonDeserializer<HealthCareService>, JsonSerializer<HealthCareService> {

    override fun serialize(src: HealthCareService?, typeOfSrc: Type?, context: JsonSerializationContext): JsonElement {
        return context.serialize(src)
    }

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): HealthCareService {

        return when (json.asJsonObject.get("type").asString) {
            HomeVisit::class.java.simpleName ->
                context!!.deserialize<HomeVisit>(json)
            else -> context!!.deserialize(json)
        }
    }

    private inline fun <reified T> JsonDeserializationContext.deserialize(json: JsonElement): T {
        return this.deserialize(json, T::class.java)
    }
}
