package ex_5

import org.crolangP2P.Constants.ALICE_ID
import org.crolangP2P.Constants.BOB_ID
import org.crolangP2P.Constants.BROKER_ADDR
import org.crolangP2P.Constants.CAROL_ID
import org.crolangP2P.CrolangP2P
import org.crolangP2P.exceptions.RemoteNodesConnectionStatusCheckException

fun main() {

    CrolangP2P.Kotlin.connectToBroker(BROKER_ADDR, ALICE_ID)
        .onFailure { println("Error connecting to Broker: $it") }
        .onSuccess {
            println("Connected to Broker at $BROKER_ADDR as $BOB_ID")

            CrolangP2P.Kotlin.isRemoteNodeConnectedToBroker(BOB_ID)
                .onFailure { error ->
                    when(error){
                        is RemoteNodesConnectionStatusCheckException.NotConnectedToBroker -> println("Local Node not connected to Broker")
                        is RemoteNodesConnectionStatusCheckException.UnknownError -> println("Unknown error checking connection to Broker: $error")
                    }
                }
                .onSuccess { isConnected -> println("Is $BOB_ID connected to the Broker: $isConnected") }

            CrolangP2P.Kotlin.areRemoteNodesConnectedToBroker(setOf(BOB_ID, CAROL_ID))
                .onFailure { println("Error checking connection to Broker: $it") }
                .onSuccess { it.forEach{ entry -> println("Is ${entry.key} connected to the Broker: ${entry.value}") } }
        }

}