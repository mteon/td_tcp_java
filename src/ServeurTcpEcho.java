import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServeurTcpEcho {
    private int port;
    private int nbClients;
    private int nbThreads = 5;
    private ExecutorService pool = Executors.newFixedThreadPool(nbThreads);

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
            pool.execute(thread);
        }
        sockServeur.close();
    }
}
