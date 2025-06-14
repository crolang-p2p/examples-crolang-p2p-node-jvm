package ex_1

import org.crolangP2P.Constants.BOB_ID
import org.crolangP2P.Constants.BROKER_ADDR
import org.crolangP2P.CrolangP2P
import org.crolangP2P.IncomingCrolangNodesCallbacks

fun main(){

    CrolangP2P.Kotlin
        .connectToBroker(BROKER_ADDR, BOB_ID)
        .onFailure { println("Error connecting to Broker: $it") }
        .onSuccess {
            println("Connected to Broker at $BROKER_ADDR as $BOB_ID")

            CrolangP2P.Kotlin.allowIncomingConnections(IncomingCrolangNodesCallbacks(
                onConnectionSuccess = { node ->
                    println("Connected successfully to Node ${node.id}, platform: ${node.platform}, version: ${node.version}")
                },
                onNewMsg = mapOf(
                    "GREETINGS_CHANNEL" to { node, msg ->
                        println("Received a message on GREETINGS_CHANNEL from Node ${node.id}: $msg")
                        node.send("GREETINGS_CHANNEL", "Hi ${node.id}, I'm Node $BOB_ID")
                    }
                )
            )).onFailure {
                println("Error allowing incoming connections: $it")
            }.onSuccess {
                println("Incoming connections are now allowed")
            }
        }
}
