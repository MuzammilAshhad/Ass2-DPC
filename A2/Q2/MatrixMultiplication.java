import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MatrixMultiplication extends Remote {
    // Method for multiplying two submatrices
    int[][] multiplySubmatrices(int[][] matrixA, int[][] matrixB) throws RemoteException;
}
