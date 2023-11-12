package task00;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class serv {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(8000)) {
            System.out.println(1);
            Socket clientSocket = server.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter pw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            while (true) {
                int clientInput = Integer.parseInt(br.readLine());
                pw.write(clientInput * clientInput + "\n");
                pw.flush();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
