package ServerSide;

import java.io.*;
import java.net.*;

/**
 * The LoginServer class represents the server component of the login application.
 * It listens for incoming client connections and handles login requests.
 */
public class LoginServer {
    public static void main(String[] args) {
        try {
            // Create a server socket to listen for incoming connections on port 1234
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Server started...");

            // Continuously accept incoming client connections
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                // Create a new thread to handle the client connection
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}