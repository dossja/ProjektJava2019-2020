package server;

import java.io.*;
import java.net.*;
import java.util.Properties;

/**
 * The main class of the server
 * 
 * @author Dominika Matyja
 * @version 1.0
 */
public class Bin2DecServer implements Closeable{
    
    /** Port number */
    private int PORT;

    /** Field represents the socket waiting for client connections */
    private ServerSocket serverSocket;

    /**
     * Argumentless constructor for server class
     * Creates the server socket
     * @throws IOException when port is already bind
     */
    
    Bin2DecServer()
    {
        Properties properties = new Properties();
        
        try (FileInputStream in = new FileInputStream("config.properties")) 
        {
            properties.load(in);
            PORT = Integer.parseInt(properties.getProperty("PORT"));
        }
        catch (IOException | NumberFormatException ex)
        {
            System.out.println(ex.getMessage());
        }
        try
        {
            serverSocket = new ServerSocket(PORT);
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * The main application method
     * @param args all parametres are ignored
     */
    
    public static void main(String args[])
    {
        try (Bin2DecServer tcpServer = new Bin2DecServer())
        {
            System.out.println("Server started!");
            while (true)
            {
                Socket socket = tcpServer.serverSocket.accept();
                System.out.println("Client connected!");
                try (SingleService singleService = new SingleService(socket))
                {
                    singleService.handleConnection();
                }
                catch (IOException ex)
                {
                    System.err.println(ex.getMessage());
                }
            }
        } 
        catch (IOException ex)
        {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Overrides the base class function, in order to close the server
     * @throws IOException if cannot close the server
     */
    
    @Override
    public void close() throws IOException 
    {
        if (serverSocket != null)
        {
            serverSocket.close();
        }
    }
}
