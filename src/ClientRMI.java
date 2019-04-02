import Interfaces.AnnuaireInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientRMI {
    private String server;

    public ClientRMI (String myserver){
        this.server = myserver;
    }
    public void run() throws RemoteException, NotBoundException {
        String user = "odie";
        Registry registre = LocateRegistry.getRegistry(this.server);
        Interfaces.AnnuaireInterface stub = (AnnuaireInterface) registre.lookup("annuaire");
            String mail = stub.getEmail(user);
            String phoneNumber = stub.getPhoneNumber(user);
            System.out.println(mail);
            System.out.println(phoneNumber);
    }
}
