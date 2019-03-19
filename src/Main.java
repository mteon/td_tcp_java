import java.io.IOException;

public class Main {
    public static void main (String[] args) throws IOException {
        ServeurTcpEcho serveur = new ServeurTcpEcho(50007, 500);
        serveur.lancerServeur();

       // ClientTcpEcho clientTcpEcho = new ClientTcpEcho(50007,"10.203.9.156");
        // clientTcpEcho.lancerClient();
    }

}
