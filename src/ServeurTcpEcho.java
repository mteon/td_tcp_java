import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServeurTcpEcho {
    private int port;
    private int nbClients;

    public ServeurTcpEcho(int myPort, int client) {
        this.port = myPort;
        this.nbClients = client;
    }

    public void lancerServeur () throws IOException {
        String line;
        ServerSocket sockServeur = new ServerSocket();
        Socket sockClient;

        sockServeur.bind(new InetSocketAddress(this.port));
        for (int i = 1; i <= nbClients; i++) {
            sockClient = sockServeur.accept();

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    sockClient.getInputStream()));

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    sockClient.getOutputStream()));

            while (true) {
                line = br.readLine();
                if (line == null || line.equals("quit")) break;
                bw.write(line.toUpperCase());
                bw.newLine();
                bw.flush();
            }
            br.close();
            bw.close();
            sockClient.close();
        }
        sockServeur.close();
    }
}
