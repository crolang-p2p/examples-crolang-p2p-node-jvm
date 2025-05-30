package org.crolangP2P.ex_8

import org.crolangP2P.Constants.ALICE_ID
import org.crolangP2P.Constants.BOB_ID
import org.crolangP2P.Constants.BROKER_ADDR
import org.crolangP2P.CrolangP2P
import org.crolangP2P.exceptions.SendSocketMsgException

fun main(){

    CrolangP2P.Kotlin.connectToBroker(BROKER_ADDR, ALICE_ID).onSuccess {
        println("Connected to Broker at $BROKER_ADDR as $ALICE_ID")

        CrolangP2P.Kotlin.sendSocketMsg(BOB_ID, "GREETINGS_CHANNEL", "Hello from ${ALICE_ID}!")
            .onFailure { error ->
                when(error){
                    is SendSocketMsgException.TriedToSendMsgToSelf -> {
                        println("Error: Tried to send a message to myself. This is not allowed.")
                    }
                    is SendSocketMsgException.EmptyId -> {
                        println("Error: The ID to send the message to is empty.")
                    }
                    is SendSocketMsgException.EmptyChannel -> {
                        println("Error: The channel to send the message to is empty.")
                    }
                    is SendSocketMsgException.NotConnectedToBroker -> {
                        println("Error: Not connected to the broker. Please connect first.")
                    }
                    is SendSocketMsgException.Disabled -> {
                        println("Error: Sending messages through WebSocket is disabled on the Broker.")
                    }
                    is SendSocketMsgException.RemoteNodeNotConnectedToBroker -> {
                        println("Error: The remote node is not connected to the broker.")
                    }
                    is SendSocketMsgException.UnauthorizedToContactRemoteNode -> {
                        println("Error: Unauthorized to contact the remote node. Check permissions.")
                    }
                    is SendSocketMsgException.UnknownError -> {
                        println("Error: An unknown error occurred while sending the message.")
                    }
                }
            }

        CrolangP2P.Kotlin.sendSocketMsg(BOB_ID, "SECRET_CHANNEL", "42")

    }

}