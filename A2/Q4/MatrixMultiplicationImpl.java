import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MatrixMultiplicationImpl extends UnicastRemoteObject implements MatrixMultiplication {

    protected MatrixMultiplicationImpl() throws RemoteException {
        super();
    }

    // Implementation of matrix multiplication
    @Override
    public int[][] multiplyMatrices(int[][] A, int[][] B, int rowStart, int rowEnd) throws RemoteException {
        int n = B[0].length; // number of columns in B
        int[][] result = new int[rowEnd - rowStart][n];

        for (int i = rowStart; i < rowEnd; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < A[0].length; k++) {
                    result[i - rowStart][j] += A[i][k] * B[k][j];
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        try {
            // Create and bind the remote object
            MatrixMultiplicationImpl server = new MatrixMultiplicationImpl();
            Naming.rebind("MatrixMultiplicationService", server);
            System.out.println("MatrixMultiplication server is ready.");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
