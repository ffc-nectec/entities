package ffc.entity

import ffc.entity.place.House
import org.amshove.kluent.`should equal`
import org.junit.Test

class MessagingTest {

    @Test
    fun houseMessage() {
        val house = House()
        val messaging = Messaging(house, "/house/${house.id}")

        with(messaging) {
            type `should equal` "House"
            id `should equal` house.id
        }
    }
}
