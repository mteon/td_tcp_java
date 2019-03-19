import java.io.IOException;

public class Main {
    public static void main (String[] args) throws IOException {
        switch (args[0]){
            case "serveur" :
                ServeurTcpEcho serveur = new ServeurTcpEcho(50007, 500);
                serveur.lancerServeur();
                break;
            case "client" :
                ClientTcpEcho clientTcpEcho = new ClientTcpEcho(50007,"10.203.9.155");
                clientTcpEcho.lancerClient();
        }
    }

}
