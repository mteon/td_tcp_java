import java.io.IOException;

public class Main {
    public static void main (String[] args) throws IOException {
        ClientTcpEcho client = new ClientTcpEcho(50007, "10.203.9.145");
        client.lancerClient();
    }

}
