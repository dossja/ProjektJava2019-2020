package server;

import java.io.*;
import java.net.*;
import model.Model;
import model.NegativeNumber;
import model.NotBinary;

/**
 * The server class servicing a single connection
 * 
 * @author Dominika Matyja
 * @version 1.0
 */
public class SingleService implements Closeable
{
    
    /** socket representing connection to the client */
    private Socket socket;
    
    /** buffered input character stream */
    private BufferedReader in;
    
    /** Formatted output character stream */
    private PrintWriter out;
    
    /** Class Model object to use the logic of conversion*/
    private Model model;
    
    /** String with request from the client*/
    private String request;
    
    /** String with server's response for the client*/
    private String response;

    /**
     * The constructor of instance of the SingleService class.
     * Use the socket as a parameter.
     * @param socket socket representing connection to the client
     */
    
    public SingleService(Socket socket)
    {
        model = new Model();
        try
        {
            this.socket = socket;
            out = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    socket.getOutputStream())), true);

            in = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
        
        /* Sets request and response into empty Strings*/
        request = "";
        response = "";
    }

    /**
     * Realizes the service
     */
    
    public void handleConnection() {
        try 
        {
            while (true)
            {
                request = in.readLine();
                System.out.println("Server recives: " + request);
                this.setResp();
                out.println(response);
                
                System.out.println("Server responded: " + response);
            }
        }
        catch (IOException ex) 
        {
            System.err.println(ex.getMessage());
        }
        finally
        {
            try
            {
                socket.close();
            }
            catch (IOException ex) 
            {
                System.err.println(ex.getMessage());
            }
        }
    }

    /**
     * Method for setting right response for different requests
     */
    @SuppressWarnings("Unused")
    public void setResp()
    {
        if((request == null) || (request.equals("")))
        {
            response = "";
        }
        
        else if(request.contains("-"))
        {
            try
            {
                String[] arr = request.split("-");
                
                switch (arr[0])
                {
                    case "B2D":
                        model.convert(true, arr[1]);
                        response = model.getResult();
                        break;
                                        
                    case "D2B":
                        model.convert(false, arr[1]);
                        response = model.getResult();
                        break;
                        
                    default:
                        response = "Incorrect converison command!";
                        
                }
            }
            catch (ArrayIndexOutOfBoundsException ex)
            {
                response = "Incorrect converison text!";
            }
            catch (NumberFormatException ex)
            {
                response = ex.getMessage();
                boolean failed = true;
            }   
        }
        
        else
        {
            switch(request)
            {
                case "HELP":
                    response = "Help instructions: "
                            + "-> 'b2d-number' for converting Binary to Decimal; "
                            + "-> 'd2b-number' for converting Decimal to Binary; "
                            + "-> 'exit' to exit the app";
                    break;
                   
                default:
                    response = "Unknown command";
            }
        }
    }
    
    /**
     * Overrides the function from base class
     * @throws IOException if it is not able to close the socket
     */
    
    @Override
    public void close() throws IOException {
        if (socket != null)
        {
            socket.close();
        }
    }
}
