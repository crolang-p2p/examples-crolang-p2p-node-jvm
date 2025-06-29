package org.crolangP2P.ex_8

import org.crolangP2P.Constants.BOB_ID
import org.crolangP2P.Constants.BROKER_ADDR
import org.crolangP2P.CrolangP2P

fun main() {

    CrolangP2P.Kotlin.connectToBroker(
        BROKER_ADDR,
        BOB_ID,
        onNewSocketMsg = mapOf(
            "GREETINGS_CHANNEL" to { fromId, msg ->
                println("[GREETINGS_CHANNEL WebSocket][${fromId}]: $msg")
            },
            "SECRET_CHANNEL" to { fromId, msg ->
                println("[SECRET_CHANNEL WebSocket][${fromId}]: $msg")
            }
        )
    ).onSuccess {
        println("Connected to Broker at $BROKER_ADDR as $BOB_ID")
    }

}