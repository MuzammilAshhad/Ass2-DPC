import java.rmi.Naming;

public class MultiplesOfTwoClient {
    public static void main(String[] args) {
        try {
            MultiplesOfTwo service = (MultiplesOfTwo) Naming.lookup("rmi://localhost/MultiplesOfTwoService");

            // Example usage: request multiples of 2 up to 5
            int limit = 5; // You can change the limit to test
            int[] multiples = service.generateMultiples(limit);

            // Print the received multiples
            System.out.println("Multiples of 2 up to " + limit + ":");
            for (int multiple : multiples) {
                System.out.println(multiple);
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
