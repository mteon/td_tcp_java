import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientTcpEcho {
    private String hostname;
    private int port;

    public ClientTcpEcho(int myPort, String myHostname) {
        this.hostname = myHostname;
        this.port = myPort;
    }

    public void lancerClient() throws IOException {
        String line = "";
        Socket sockClient = new Socket();

        sockClient.connect(new InetSocketAddress(this.hostname, this.port));

        BufferedReader br = new BufferedReader(new InputStreamReader(
                sockClient.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                sockClient.getOutputStream()));

        while (true) {
            Scanner sc = new Scanner(System.in);
            line = sc.nextLine();
            if(line.equals("quit")) break;
            bw.write(line);
            bw.newLine();
            bw.flush();
            String received = br.readLine();
            System.out.println(received);
        }


        br.close();
        bw.close();
        sockClient.close();
    }
}
