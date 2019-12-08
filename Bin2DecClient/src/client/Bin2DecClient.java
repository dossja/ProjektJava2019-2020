package client;

import java.io.*;
import java.net.*;
import java.util.Properties;
import view.View;

/**
 *
 * @author Dominika Matyja
 * @version 1.0
 */
public class Bin2DecClient {

    /** Socket which is connencting with server*/
    private Socket clientSocket;
    
    /** Output stream*/
    private PrintWriter out;
    
    /** Input stream*/
    private BufferedReader in;
    
    /** Number of used port*/
    private int PORT;
    
    /** IP address of server*/
    private String addr;
    
    /** Object of class view of the application*/
    private View view;
    
    /** Boolean of information about connection:
     * True - successful, False - unsuccessful
     */
    private boolean isServerConnected;
 
    /**
     * Argumentless constructor of the class
     * Firstly reads PORT and addr form file config.properties
     * After that it connects to the server with those informations
     */
    
    Bin2DecClient()
    {
        /** At first the boolean is changed into false,
         * the PORT is 0, and addr is an empty String*/
        isServerConnected = false;
        PORT = 0;
        addr = "";
        
        /** Creates new View for the client and new properites object*/
        view = new View();
        
        Properties prop = new Properties();
        
        /** Tries to open the file and load the properties,
         * if unsuccesful - throws exception*/
        try (FileInputStream inFile = new FileInputStream("config.properties"))
        {
            prop.load(inFile);
            addr = prop.getProperty("IPaddress");
            PORT = Integer.parseInt(prop.getProperty("PORT"));
        }
        catch (IOException | NumberFormatException ex)
        {
            view.printEx("Exception: " + ex + " from configuration files!");
        }
        
        /** Tries to create new clientSocket and connect to the server,
         * throws exception if unsuccesful*/
        try
        {
            clientSocket = new Socket(addr, PORT);
            if (clientSocket != null && clientSocket.isConnected())
            {
                isServerConnected = true;
                view.printMsg("Client is connected to the server!");
            }
            out = new PrintWriter( 
                    new BufferedWriter(
                        new OutputStreamWriter(
                            clientSocket.getOutputStream())), true);
            
            in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
        }
        catch (IOException ex)
        {
            view.printEx("Exception: " + ex 
                    + " during connection to the server!");
        }
       
    }

    /**
     * Method for sending answert to server, after server responded to View
     * @param req message to be sent to the server
     */
    
    public void clientSendRequest(String req)
    {
        /** Tries to get message from the server,
         * if unsuccesful of not connected, throws exception */
        
        try
        {
            if(!isServerConnected)
                return;
            
            req = req.toUpperCase();
            out.println(req);
            
            in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            
            String resp = in.readLine();

            if (resp == null || resp.isEmpty())
                view.printEx("Server not responding!");
            
            else
                view.printMsg(resp);
        }
        catch (IOException ex)
        {
            view.printEx("Exception: " + ex
                    + " cannot connect with server!");
        }
    }


    /**
     * Method used to connect with server
     */
    
    public void runClient()
    {
        boolean endConnection = false;
        
        do
        {
            try
            {
                out = new PrintWriter(
                    new BufferedWriter(
                        new OutputStreamWriter(
                            clientSocket.getOutputStream())), true);
                
                String clientResp;
                
                while (clientSocket.isConnected()
                        && (clientResp = view.getReq()) != null)
                    {
                          if (!clientSocket.isConnected())
                              break;
                          
                          else if (clientResp.toUpperCase().equals("EXIT"))
                          {
                              view.printMsg("Exiting the program");
                              endConnection = true;
                              break;
                          }
                          
                          view.printMsg("Sending command to server: "
                                  + clientResp);
                          this.clientSendRequest(clientResp);                          view.printMsg("Request has been sended!");
                    }
            }
            catch (IOException ex)
            {
                view.printEx("Exception: " + ex
                    + " cannot connect with server!");
            }
        } while (!endConnection);
    }
    
     /**
     * Method used to connect with server,
     * also tries to send request for arguments from command line
     * @param arg is an array with arguments from command line
     */
    
    public void runClient(String[] arg)
    {
        try
        {
            out = new PrintWriter(
                    new BufferedWriter(
                        new OutputStreamWriter(
                            clientSocket.getOutputStream())), true);
            
            while (clientSocket.isConnected())
            {
                for (String l: arg)
                {
                    view.printMsg("Sending to server:\t" + l);
                    this.clientSendRequest(l);
                }
                break;
            }
        }
        catch (ArrayIndexOutOfBoundsException ex)
        {
            view.printMsg("Please send correct form of request!");
        }
        
        catch (IOException ex)
        {
            view.printEx("Exception: " + ex
                    + " cannot connect with server!");
        }
    }
    
    /**
     * Main method of this project, which creates new client (as an object)
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Bin2DecClient client = new Bin2DecClient();
        
        client.runClient(args);
        
        while (true)
        {
            client.runClient();
        }
    }
    
}
