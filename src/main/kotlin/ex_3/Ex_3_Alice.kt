package ex_3

import org.crolangP2P.Constants.ALICE_ID
import org.crolangP2P.Constants.BOB_ID
import org.crolangP2P.Constants.BROKER_ADDR
import org.crolangP2P.CrolangP2P
import org.crolangP2P.SyncCrolangNodeCallbacks
import org.crolangP2P.exceptions.ConnectionToNodeFailedReasonException

fun main(){

    CrolangP2P.Kotlin.connectToBroker(BROKER_ADDR, ALICE_ID)
    println("Connected to Broker at $BROKER_ADDR as $ALICE_ID")

    CrolangP2P.Kotlin.connectToSingleNodeSync(BOB_ID, SyncCrolangNodeCallbacks(
        onDisconnection = { id ->
            println("Disconnected from Node $id, trying to reconnect...")
            CrolangP2P.Kotlin
                .connectToSingleNodeSync(id)
                .onSuccess { node ->
                    println("Connected successfully to Node ${node.id}")
                }
                .onFailure { error ->
                    when(error){
                        is ConnectionToNodeFailedReasonException.ConnectionsNotAllowedOnRemoteNode -> {
                            println("Connections not allowed on remote Node $id")
                        }
                        else -> {
                            println("Error connecting to Node $id: ${error.message}")
                        }
                    }
                }
        }
    )).onSuccess { node ->
        println("Connected successfully to Node ${node.id}")
        node.send("CHANNEL_NUMBERS", "42")
        node.send("CHANNEL_DISCONNECT")
    }.onFailure {
        println("Error while connecting to Node $BOB_ID: $it")
    }

}