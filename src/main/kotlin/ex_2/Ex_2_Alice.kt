package ex_2

import org.crolangP2P.Constants.ALICE_ID
import org.crolangP2P.Constants.BROKER_ADDR
import org.crolangP2P.CrolangP2P
import org.crolangP2P.exceptions.ConnectToBrokerException

fun main() {

    CrolangP2P.Kotlin
        .connectToBroker(BROKER_ADDR, ALICE_ID)
        .onFailure { error ->
            when(error){
                is ConnectToBrokerException.LocalClientAlreadyConnectedToBroker -> {
                    println("Local client is already connected to Broker at $BROKER_ADDR")
                }
                is ConnectToBrokerException.ClientWithSameIdAlreadyConnected -> {
                    println("A client with the same ID $ALICE_ID is already connected to Broker at $BROKER_ADDR")
                }
                is ConnectToBrokerException.UnsupportedArchitecture -> {
                    println("This client version is not supported by Broker at $BROKER_ADDR")
                }
                is ConnectToBrokerException.Unauthorized -> {
                    println("Unauthorized connection attempt to Broker at $BROKER_ADDR with ID $ALICE_ID")
                }
                is ConnectToBrokerException.SocketError -> {
                    println("Socket error while connecting to Broker at $BROKER_ADDR")
                }
                is ConnectToBrokerException.ErrorParsingRTCConfiguration -> {
                    println("Error parsing RTC configuration for Broker at $BROKER_ADDR")
                }
                is ConnectToBrokerException.UnknownError -> {
                    println("Unknown error while connecting to Broker at $BROKER_ADDR")
                }
            }
        }
        .onSuccess {
            println("Connected to Broker at $BROKER_ADDR as $ALICE_ID")
        }

}