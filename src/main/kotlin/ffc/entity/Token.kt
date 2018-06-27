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

const val USER_DATE_EXPIRE = 1
const val ORG_DATE_EXPIRE = 9000

data class Token(
    val user: User,
    val token: String
) {
    @Deprecated("check at user", level = DeprecationLevel.ERROR)
    val role = User.Role.USER

    @Deprecated("not need", level = DeprecationLevel.ERROR)
    val name: String = ""

    @Deprecated("Use createDate", ReplaceWith("createDate"))
    val timestamp: DateTime = DateTime.now()

    val createDate: DateTime = DateTime.now()
    var expireDate: DateTime = when (user.role) {
        User.Role.ORG -> createDate.plusDays(ORG_DATE_EXPIRE)
        else -> createDate.plusDays(USER_DATE_EXPIRE)
    }

    val isNotExpire: Boolean
        get() = !isExpire

    val isExpire: Boolean
        get() = expireDate <= DateTime.now()
}
