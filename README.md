# LoginApp
Client/Server Communication Simulation App;

The Application is mocking the Login Process using Socket Communication.


### The Project Structure:
- LoginApp
  - `src`: Source directory containing the main source files.
    - `main`: Main source directory.
      - `java`: Directory containing Java source files.
        - `ClientSide`: Directory for client-side code.
          - `LoginClient.java`: Java class for the client-side logic.
          - `LoginGUI.java`: Java class for the client-side Login Page.
        - `ServerSide`: Directory for server-side code.
          - `LoginServer.java`: Java class for the server-side logic.
          - `ClientHandler.java`: Java class for handling client connections on the server side.



### Note:
In ClientHandler.java File:
The private method "isValidCredentials" is implemented to validate the credentials,
e.g., checking against a database or hardcoded credentials. For simplicity, hardcoded values are used in this code.
