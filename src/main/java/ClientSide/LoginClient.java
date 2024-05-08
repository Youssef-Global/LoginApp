package ClientSide;
import java.io.*;
import java.net.*;

public class LoginClient {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public LoginClient() {
        try {
            // Establish connection to the server
            socket = new Socket("localhost", 1234);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String login(String username, String password) {
        try {
            // Send username and password to the server
            out.println(username);
            out.println(password);

            // Receive and return server's response
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void close() {
        try {
            // Close the socket and streams
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
