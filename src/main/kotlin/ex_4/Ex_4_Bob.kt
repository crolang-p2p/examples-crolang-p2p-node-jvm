package ex_4

import org.crolangP2P.Constants.BOB_ID
import org.crolangP2P.Constants.BROKER_ADDR
import org.crolangP2P.CrolangP2P
import org.crolangP2P.IncomingCrolangNodesCallbacks

fun main(){

    CrolangP2P.Kotlin.connectToBroker(BROKER_ADDR, BOB_ID)
        .onSuccess {

            println("Connected to Broker at $BROKER_ADDR as $BOB_ID")

            CrolangP2P.Kotlin.allowIncomingConnections(IncomingCrolangNodesCallbacks(
                onConnectionSuccess = { node ->
                    println("Connected to Node ${node.id} successfully")
                    node.send("CHANNEL_LETTERS", "ABC")
                    node.send("CHANNEL_NUMBERS", "42")
                },
                onNewMsg = mapOf(
                    "GREETINGS_CHANNEL" to { node, msg ->
                        println("Received a message on GREETINGS_CHANNEL from Node ${node.id}: $msg")
                    },
                )
            ))
        }

}