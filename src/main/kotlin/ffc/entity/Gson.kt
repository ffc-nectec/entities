/*
 * Copyright (c) 2561 NECTEC
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

import com.fatboyindustrial.gsonjodatime.Converters
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import me.piruin.geok.LatLng
import me.piruin.geok.gson.LatLngSerializer

val goon = Converters.registerAll(GsonBuilder())
        .adapterFor<LatLng>(LatLngSerializer())
        .adapterFor<Identity>(IdentityDeserializer()).create()

private inline fun <reified T> GsonBuilder.adapterFor(adapter: Any): GsonBuilder {
    return registerTypeAdapter(object : TypeToken<T>() {}.type, adapter)
}

fun Any.toJson(): String {
    return goon.toJson(this)
}

inline fun <reified T> String.fromJson(): T {
    return goon.fromJson(this, T::class.java)
}


