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

        ServerSocket sockServeur = new ServerSocket();
        Socket sockClient;

        sockServeur.bind(new InetSocketAddress(this.port));
        for (int i = 1; i <= nbClients; i++) {
            sockClient = sockServeur.accept();
            ThreadServeurEcho thread = new ThreadServeurEcho(sockClient);
            thread.start();
        }
        sockServeur.close();
    }
}
