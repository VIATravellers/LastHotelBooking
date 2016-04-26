package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
        
/*
 * Before running this Server first set the CLASSPATH environment variable
 * in a command prompt:
 * 
 * export CLASSPATH=/home/skr/workspace/RMIExample/bin/
 * 
 * Then run the rmiregistry in that same command prompt
 * 
 * rmiregistry
 * 
 * OR don't set the CLASSPATH and run like this,
 * 
 * rmiregistry -J-Djava.rmi.server.codebase=file:/home/skr/workspace/RMIExample/bin/
 * 
 * Then run the server in another command prompt
 * 
 * java -classpath /home/skr/workspace/RMIExample/bin -Djava.rmi.server.codebase=file:/home/skr/workspace/RMIExample/bin/ server.Server
 * 
 */
public class Server implements Hello {
        
    public Server() {}

    public String sayHello() {
        return "Hello again, world!";
    }
        
    public static void main(String args[]) {
        
    	System.setProperty("java.rmi.server.hostname","10.0.0.110");
    	
        try {
            Server obj = new Server();
            Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Hello", stub);

            System.out.println("Server ready");
        } catch (Exception e) {
            System.out.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
