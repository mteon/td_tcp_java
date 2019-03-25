import java.io.IOException;

public class Main {
    public static void main (String[] args) throws IOException {
        switch (args[0]){
            case "serveur" :
                ServeurTcpEcho serveur = new ServeurTcpEcho(50007, 500);
                serveur.lancerServeur();
                break;
            /* case "client" :
                ClientTcpEcho clientTcpEcho = new ClientTcpEcho(50007,"10.203.9.155");
                clientTcpEcho.lancerClient(); */

            /* case "client" :
                ClientSmtp clientSmtp = new ClientSmtp(25,"martin", "139.124.187.23");
                clientSmtp.sendMail("martin", "martin", "Test", "JE SUIS LE TEST"); */

            case "client" :
                ClientPOP3 clientPOP3 = new ClientPOP3(110,"139.124.187.23");
                clientPOP3.recupMail("martin", "martin");
        }
    }

}
