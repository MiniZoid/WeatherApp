/**
 * A weather app that retrieves data from an API based on US zip code and displays it on a GUI
 * @author Ryan Stroz
 * @version 2.2 (3/4/2022)
 */
public class AppController {
    static boolean bugReporting = false; //Utility variable for debugging. Setting true will print any errors to the console. 
    private String zipCode;
    private String url;
    private JsonParser parser;
    private City currentCity;
    AppGUI gui;
    EventListener listener;


    /**
     * Starts program by creating a JSON parser and passing it to the AppController then starts the app     *
     */
    public static void main(String [] args){
        JsonParser parser = new JsonParser();
        AppController controller = new AppController(parser); 
        controller.startApp();       
    }

    /**
     * Constructor for AppController that operates program
     * @param parser JSON parser to be used in program
     */
    public AppController(JsonParser parser){
        this.parser = parser;    
    }

    /**
     * Creates action listeners for buttons and generates app GUI
     */
    public void startApp(){
        listener = new EventListener(this);
        gui = new AppGUI(this,listener);   
    }

    /**
     * Handle Search button being pressed on app
     * takes value of text field and adds it to API url
     * parses data into printable format and displays on screen
     */
    public void searchButtonPressed(){        
        zipCode = gui.getField();
        url = "http://api.weatherapi.com/v1/current.json?key=f22bd71b6703489fb7f41918220303&q="+zipCode+"&aqi=no";
        currentCity = parser.parseAndLoad(url);
        if(parser.parsed){
            gui.setIcon(currentCity.getIcon());
            gui.setDisplayString(currentCity.toString());
        }
        else{
            gui.setIcon(null);
            gui.setDisplayString("Please enter a valid 5 digit zip code!");
        }        
        gui.repaint();
    }

    /**
     * Handles refresh button being pressed on app
     * Takes originally searched string and refreshes the data by re-parsing the JSON from the API
     */
    public void refreshButtonPressed(){
        currentCity = parser.parseAndLoad(url);
        gui.displayString = currentCity.toString();
        gui.repaint();
    }
}