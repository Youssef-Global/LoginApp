package ServerSide;

import java.io.*;
import java.net.*;

/**
 * The ClientHandler class represents a thread responsible for handling client requests on the server side.
 * It receives login credentials from the client, validates them, and sends a response back.
 * It allows for multiple login attempts with Max_Attmepts = 3 (editable).
 *
 */
class ClientHandler implements Runnable {
    private static final int MAX_ATTEMPTS = 3; // Maximum number of login attempts
    private Socket clientSocket;

    /**
     * Constructs a new ClientHandler with the specified client socket.
     * @param clientSocket the client socket
     */
    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    /**
     * The run method of the thread.
     * It handles communication with the client, including login attempts.
     */
    @Override
    public void run() {
        try {
            // Create input and output streams for communication with the client
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Continue to prompt for username and password until valid credentials are provided
            int attempts = 0;
            while (attempts <= MAX_ATTEMPTS) {

                // Read username and password sent by the client
                String username = in.readLine();
                String password = in.readLine();

                // Validate the login credentials (For simplicity, hardcoded validation is used)
                if (isValidCredentials(username, password)) {
                    // Send success message to the client
                    out.println("You have successfully logged in.");
                    clientSocket.close();
                } else {
                    // Close the client socket if maximum attempts reached
                    if (attempts == MAX_ATTEMPTS) {
                        out.println("Maximum login attempts exceeded. Connection closed.");
                        clientSocket.close();
                    }
                    // Send failure message to the client
                    out.println("Invalid username or password. Please try again.");
                    attempts++; // Increment the number of attempts
                }
            }

            // Close the client socket
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Validates the login credentials.
     *
     * @param username the username provided by the client
     * @param password the password provided by the client
     * @return true if the credentials are valid, false otherwise
     */
    private boolean isValidCredentials(String username, String password) {
        // checking against hardcoded credentials.
        return username.equals("Youssef Ashraf Awaad Ebrahim") && password.equals("sec4_33233108");
    }
}