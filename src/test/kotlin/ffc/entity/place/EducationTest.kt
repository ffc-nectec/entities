package ffc.entity.place

import org.amshove.kluent.`should be`
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class EducationTest(val name: String, val expected: Education) {

    companion object {

        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
                arrayOf("เตรียมอนุบาล", Education.PreKindergarten),
                arrayOf("ก่อนอนุบาล", Education.PreKindergarten),
                arrayOf("มัธยม", Education.HighSchool),
                arrayOf("ประถม", Education.ElementarySchool)
        )
    }

    @Test
    fun byName() {
        Education.byName(name) `should be` expected
    }
}
