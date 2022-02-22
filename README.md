# LeapMotion Gemini Java

An implementation of the Java LeapMotion API. The application pipes out frame tracking data over a socket server and can be received using the JAR library.

## WARNINGS
This repository is for research purposes only and is NOT made in collaboration with Ultraleap.

## Contents

### Unity Application

The unity application (folder "LeapMotion_Unity_Server) pipes out the tracking data over the socket.

### Java Library
The Java library project contains the standard methods from the original Java API. The client listens on the port and deserialises the frame data.

## Requirements

- Unity version 2020.3.28f1
- Java SDK 17

## Usage

1. Run the Unity application.
   - Located in **/LeapMotion_Java_Socket/Leapmotion_Unity_Server/Build/LeapServer**
   - The application will show the status of the sensor and display and error messages encountered.

2. Import the JAR
   - The JAR is located in **/LeapMotion_Java_Socket/lib/LeapMotion_Java**
   - Import the JAR file as a library.

3. Run the Java Client

    ```java
    Client client = new Client(6666);
    client.start(); // Starts the thread to receive the tracking data
    ```

4. To receive tracking data you should check for nulls.
    ```java
    if(frame!= null && frame.getHands().size() != 0){

    }
    ```

5. When completing work with Java, you **must** close the socket in order to keep the server alive (and avoid a reboot)

    ```java
    client.close();
    ```
