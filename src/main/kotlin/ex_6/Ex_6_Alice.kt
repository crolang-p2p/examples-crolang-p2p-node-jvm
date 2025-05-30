package ex_6

import org.crolangP2P.Constants.ALICE_ID
import org.crolangP2P.Constants.BOB_ID
import org.crolangP2P.Constants.BROKER_ADDR
import org.crolangP2P.Constants.CAROL_ID
import org.crolangP2P.CrolangP2P
import org.crolangP2P.SyncCrolangNodeCallbacks

fun main() {
    CrolangP2P.Kotlin.connectToBroker(BROKER_ADDR, ALICE_ID).onSuccess {
        println("Connected to Broker at $BROKER_ADDR as $ALICE_ID")

        CrolangP2P.Kotlin.connectToMultipleNodesSync(mapOf(
            BOB_ID to SyncCrolangNodeCallbacks(),
            CAROL_ID to SyncCrolangNodeCallbacks()
        ))

        CrolangP2P.Kotlin.getConnectedNode(BOB_ID).ifPresent { node ->
            println("Node $BOB_ID is connected")
            node.send("GREETINGS_CHANNEL", "Hello ${node.id}!")
        }

        CrolangP2P.Kotlin.getAllConnectedNodes()[CAROL_ID]?.let { node ->
            println("Node $CAROL_ID is connected")
            node.send("GREETINGS_CHANNEL", "Hello ${node.id}!")
        }

    }
}