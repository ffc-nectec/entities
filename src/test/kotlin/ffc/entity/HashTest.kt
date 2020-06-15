package ffc.entity

import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should not be equal to`
import org.junit.Test

class HashTest {
    val hash = Hash()
    val exam1 = "3zCwrRVKYTZhzxwY83NqtN59tVARgN2G"
    val exam2 = """Hj8^E3v%?vnq?N6n5V6?n3qswxLSB=Va"""

    @Test
    fun validSimple() {
        hash.simple(exam1) `should be equal to` "09fd51f5ecb4e7102241afb6e2586548357187dedaf32320e0f7ee430525535d"
    }

    @Test
    fun invalidSimple() {
        hash.simple(exam1) `should not be equal to` "08d24b8eeed8a31d49ae6332262207115da08a386a4b28eb4ea441341a451d53"
    }

    @Test
    fun validSimple2() {
        hash.simple(exam2) `should be equal to` "a4ce318f4972dd95a8ce14a5d664e5eb190b4ca459b75e5160c15ac7f000afcb"
    }

    @Test
    fun invalidSimple2() {
        hash.simple(exam2) `should not be equal to` "08d24b8eeed8a31d49ae6332262207115da08a386a4b28eb4ea441341a451d53"
    }

    @Test
    fun hashBest() {
        hash.best(exam1)
        hash.best(exam2)
    }

    @Test
    fun validBest() {
        hash.bestCheck(exam1, hash.best(exam1)) `should be equal to` true
        hash.bestCheck(exam2, hash.best(exam2)) `should be equal to` true
    }

    @Test
    fun invalidBest() {
        hash.bestCheck(exam2, hash.best(exam1)) `should be equal to` false
        hash.bestCheck(exam1, hash.best(exam2)) `should be equal to` false
        hash.bestCheck(exam1, hash.best(exam2)) `should be equal to` false
        hash.bestCheck(exam2, hash.best(exam1)) `should be equal to` false
    }
}
