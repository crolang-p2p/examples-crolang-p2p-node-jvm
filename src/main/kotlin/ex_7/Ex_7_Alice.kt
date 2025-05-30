package ex_7

import org.crolangP2P.Constants.ALICE_ID
import org.crolangP2P.Constants.BOB_ID
import org.crolangP2P.Constants.BROKER_ADDR
import org.crolangP2P.CrolangP2P
import org.crolangP2P.SyncCrolangNodeCallbacks

fun main(){

    val COUNTER_TRESHOLD = 20

    CrolangP2P.Kotlin.connectToBroker(BROKER_ADDR, ALICE_ID)

    println("Connected to Broker at $BROKER_ADDR as $ALICE_ID")

    CrolangP2P.Kotlin.connectToSingleNodeSync(BOB_ID, SyncCrolangNodeCallbacks(
        onDisconnection = { id ->
            println("Disconnected from Node $id")
        },
        onNewMsg = mapOf(
            "COUNT_CHANNEL" to { node, msg ->
                println("[COUNT_CHANNEL][${node.id}]: $msg")
                val i = msg.toInt()
                if(i >= COUNTER_TRESHOLD){
                    println("Counter threshold exceeded, disconnecting from Node ${node.id}")
                    node.disconnect()
                } else {
                    node.send("COUNT_CHANNEL", (i + 1).toString())
                }
            }
        )
    )).onSuccess {
        println("Connected successfully to Node ${it.id}")
        println("Disconnecting from Broker...")
        CrolangP2P.Kotlin.disconnectFromBroker()
        println("Is local Node connected to the Broker: ${CrolangP2P.Kotlin.isLocalNodeConnectedToBroker()}")
    }
}