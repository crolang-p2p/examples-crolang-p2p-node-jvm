package ex_1

import org.crolangP2P.Constants.ALICE_ID
import org.crolangP2P.Constants.BOB_ID
import org.crolangP2P.Constants.BROKER_ADDR
import org.crolangP2P.CrolangP2P
import org.crolangP2P.SyncCrolangNodeCallbacks

fun main(){

    CrolangP2P.Kotlin
        .connectToBroker(BROKER_ADDR, ALICE_ID)
        .onFailure { println("Error connecting to Broker: $it") }
        .onSuccess {
            println("Connected to Broker at $BROKER_ADDR as $ALICE_ID")

            CrolangP2P.Kotlin.connectToSingleNodeSync(BOB_ID, SyncCrolangNodeCallbacks(
                onNewMsg = mapOf(
                    "GREETINGS_CHANNEL" to { node, msg ->
                        println("Received a message on GREETINGS_CHANNEL from Node ${node.id}: $msg")
                    }
                )
            )).onFailure {
                println("Error connecting to Node $BOB_ID: $it")
            }.onSuccess {
                println("Connected to Node ${it.id}, platform: ${it.platform}, version: ${it.version}")
                it.send("GREETINGS_CHANNEL", "Hello from Node $ALICE_ID")
            }
        }
}