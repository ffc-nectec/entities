/*
 * Copyright (c) 2019 NECTEC
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

import ffc.entity.util.generateTempId
import org.amshove.kluent.`should be`
import org.amshove.kluent.`should equal`
import org.junit.Test

class UserTelTest {

    val user = User(generateTempId(), "piruin", "12345678", User.Role.PROVIDER)

    @Test
    fun setTelNationalFormat() {
        user.tel = "0865678901"

        user.tel `should equal` "+66 86 567 8901"
    }

    @Test
    fun setTelInternationalFormat() {
        user.tel = "+66865678901"

        user.tel `should equal` "+66 86 567 8901"
    }

    @Test
    fun setNull() {
        user.tel = "+66865678901"

        user.tel = null

        user.tel `should be` null
    }

    @Test(expected = IllegalArgumentException::class)
    fun checkInvalidPhoneNumber() {
        user.tel = "+1304930"
    }

    @Test(expected = IllegalArgumentException::class)
    fun setBlank() {
        user.tel = "     "
    }

    @Test(expected = IllegalArgumentException::class)
    fun setEmpty() {
        user.tel = ""
    }
}
