import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientSmtp {
        private String hostname, serveurSmtp;
        private int port;
        private String recv;

        public ClientSmtp(int myPort, String myHostname, String myServeur) {
            this.hostname = myHostname;
            this.port = myPort;
            this.serveurSmtp = myServeur;
        }

    public boolean sendMail(String from, String to, String subject, String body) throws IOException {
        Socket sockClient = new Socket();
        String[] data = {
                "EHLO " + this.hostname,
                "MAIL FROM: " + from,
                "RCPT TO: " + to,
                "DATA",
                "FROM: " + from + "\n" +
                "TO: " + to + "\n" +
                "SUBJECT: " + subject + "\n" +
                body + "\n" +
                ".",
                "QUIT"
        };

        sockClient.connect(new InetSocketAddress(this.serveurSmtp, this.port));

        BufferedReader br = new BufferedReader(new InputStreamReader(
                sockClient.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                sockClient.getOutputStream()));

        for (String str: data) {
            while ((recv = br.readLine()) != null && recv.length() != 0) {
                System.out.println(recv);
                if (recv.charAt(3) != '-') break;
            }

            bw.write(str);
            System.out.println(str);
            bw.newLine();
            bw.flush();

        }
        br.close();
        bw.close();
        sockClient.close();

        return true;
    }
}
