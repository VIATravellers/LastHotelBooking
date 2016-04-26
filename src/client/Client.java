package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import server.Hello;

public class Client {

    private Client() {}

    public static void main(String[] args) {

        String host = "10.0.0.110";
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            Hello stub = (Hello) registry.lookup("Hello");
            String response = stub.sayHello();
            System.out.println("response: " + response);
        } catch (Exception e) {
            System.out.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
