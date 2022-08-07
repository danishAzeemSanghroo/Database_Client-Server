package src;

import java.io.*;
import java.net.*;
import java.sql.SQLException;
  
// Server class
public class Server {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, Exception
    {
        
        int count=1;
        
        ServerSocket server = null;
        System.out.println("Server is running....");
        boolean establishDBConnection = Database.establishDBConnection();
        if(true){
            System.out.println("Server is now connected to DB");
        }
        try {
  
            // server is listening on port 1234
            server = new ServerSocket(1234);
            server.setReuseAddress(true);
  
            // running infinite loop for getting
            // client request
            while (true) {
  
                // socket object to receive incoming client
                // requests
                Socket client = server.accept();
  
                // Displaying that new client is connected
                // to server
                
                System.out.println("New "+ (count) +" client connected with IP "
                                   + client.getInetAddress()
                                         .getHostAddress());
                count++;
                // create a new thread object
                ClientHandler clientSock
                    = new ClientHandler(client);
  
                // This thread will handle the client
                // separately
                new Thread(clientSock).start();
                System.out.println("ClientHandler Started...");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (server != null) {
                try {
                    server.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}