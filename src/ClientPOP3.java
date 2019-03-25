import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientPOP3 {
    private String serveurPOP3;
    private int port;
    private String recv, sender;
    private Scanner scanner = new Scanner(System.in);

    public ClientPOP3(int myPort, String myServeur) {
        this.port = myPort;
        this.serveurPOP3 = myServeur;
    }

    public boolean recupMail(String password, String username) throws IOException {
        Socket sockClient = new Socket();
        String[] data = {
                "USER " + username,
                "PASS " + password,
                "LIST ",
                "RETR ",
                "QUIT"
        };

        sockClient.connect(new InetSocketAddress(this.serveurPOP3, this.port));

        BufferedReader br = new BufferedReader(new InputStreamReader(
                sockClient.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                sockClient.getOutputStream()));

        for (int i = 0; i < 3 ; ++i) {
            recv = br.readLine();

            bw.write(data[i]);
            bw.newLine();
            bw.flush();

            System.out.println(data[i]);
            }

        while ((recv = br.readLine()) != null) {
            System.out.println(recv);
            if (recv.equals(".")) break;
        }

        while(true) {
            System.out.println(recv);
            sender = scanner.nextLine();
            bw.write(data[3] + " " + sender);
            bw.newLine();
            bw.flush();

            while ((recv = br.readLine()) != null) {
                System.out.println(recv);
                if (recv.equals(".") || recv.contains("-ERR Invalid message number.")) break;
            }

            if(recv.contains("-ERR Invalid message number."))break;
        }
        br.close();
        bw.close();
        sockClient.close();

        return true;
    }
}

