# examples-kotlin-crolang-p2p-node-jvm

## Table of Contents
- [The CrolangP2P Project](#the-crolangp2p-project)
- [Kotlin Examples Introduction](#kotlin-examples-introduction)
- [Examples Cross-language Interoperability](#examples-cross-language-interoperability)
- [Kotlin Examples List](#kotlin-examples-list)
    - [Example 1: Communication among Nodes with CrolangP2P](src/main/kotlin/ex_1/README.md)
    - [Example 2: Connecting to the CrolangP2P Broker](src/main/kotlin/ex_2/README.md)
    - [Example 3: Enabling/disabling incoming P2P connections](src/main/kotlin/ex_3/README.md)
    - [Example 4: Different ways of establish P2P connections](src/main/kotlin/ex_4/README.md)
    - [Example 5: Checking if other Nodes are connected to the Broker](src/main/kotlin/ex_5/README.md)
    - [Example 6: Retrieving nodes currently connected to your local nodes](src/main/kotlin/ex_6/README.md)
    - [Example 7: Disconnecting from the Broker](src/main/kotlin/ex_7/README.md)
    - [Example 8: Sending messages via WebSocket](src/main/kotlin/ex_8/README.md)
    - [Example 9: Nodes allowing incoming connections and initiating new connections simultaneously](src/main/kotlin/ex_9/README.md)
    - [Example 10: Sending large amount of data over P2P](src/main/kotlin/ex_10/README.md)
    - [Example 11: Customizing Local Node Behavior with BrokerConnectionAdditionalParameters](src/main/kotlin/ex_11/README.md)
    - [Example 12: Authenticating to the Broker](src/main/kotlin/ex_12/README.md)
- [Adding the Library to Your Project](#adding-the-library-to-your-project)

## The CrolangP2P Project

[CrolangP2P](https://github.com/crolang-p2p) is a simple, robust, and cross-language framework for establishing peer-to-peer (P2P) connections between clients (Crolang Nodes) in different programming languages. Nodes connect using unique IDs and can exchange data directly via WebRTC, or through a Broker as a relay if direct P2P is not possible. It is also possible for nodes to exchange messages via WebSocket through the Broker. The project focuses on minimal setup, language-agnostic interoperability, support for large data transfers, and easy extensibility.

## Kotlin Examples Introduction
This repository contains a collection of practical examples demonstrating how to use the [CrolangP2P](https://github.com/crolang-p2p) library in a JVM (Kotlin) environment. The official Kotlin/JVM implementation is available here: [crolang-p2p-node-jvm](https://github.com/crolang-p2p/crolang-p2p-node-jvm).

Each example is designed to illustrate a specific use case, ranging from basic node communication to advanced features such as authentication, connection management, and large data transfers.

> The main entry point for the CrolangP2P documentation is available at [https://github.com/crolang-p2p](https://github.com/crolang-p2p).

## Examples Cross-language Interoperability
[CrolangP2P](https://github.com/crolang-p2p) is a cross-language library, available for multiple programming languages. Each implementation has a dedicated repository with the same set of examples, allowing you to mix and match nodes written in different languages. For instance, you can run the Alice node from Example 1 in Kotlin and the Bob node from Example 1 in JavaScript, and they will interoperate seamlessly.

You can find the complete list of example repositories for all supported languages here: [CrolangP2P Examples List](https://github.com/crolang-p2p#examples-list)

## Kotlin Examples List
The examples are organized in numbered folders, each with its own source code and a dedicated README explaining the context and execution instructions.

1. [Communication among Nodes with CrolangP2P](src/main/kotlin/ex_1/README.md)
2. [Connecting to the CrolangP2P Broker](src/main/kotlin/ex_2/README.md)
3. [Enabling/disabling incoming P2P connections](src/main/kotlin/ex_3/README.md)
4. [Different ways of establish P2P connections](src/main/kotlin/ex_4/README.md)
5. [Checking if other Nodes are connected to the Broker](src/main/kotlin/ex_5/README.md)
6. [Retrieving nodes currently connected to your local nodes](src/main/kotlin/ex_6/README.md)
7. [Disconnecting from the Broker](src/main/kotlin/ex_7/README.md)
8. [Sending messages via WebSocket](src/main/kotlin/ex_8/README.md)
9. [Nodes allowing incoming connections and initiating new connections simultaneously](src/main/kotlin/ex_9/README.md)
10. [Sending large amount of data over P2P](src/main/kotlin/ex_10/README.md)
11. [Customizing Local Node Behavior with BrokerConnectionAdditionalParameters](src/main/kotlin/ex_11/README.md)
12. [Authenticating to the Broker](src/main/kotlin/ex_12/README.md)

## Adding the Library to Your Project

To use the Kotlin/JVM implementation of CrolangP2P in your project, simply add the dependency to your build configuration. The library is available on Maven Central and can be integrated with Gradle (Kotlin or Groovy DSL) or Maven.

Refer to the [library's Maven Central page](https://central.sonatype.com/artifact/io.github.crolang-p2p/crolang-p2p-node-jvm/overview) for the latest version.
