import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MultiplesOfTwoImpl extends UnicastRemoteObject implements MultiplesOfTwo {

    protected MultiplesOfTwoImpl() throws RemoteException {
        super();
    }

    @Override
    public int[] generateMultiples(int limit) throws RemoteException {
        int[] multiples = new int[limit];
        for (int i = 0; i < limit; i++) {
            multiples[i] = (i + 1) * 2;
        }
        return multiples;
    }

    public static void main(String[] args) {
        try {
            // Create server object
            MultiplesOfTwoImpl server = new MultiplesOfTwoImpl();

            // Bind the remote object's stub in the registry
            Naming.rebind("MultiplesOfTwoService", server);

            System.out.println("MultiplesOfTwoImpl server is ready.");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
