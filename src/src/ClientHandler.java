package src;

// ClientHandler class

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

    public class ClientHandler implements Runnable {
        public Socket clientSocket;
  
        // Constructor
        public ClientHandler(Socket socket)
        {
            this.clientSocket = socket;
        }
  
        public void run()
        {
            PrintWriter out = null;
            BufferedReader in = null;
            try {
                    
                  // get the outputstream of client
                out = new PrintWriter(
                    clientSocket.getOutputStream(), true);
  
                  // get the inputstream of client
                in = new BufferedReader(
                    new InputStreamReader(
                        clientSocket.getInputStream()));
  
                //getTitles from db
                
                //System.out.println("getTitles"+numberOftitles);
                String line;
                while ((line = in.readLine()) != null) {
                    if(line.equalsIgnoreCase("stop")){
                        int count=1;
                        System.out.println("Client "+ (count++) +" Disconneted");
                    }
                    //getTitles from database
                    
                    // writing the received message from
                    // client
                    System.out.printf(
                        " Sent from the client: %s\n",
                        line);
                    int numberOfTitles = Database.getTitles(line);
                    String s=String.valueOf(numberOfTitles);
                    out.println(s);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            } catch (Exception ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();
                        clientSocket.close();
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
