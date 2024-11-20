import java.rmi.Naming;
import java.util.Arrays;

public class MatrixMultiplicationClient {

    // Method to divide matrix into submatrices
    public static int[][][] divideMatrix(int[][] matrix, int blockSize) {
        int numBlocks = matrix.length / blockSize;
        int[][][] subMatrices = new int[numBlocks * numBlocks][blockSize][blockSize];

        for (int i = 0; i < numBlocks; i++) {
            for (int j = 0; j < numBlocks; j++) {
                for (int row = 0; row < blockSize; row++) {
                    System.arraycopy(matrix[i * blockSize + row], j * blockSize, subMatrices[i * numBlocks + j][row], 0, blockSize);
                }
            }
        }
        return subMatrices;
    }

    // Method to combine result submatrices into the final matrix
    public static int[][] combineResults(int[][][] subMatrices, int blockSize, int size) {
        int[][] result = new int[size][size];
        int numBlocks = size / blockSize;

        for (int i = 0; i < numBlocks; i++) {
            for (int j = 0; j < numBlocks; j++) {
                for (int row = 0; row < blockSize; row++) {
                    System.arraycopy(subMatrices[i * numBlocks + j][row], 0, result[i * blockSize + row], j * blockSize, blockSize);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        try {
            // Example 4x4 matrices (can be any size)
            int[][] matrixA = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
            };
            int[][] matrixB = {
                {1, 0, 0, 1},
                {0, 1, 1, 0},
                {1, 1, 0, 1},
                {0, 0, 1, 0}
            };
            
            // Assume 2x2 submatrices for parallelization
            int blockSize = 2;

            // Split matrices A and B into submatrices
            int[][][] subMatricesA = divideMatrix(matrixA, blockSize);
            int[][][] subMatricesB = divideMatrix(matrixB, blockSize);

            int[][][] resultSubMatrices = new int[subMatricesA.length][][];

            // Connect to the RPC server(s)
            MatrixMultiplication service = (MatrixMultiplication) Naming.lookup("rmi://localhost/MatrixMultiplicationService");

            // Perform parallel matrix multiplication
            for (int i = 0; i < subMatricesA.length; i++) {
                resultSubMatrices[i] = service.multiplySubmatrices(subMatricesA[i], subMatricesB[i]);
            }

            // Combine the results from the submatrices
            int[][] finalResult = combineResults(resultSubMatrices, blockSize, matrixA.length);

            // Print the final matrix
            System.out.println("Final Result Matrix:");
            for (int[] row : finalResult) {
                System.out.println(Arrays.toString(row));
            }

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
