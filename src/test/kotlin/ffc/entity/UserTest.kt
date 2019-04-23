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
import org.amshove.kluent.`should not be`
import org.junit.Test

class UserTest {

    @Test
    fun activate() {
        val user = User(generateTempId(), "piruin", "12345678", User.Role.PROVIDER)

        user.activate()

        user.isActivated `should be` true
        user.activateTime `should not be` null
    }

    @Test(expected = IllegalStateException::class)
    fun cannotActivateAgain() {
        val user = User(generateTempId(), "piruin", "12345678", User.Role.PROVIDER)
        user.activate()

        user.activate()
    }
}
