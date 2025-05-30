package ex_4

import org.crolangP2P.Constants.ALICE_ID
import org.crolangP2P.Constants.BOB_ID
import org.crolangP2P.Constants.BROKER_ADDR
import org.crolangP2P.Constants.CAROL_ID
import org.crolangP2P.CrolangP2P
import org.crolangP2P.SyncCrolangNodeCallbacks

fun main(){

    val bobSyncCallbacks = SyncCrolangNodeCallbacks(
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

    val carolSyncCallbacks = SyncCrolangNodeCallbacks(
        onDisconnection = { id -> println("Node $id disconnected") },
        onNewMsg = mapOf(
            "CHANNEL_ANIMALS" to { node, msg ->
                println("Received a message on CHANNEL_ANIMALS from Node ${node.id}: $msg")
            }
        )
    )

    CrolangP2P.Kotlin.connectToBroker(BROKER_ADDR, ALICE_ID)
        .onSuccess {

            println("Connected to Broker at $BROKER_ADDR as $ALICE_ID")

            CrolangP2P.Kotlin.connectToMultipleNodesSync(mapOf(
                BOB_ID to bobSyncCallbacks,
                CAROL_ID to carolSyncCallbacks
            )).forEach{ entry ->
                entry.value.onFailure {
                    println("Failed to connect to Node ${entry.key}: $it")
                }.onSuccess { node ->
                    println("Connected to Node ${node.id} successfully")
                    node.send("GREETINGS_CHANNEL", "Hello there!")
                }
            }

        }

}