package ffc.entity.place

import org.amshove.kluent.`should be`
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ReligionTest(val name: String, val expected: Religion) {

    companion object {

        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
                arrayOf("พุทธ", Religion.Buddhism),
                arrayOf("อิสลาม", Religion.Islam),
                arrayOf("คริสต์", Religion.Christianity),
                arrayOf("อื่นๆ", Religion.Etc)
        )
    }

    @Test
    fun fromName() {
        Religion.byName(name) `should be` expected
    }
}
