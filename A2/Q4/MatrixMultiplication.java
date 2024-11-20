import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MatrixMultiplication extends Remote {
    // Method to multiply submatrices and return the result
    int[][] multiplyMatrices(int[][] A, int[][] B, int rowStart, int rowEnd) throws RemoteException;
}
