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

package ffc.entity.gson

import com.fatboyindustrial.gsonjodatime.DateTimeConverter
import com.fatboyindustrial.gsonjodatime.LocalDateConverter
import com.fatboyindustrial.gsonjodatime.LocalDateTimeConverter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import ffc.entity.Identity
import ffc.entity.Place
import ffc.entity.User
import ffc.entity.healthcare.CommunityService
import ffc.entity.healthcare.Disease
import ffc.entity.util.URLs
import me.piruin.geok.LatLng
import me.piruin.geok.geometry.Geometry
import me.piruin.geok.gson.GeometrySerializer
import me.piruin.geok.gson.LatLngSerializer
import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import java.lang.reflect.Type

val ffcGson: Gson by lazy {
    GsonBuilder()
            .setExclusionStrategies(ExcludeAnnotationStrategy())
            .adapterFor<User>(UserJsonAdapter())
            .adapterFor<Identity>(IdentityJsonAdapter())
            .adapterFor<CommunityService>(CommunityServiceJsonAdapter())
            .adapterFor<Disease>(DiseaseJsonAdapter())
            .adapterFor<Place>(PlaceJsonAdapter())
            .adapterFor<URLs>(URLsJsonAdapter())
            .adapterForExtLibrary()
            .create()
}

internal fun GsonBuilder.adapterForExtLibrary(): GsonBuilder {
    adapterFor<Geometry>(GeometrySerializer())
    adapterFor<LatLng>(LatLngSerializer())
    adapterFor<DateTime>(DateTimeConverter())
    adapterFor<LocalDate>(LocalDateConverter())
    adapterFor<LocalDateTime>(LocalDateTimeConverter())
    return this
}

private inline fun <reified T> GsonBuilder.adapterFor(adapter: Any): GsonBuilder {
    return registerTypeAdapter(typeTokenOf<T>(), adapter)
}

inline fun <reified T> typeTokenOf(): Type = object : TypeToken<T>() {}.type

fun Any.toJson(gson: Gson = ffcGson): String = gson.toJson(this)

inline fun <reified T> String.parseTo(gson: Gson = ffcGson): T = gson.fromJson(this, typeTokenOf<T>())

internal inline fun <reified T> JsonDeserializationContext.deserialize(json: JsonElement): T {
    return this.deserialize(json, T::class.java)
}
