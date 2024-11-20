import java.rmi.Naming;

public class Client {
    public static void main(String[] args) {
        try {
            // Look up the remote object in the RMI registry
            MultiplesOfTwo service = (MultiplesOfTwo) Naming.lookup("rmi://localhost/MultiplesOfTwoService");

            // Request multiples of 2 up to the limit
            int limit = 5; // Example limit, can be changed to test other values
            int[] multiples = service.generateMultiples(limit);

            // Print the received multiples of 2
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
