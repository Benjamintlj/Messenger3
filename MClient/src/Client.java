import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {

        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {

            // creates a socket at port 1234
            //socket = new Socket("localhost", 1234);
            socket = new Socket("127.0.0.1", 1234);

            // reads socket char by char
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

            // buffers it so that you get a string
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            // looks at what the user inputs
            Scanner scanner = new Scanner(System.in);

            while (true) {
                // gets the message to send from the user after they press enter
                String messageToSend = scanner.nextLine();

                // writes the message
                bufferedWriter.write(messageToSend);
                bufferedWriter.newLine();
                // empties the buffer
                bufferedWriter.flush();

                // reads the input
                System.out.println("Server: " + bufferedReader.readLine());

                // break if user says bye
                if (messageToSend.equalsIgnoreCase("bye")) break;
            }

        // CLEANING UP
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) socket.close();
                if (inputStreamReader != null) inputStreamReader.close();
                if (outputStreamWriter != null) outputStreamWriter.close();
                if (bufferedReader != null) bufferedReader.close();
                if (bufferedWriter != null) bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
