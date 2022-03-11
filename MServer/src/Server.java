import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) throws IOException {
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        ServerSocket serverSocket = null;

        serverSocket = new ServerSocket(1234);

        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);

                // creates a server socket
                socket = serverSocket.accept();

                // reads socket char by char
                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

                // buffers it into a string
                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);

                while (true) {

                    // reads the entire line entered by the user
                    String messageFromClient = bufferedReader.readLine();
                    System.out.println("Client: " + messageFromClient);

                    // sends a message in return
                    String messageToSend = scanner.nextLine();
                    bufferedWriter.write(messageToSend);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    if (messageFromClient.equalsIgnoreCase("bye")) break;
                }

                // CLEANING UP
                socket.close();
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedReader.close();
                bufferedWriter.close();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
