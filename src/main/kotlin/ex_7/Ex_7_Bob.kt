package ex_7

import org.crolangP2P.Constants.BOB_ID
import org.crolangP2P.Constants.BROKER_ADDR
import org.crolangP2P.CrolangP2P
import org.crolangP2P.IncomingCrolangNodesCallbacks

fun main() {

    CrolangP2P.Kotlin.connectToBroker(BROKER_ADDR, BOB_ID)

    println("Connected to Broker at $BROKER_ADDR as $BOB_ID")

    CrolangP2P.Kotlin.allowIncomingConnections(IncomingCrolangNodesCallbacks(
        onConnectionSuccess = { node ->
            println("Connected successfully to Node ${node.id}")
            println("Disconnecting from Broker...")
            CrolangP2P.Kotlin.disconnectFromBroker()
            println("Is local Node connected to the Broker: ${CrolangP2P.Kotlin.isLocalNodeConnectedToBroker()}")
            node.send("COUNT_CHANNEL", "0")
        },
        onDisconnection = { id ->
            println("Disconnected from Node $id")
        },
        onNewMsg = mapOf(
            "COUNT_CHANNEL" to { node, msg ->
                println("[COUNT_CHANNEL][${node.id}]: $msg")
                node.send("COUNT_CHANNEL", (msg.toInt() + 1).toString())
            }
        )
    ))

}