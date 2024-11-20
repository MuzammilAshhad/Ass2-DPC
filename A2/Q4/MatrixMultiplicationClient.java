import java.rmi.Naming;

public class MatrixMultiplicationClient {
    public static void main(String[] args) {
        try {
            // Look up the remote object in the RMI registry
            MatrixMultiplication service = (MatrixMultiplication) Naming.lookup("rmi://localhost/MatrixMultiplicationService");

            // Define two matrices for multiplication
            int[][] A = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
            };
            int[][] B = {
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
            };

            // Split the matrix and send each submatrix to the server
            int[][] resultPart1 = service.multiplyMatrices(A, B, 0, 1); // First row
            int[][] resultPart2 = service.multiplyMatrices(A, B, 1, 2); // Second row
            int[][] resultPart3 = service.multiplyMatrices(A, B, 2, 3); // Third row

            // Combine the results
            int[][] result = new int[3][3];
            System.arraycopy(resultPart1, 0, result, 0, resultPart1.length);
            System.arraycopy(resultPart2, 0, result, resultPart1.length, resultPart2.length);
            System.arraycopy(resultPart3, 0, result, resultPart1.length + resultPart2.length, resultPart3.length);

            // Print the final result
            System.out.println("Result matrix:");
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result[0].length; j++) {
                    System.out.print(result[i][j] + " ");
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
