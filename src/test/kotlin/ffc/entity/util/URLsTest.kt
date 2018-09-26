package ffc.entity.util

import org.amshove.kluent.`should be equal to`
import org.junit.Test

class URLsTest {

    private val urLs = URLs()

    @Test
    fun valid() {
        urLs.add("https://www.ffc.in.th")
        urLs.addAll(listOf("https://ffc.in.th", "http://ffc.in.th"))

        urLs.size `should be equal to` 3
    }

    @Test(expected = IllegalArgumentException::class)
    fun addInvalidUrl() {
        urLs.add("Foo")
    }

    @Test(expected = IllegalArgumentException::class)
    fun addAllInvalidUrl() {
        urLs.addAll(listOf("Foo", "Bar"))
    }

    @Test(expected = IllegalArgumentException::class)
    fun addAllInvalidUrlArray() {
        urLs.addAll(arrayOf("Foo", "Bar"))
    }
}
