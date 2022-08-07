package src;

import java.io.*;
import java.net.*;
import java.util.*;
  
// Client class
class Client {
    
    // driver code
    public static void main(String[] args)
    {
        // establish a connection by providing host and port
        System.out.println("Client is running");
        // number
        try (Socket socket = new Socket("localhost", 1234)) {
            
            // writing to server
            PrintWriter out = new PrintWriter(
                socket.getOutputStream(), true);
  
            // reading from server
            BufferedReader in
                = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
  
            // object of scanner class
            Scanner sc = new Scanner(System.in);
            String line = null;
            
            
            while (!"stop".equalsIgnoreCase(line)) {
                
                System.out.println("Enter the artist name: ");
                // reading from user
                line = sc.nextLine();
  
                // sending the user input to server
                out.println(line);
                out.flush();
  
                //you entered
                System.out.println("you entered "+line);
                
                // displaying server reply
                System.out.println("FROM SERVER: Number of titles: "
                                   + in.readLine() +" records found");
            }
            
            // closing the scanner object
            sc.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}