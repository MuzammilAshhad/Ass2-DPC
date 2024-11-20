import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MatrixMultiplicationServer extends UnicastRemoteObject implements MatrixMultiplication {

    protected MatrixMultiplicationServer() throws RemoteException {
        super();
    }

    @Override
    public int[][] multiplySubmatrices(int[][] matrixA, int[][] matrixB) throws RemoteException {
        int rowsA = matrixA.length;
        int colsA = matrixA[0].length;
        int colsB = matrixB[0].length;
        int[][] result = new int[rowsA][colsB];

        // Perform multiplication of submatrices
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                result[i][j] = 0;
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        try {
            MatrixMultiplicationServer server = new MatrixMultiplicationServer();
            Naming.rebind("MatrixMultiplicationService", server);
            System.out.println("MatrixMultiplicationServer is ready.");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
