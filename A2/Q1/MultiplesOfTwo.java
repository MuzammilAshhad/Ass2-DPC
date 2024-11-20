import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MultiplesOfTwo extends Remote {
    int[] generateMultiples(int limit) throws RemoteException;
}
