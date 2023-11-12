package task00;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class client {
    public static void main(String[] args) {
        try (Socket clientSocket = new Socket("localhost", 8000)) {
            Scanner sc = new Scanner(System.in);
            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter pw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            while (true) {
                String toSend = sc.nextLine();
                pw.write(toSend + "\n");
                pw.flush();
                System.out.println(br.readLine());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
