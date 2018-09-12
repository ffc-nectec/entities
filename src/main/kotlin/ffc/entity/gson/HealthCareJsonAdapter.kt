package ffc.entity.gson

import com.google.gson.GsonBuilder
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
        val jsonObj = json.asJsonObject
        val gson = GsonBuilder().adapterForExtLibrary()

        return when (jsonObj.get("type").asString) {
            HomeVisit::class.java.simpleName ->
                gson.create().fromJson(jsonObj.toString(), HomeVisit::class.java)
            else -> gson.create().fromJson(json.toString(), HealthCareService::class.java)
        }
    }
}
