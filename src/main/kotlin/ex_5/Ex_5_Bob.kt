package ex_5

import org.crolangP2P.Constants.BOB_ID
import org.crolangP2P.Constants.BROKER_ADDR
import org.crolangP2P.CrolangP2P

fun main() {

    CrolangP2P.Kotlin.connectToBroker(BROKER_ADDR, BOB_ID)
        .onFailure { println("Error connecting to Broker: $it") }
        .onSuccess { println("Connected to Broker at $BROKER_ADDR as $BOB_ID") }

}