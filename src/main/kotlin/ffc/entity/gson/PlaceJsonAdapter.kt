package ffc.entity.gson

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import ffc.entity.House
import ffc.entity.Place
import ffc.entity.place.Business
import ffc.entity.place.School
import ffc.entity.place.Temple
import java.lang.reflect.Type

class PlaceJsonAdapter : JsonDeserializer<Place>, JsonSerializer<Place> {
    override fun serialize(src: Place?, typeOfSrc: Type?, context: JsonSerializationContext): JsonElement {
        return context.serialize(src)
    }

    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext): Place {

        return when (json.asJsonObject.get("type").asString) {
            House::class.java.simpleName -> context.deserialize<House>(json)
            Business::class.java.simpleName -> context.deserialize<Business>(json)
            School::class.java.simpleName -> context.deserialize<School>(json)
            Temple::class.java.simpleName -> context.deserialize<Temple>(json)
            else -> context.deserialize(json)
        }
    }

    private inline fun <reified T> JsonDeserializationContext.deserialize(json: JsonElement): T {
        return this.deserialize(json, T::class.java)
    }
}
