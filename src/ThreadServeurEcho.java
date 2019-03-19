import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ThreadServeurEcho extends Thread { //can also Implement Runnable

    Socket socketclient;

    ThreadServeurEcho(Socket sockcli){
        this.socketclient = sockcli;
    }

    @Override
    public void run() {
        try {
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    socketclient.getInputStream()));

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    socketclient.getOutputStream()));

            System.out.println("Client connecté à l'adresse " + socketclient.getInetAddress());

            while (true) {
                line = br.readLine();
                if (line == null || line.equals("quit")) break;
                bw.write(line.toUpperCase());
                bw.newLine();
                bw.flush();
            }
            br.close();
            bw.close();
            socketclient.close();
        }
        catch(Exception e){
            System.out.println("Euh nique ta mère");
        }
    }
}
