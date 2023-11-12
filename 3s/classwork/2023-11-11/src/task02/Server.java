package task02;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try(ServerSocket ss = new ServerSocket(8000)) {
            Socket client = ss.accept();

            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

            int randomNumber = (int) (100 * Math.random());

            while (true) {
                int clientInput = Integer.parseInt(br.readLine());
                String out = clientInput == randomNumber ? "You did good! Restarting..." : clientInput > randomNumber ? "Less" : "More";
                System.out.println(out);
                bw.write(out + "\n");
                bw.flush();
                if (out.contains("You did good!")) {
                    randomNumber = (int) (100 * Math.random());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
