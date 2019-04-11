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

enum class Religion(val th: String) {
    Buddhism("พุทธ"),
    Islam("อิสลาม"),
    Christianity("คริสต์"),
    Hinduism("ฮินดู"),
    Sikhism("ซิกซ์"),
    Judaism("ยิว"),
    Zen("เซน"),
    Zoroastrianism("โซโรอัสเตอร์"),
    BahiFaith("บาไฮ"),
    Etc("อื่นๆ");

    companion object {
        fun byName(name: String): Religion {
            val religion = Religion.values().firstOrNull { name == it.th }
            return religion ?: throw IllegalArgumentException("Not found religion for $name")
        }
    }
}
