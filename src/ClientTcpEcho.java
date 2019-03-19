import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientTcpEcho {
    private String hostname;
    private int port;

    public ClientTcpEcho(int myPort, String myHostname) {
        this.hostname = myHostname;
        this.port = myPort;
    }

    public void lancerClient() throws IOException {
        String line;

        Socket sockClient = new Socket();
        sockClient.connect(new InetSocketAddress(this.hostname, this.port));
        BufferedReader br = new BufferedReader(new InputStreamReader(
                sockClient.getInputStream()));
        while ((line = br.readLine()) != null)
            System.out.println(line);
        br.close();
        if (line.contains("quit"))
            sockClient.close();
    }
}
