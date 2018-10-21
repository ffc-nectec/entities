package ffc.entity.gson

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import ffc.entity.Place
import ffc.entity.place.Business
import ffc.entity.place.House
import ffc.entity.place.ReligiousPlace
import ffc.entity.place.School
import java.lang.reflect.Type
import ffc.entity.House as OldHouse

class PlaceJsonAdapter : JsonDeserializer<Place>, JsonSerializer<Place> {
    override fun serialize(src: Place?, typeOfSrc: Type?, context: JsonSerializationContext): JsonElement {
        return context.serialize(src)
    }

    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext): Place {

        return when (json.asJsonObject.get("type").asString) {
            OldHouse::class.java.simpleName -> context.deserialize<OldHouse>(json)
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
