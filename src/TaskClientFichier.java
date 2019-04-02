import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class TaskClientFichier {
    String serveur;
    int port;

    public TaskClientFichier(String myserveur, int myport) {
        this.serveur = myserveur;
        this.port = myport;
    }

    public void run() throws IOException {

        Socket client = new Socket();
        client.connect(new InetSocketAddress(this.serveur, this.port));

        BufferedWriter outClient = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

        InputStream inClient = client.getInputStream();

        String filename = new Scanner(System.in).nextLine();

        outClient.write(filename);
        outClient.newLine();
        outClient.flush();

        byte buf[] = new byte[512];

        FileOutputStream f = new FileOutputStream("imagebis.jpg");

        int nbOctets;

        while ((nbOctets = inClient.read(buf)) != -1) {
            f.write(buf, 0, nbOctets);
        }

    }
}
