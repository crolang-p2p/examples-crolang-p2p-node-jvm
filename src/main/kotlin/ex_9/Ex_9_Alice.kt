package ex_5

import org.crolangP2P.Constants.ALICE_ID
import org.crolangP2P.Constants.BOB_ID
import org.crolangP2P.Constants.BROKER_ADDR
import org.crolangP2P.CrolangP2P
import org.crolangP2P.SyncCrolangNodeCallbacks

fun main(){

    CrolangP2P.Kotlin.connectToBroker(BROKER_ADDR, ALICE_ID)

    println("Connected to Broker at $BROKER_ADDR as $ALICE_ID")

    CrolangP2P.Kotlin.connectToSingleNodeSync(BOB_ID, SyncCrolangNodeCallbacks(
        onNewMsg = mapOf(
            "REDIRECT_TO_ALICE" to { node, msg -> println("[REDIRECT_TO_ALICE][${node.id}]: $msg") }
        )
    )).onSuccess {
        println("Connected successfully to Node ${it.id}")
        it.send("CONNECT_TO_CAROL", "")
    }
}