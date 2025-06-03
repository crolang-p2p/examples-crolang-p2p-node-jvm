package org.crolangP2P.ex_12

import com.google.gson.Gson
import org.crolangP2P.Constants.ALICE_ID
import org.crolangP2P.Constants.BROKER_ADDR
import org.crolangP2P.CrolangP2P

class Authentication(val token: String, val password: String)

fun main() {
    CrolangP2P.Kotlin.connectToBroker(
        BROKER_ADDR,
        ALICE_ID,
        onConnectionAttemptData = Gson().toJson(Authentication("magic-token", "unicorns"))
    ).onFailure {
        println("Failed to connect to Broker: $it")
    }.onSuccess {
        println("Connected to Broker at $BROKER_ADDR as $ALICE_ID")
    }

}