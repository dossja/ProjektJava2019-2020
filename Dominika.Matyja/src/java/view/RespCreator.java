package view;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for creating the response to servlet request
 * Returns it as a HTML page
 *
 * @author Dominika Matyja
 * @version 1.0
 */
public class RespCreator {
    /**
     * Text of the label above the first text field
     */
    private String l_NumberIN;
    
    /**
     * Text of the label above the second text field
     */
    private String l_NumberOUT;
    
    /**
     * Error message
     */
    private String l_Error;
    
    /**
     * Value of first text field
     */    
    private String viewIN;
    
    /**
     * Value of second text field
     */
    private String viewOUT;
    
    /**
     * List of values of previous conversions
     */
    private List<String> convHistory;
    
    /**
     * Response from servlet
     * It contains code of HTML page
     */
    private String response;
    
    /**
     * Integer with number of conversions to display via Cookie
     * It iterates after every conversion
     */
    private int numberOfConversions;
    
    /**
     * Argumentless constructor
     * It sets the proper fields values and creates new conversion history
     */
    public RespCreator()
    {
        l_NumberIN = "Binary";
        l_NumberOUT = "Decimal";
        
        l_Error = "";
        
        viewIN = "";
        viewOUT = "";
        
        convHistory = new ArrayList<>();
        
        response = "";
        
        numberOfConversions = 0;
    }
    
    /**
     * Sets value of the first label
     * @param value new value of the label
     */
    public void setNumberIN(String value)
    {
        l_NumberIN = value;
    }
    
    /**
     * Sets value of the second label
     * @param value new value of the label
     */
    public void setNumberOUT(String value)
    {
        l_NumberOUT = value;
    }
    
    /**
     * Sets value of the error label
     * @param value new value of the label
     */
    public void setError(String value)
    {
        l_Error = value;
    }
    
    /**
     * Sets value of the first text field
     * @param value new value of the text field
     */
    public void setViewIN(String value)
    {
        viewIN = value;
    }

    
    /**
     * Sets value of the second text field
     * @param value new value of the text field
     */    
    public void setViewOUT(String value)
    {
        viewOUT = value;
    }
    
        
    /**
     * Sets value of the number of conversions label
     * @param number new value of the label
     */
    public void setNumberOfConversions(int number)
    {
        numberOfConversions = number;
    }
    
    /**
     * Adds new element into history of conversions
     * If greater than 10, then removes the first value (index 0)
     * @param conversion 
     */
    public void addNewHistoryElement(String conversion)
    {
        if(convHistory.size() >= 10)
        {
            convHistory.remove(0);
        }
        
        convHistory.add("<li>" + conversion + "</li>");
    }
    
    /**
     * Cleras history of conversions (when the proper submit button is clicked)
     */
    public void deleteHistory()
    {
        convHistory.clear();
    }
    
    /**
     * Gets the number from first text field
     * @return number to convert
     */
    public String getNumberIN()
    {
        return l_NumberIN;
    }
    
    /**
     * Gets the number for second text field
     * @return output of conversion
     */
    public String getNumberOUT()
    {
        return l_NumberOUT;
    }
    
    /**
     * Function from getting the response from servlet
     * @return response as HTML page
     */
    public String getResp()
    {
        return response;
    }
    
    /**
     * Creates response from servlet as code for HTML page
     */
    public void createResp()
    {
        String convHistoryStr = "";
        convHistoryStr = convHistory.stream().map((str) -> str).reduce(convHistoryStr, String::concat);
        
        response = "<!DOCTYPE html>\n" +
            "\n" +
            "<html>\n" +
            "    <head>\n" +
            "        <title>Bin2Dec Program</title>\n" +
            "        <meta charset=\"UTF-8\">\n" +
            "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    </head>\n" +
            "    <body>\n" +
            "        <div id = \"view\">\n" +
            "            <h1 id = \"l_Title\" style = \"text-align: center\"> Welcome to the Binary to Decimal converter! </h1>\n" +
            "			\n" +
                         
            "                <form action = \"Convert\" method = \"POST\" style = \"text-align: center\">\n" +
            "                    <h4 id = \"l_NumberIN\" style = \"text-align: center\">" + l_NumberIN +  "</h4>\n" +
            "                    <input id = \"viewIN\" type = \"text\" value = \"" + viewIN + "\" name = \"viewIN\">\n" +
            "                    <h4 id = \"l_NumberOUT\" style = \"text-align: center\"><br>" + l_NumberOUT + "</h4>\n" +
            "                    <input id = \"viewOUT\" type = \"text\" value = \"" + viewOUT + "\" name = \"viewOUT\"> <br> <br>\n" +
            "                    <input id = \"convert\" type = \"submit\" value = \"Convert\"> \n" +
            "                    <br>\n" +
            "                </form>\n" +
            "                \n" +
            "                <form action = \"Clean\" method = \"POST\" style = \"text-align: center\">\n" +
            "                    <h3 id = \"l_Clean\" style = \"text-align: center\"> <br> Button to clean the numbers: <br> </h3>\n" +
            "                    <input id = \"clean\" type = \"submit\" value = \"Clean\" />\n" +
            "                </form>\n" +
            "                \n" +
            "                <form action = \"Change\" method = \"POST\" style = \"text-align: center\">\n" +
            "                    <h3 id = \"l_Change\" style = \"text-align: center\"> <br> Button to change the direction of conversion: <br> </h3>\n" +
            "                    <input id = \"change\" type = \"submit\" value = \"Change\" />\n" +
            "                </form>\n" +
            "            \n" +
            "            <div id = \"error\" style = \"text-align: center\">" + l_Error + "</div>\n" +
            "            <h3 id = \"l_History\" style = \"text-align: center\">Conversion history: </h3>  \n" +
            "                <div id = \"convHistory\" style = \"text-align: center\">" + convHistoryStr + "</div>\n" +
            "                <form action = \"Delete\" method = \"POST\" style = \"text-align: center\">\n" +
            "                    <input id = \"delete\" type = \"submit\" value = \"Delete\" />\n" +
            "                </form>\n" +
            "           <h3 id =\"cookie\" style = \"text-align: center\"> You have done: " + numberOfConversions + " conversions.</h3>\n" +
            "        </div>\n" +
            "    </body>\n" +
            "</html>";
    }
}
