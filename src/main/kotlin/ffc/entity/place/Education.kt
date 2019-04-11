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

package ffc.entity.place

enum class Education(val names: List<String>) {
    PreKindergarten("เตรียมอนุบาล", "ก่อนอนุบาล"),
    Kindergarten("อนุบาล"),
    ElementarySchool("ประถม"),
    JuniorHighSchool("มัธยมต้น"),
    HighSchool("มัธยม", "มัธยมปลาย"),
    TechnicalCollege("วิทยาลัยเทคนิค"),
    College("วิทยาลัย"),
    University("มหาวิทยาลัย");

    constructor(vararg namesArg: String) : this(namesArg.toList())

    companion object {
        fun byName(name: String): Education {
            val education = Education.values().firstOrNull { it.names.contains(name) }
            return education ?: throw IllegalArgumentException("Not found Education of $name")
        }
    }
}
