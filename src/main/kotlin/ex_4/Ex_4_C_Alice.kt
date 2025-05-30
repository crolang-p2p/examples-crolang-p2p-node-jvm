package ex_4

import org.crolangP2P.AsyncCrolangNodeCallbacks
import org.crolangP2P.Constants.ALICE_ID
import org.crolangP2P.Constants.BOB_ID
import org.crolangP2P.Constants.BROKER_ADDR
import org.crolangP2P.CrolangP2P

fun main(){

    val asyncCrolangNodeCallbacks = AsyncCrolangNodeCallbacks(
        onConnectionSuccess = { node ->
            println("Connected to Node ${node.id} successfully")
            node.send("GREETINGS_CHANNEL", "Hello there!")
        },
        onConnectionFailed = { id, reason ->
            println("Failed to connect to Node $id: $reason")
        },
        onDisconnection = { id -> println("Node $id disconnected") },
        onNewMsg = mapOf(
            "CHANNEL_LETTERS" to { node, msg ->
                println("Received a message on CHANNEL_LETTERS from Node ${node.id}: $msg")
            },
            "CHANNEL_NUMBERS" to { node, msg ->
                println("Received a message on CHANNEL_NUMBERS from Node ${node.id}: $msg")
            }
        )
    )

    CrolangP2P.Kotlin.connectToBroker(BROKER_ADDR, ALICE_ID)
        .onSuccess {
            println("Connected to Broker at $BROKER_ADDR as $ALICE_ID")

            CrolangP2P.Kotlin.connectToSingleNodeAsync(
                BOB_ID, asyncCrolangNodeCallbacks
            )
        }

}