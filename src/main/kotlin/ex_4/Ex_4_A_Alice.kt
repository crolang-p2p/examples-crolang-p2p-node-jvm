package ex_4

import org.crolangP2P.Constants.ALICE_ID
import org.crolangP2P.Constants.BOB_ID
import org.crolangP2P.Constants.BROKER_ADDR
import org.crolangP2P.CrolangP2P
import org.crolangP2P.SyncCrolangNodeCallbacks

fun main(){

    val syncCrolangNodeCallbacks = SyncCrolangNodeCallbacks(
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

            CrolangP2P.Kotlin.connectToSingleNodeSync(
                BOB_ID, syncCrolangNodeCallbacks
            ).onFailure {
                println("Failed to connect to Node $BOB_ID: $it")
            }.onSuccess { node ->
                println("Connected to Node ${node.id} successfully")
                node.send("GREETINGS_CHANNEL", "Hello there!")
            }

        }

}