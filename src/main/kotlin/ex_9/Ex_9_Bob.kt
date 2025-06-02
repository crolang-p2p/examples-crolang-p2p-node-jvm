package ex_9

import org.crolangP2P.Constants.ALICE_ID
import org.crolangP2P.Constants.BOB_ID
import org.crolangP2P.Constants.BROKER_ADDR
import org.crolangP2P.Constants.CAROL_ID
import org.crolangP2P.CrolangP2P
import org.crolangP2P.IncomingCrolangNodesCallbacks
import org.crolangP2P.SyncCrolangNodeCallbacks

fun main() {

    CrolangP2P.Kotlin.connectToBroker(BROKER_ADDR, BOB_ID)

    println("Connected to Broker at $BROKER_ADDR as $BOB_ID")

    CrolangP2P.Kotlin.allowIncomingConnections(IncomingCrolangNodesCallbacks(
        onConnectionSuccess = { node ->
            println("Connected successfully to Node ${node.id}")
        },
        onNewMsg = mapOf(
            "CONNECT_TO_CAROL" to { node, _ ->
                println("[CONNECT_TO_CAROL][${node.id}]")
                println("Connecting to Node $CAROL_ID")

                connectToCarol()
            }
        )
    ))

}

fun connectToCarol(){
    CrolangP2P.Kotlin.connectToSingleNodeSync(CAROL_ID, SyncCrolangNodeCallbacks(
        onNewMsg = mapOf(
            "REDIRECT_TO_ALICE" to { node, msg ->
                println("[REDIRECT_TO_ALICE][${node.id}]: $msg")
                CrolangP2P.Kotlin.getConnectedNode(ALICE_ID).ifPresent {
                    val newMsg = "$msg, this message was redirected by Node $BOB_ID"
                    println("Redirecting to Node $ALICE_ID: $newMsg")
                    it.send("REDIRECT_TO_ALICE", newMsg)
                }
            }
        )
    )).onSuccess { println("Connected successfully to Node ${it.id}") }
}
