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

package ffc.entity

import org.joda.time.DateTime
import java.util.UUID

const val USER_DATE_EXPIRE = 1
const val ORG_DATE_EXPIRE = 9000

data class TokenMessage(
    val token: UUID,
    var firebaseToken: String? = null,
    val timestamp: DateTime = DateTime.now(),
    val role: TYPEROLE = TYPEROLE.NOAUTH,
    val name: String
) {

    var expireDate: DateTime = when (role) {
        TYPEROLE.USER -> timestamp.plusDays(USER_DATE_EXPIRE)
        TYPEROLE.ORG -> timestamp.plusDays(ORG_DATE_EXPIRE)
        else -> timestamp
    }

    val isNotExpire: Boolean
        get() = !isExpire

    val isExpire: Boolean
        get() = expireDate <= DateTime.now()

    enum class TYPEROLE {
        ORG, USER, NOAUTH
    }
}
