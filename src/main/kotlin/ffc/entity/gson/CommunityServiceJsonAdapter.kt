package ffc.entity.gson

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import ffc.entity.healthcare.CommunityService
import ffc.entity.healthcare.HomeVisit
import java.lang.reflect.Type

class CommunityServiceJsonAdapter : JsonDeserializer<CommunityService>, JsonSerializer<CommunityService> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): CommunityService {
        return when (json.asJsonObject.get("type").asString) {
            HomeVisit::class.java.simpleName -> context.deserialize<HomeVisit>(json)
            else -> context.deserialize(json)
        }
    }

    override fun serialize(
        src: CommunityService,
        typeOfSrc: Type,
        context: JsonSerializationContext
    ): JsonElement {
        return context.serialize(src)
    }
}
