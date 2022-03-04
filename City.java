import java.awt.Image;

/**
 * A POJO representing a City, its weather conditions, and time.
 */
public class City{    
    
    private String town, state, condition; 
    private String[] formattedTime;
    private double temp_F, temp_C, feelsLikeTemp_F, feelsLikeTemp_C ;
    private Image icon;

    /**
     * Constructor for City object
     * @param town Name of Town
     * @param state State town is located in
     */
    public City(String town, String state){
        this.town = town;
        this.state = state;
    }

    /**
     * Setter method for temperature in Fahrenheit
     * @param temp value for temp to be set to
     */
    public void setTemp_F(double temp){
        temp_F = temp;
    }

    /**
     * Setter method for temperature in Celsius
     * @param temp value for temp to be set to
     */
    public void setTemp_C(double temp){
        temp_C = temp;
    }

    /**
     * Setter method for feels like temperature in Fahrenheit
     * @param temp value for temp to be set to
     */
    public void setFeelsLikeF(double temp){
        this.feelsLikeTemp_F = temp;
    }

    /**
     * Setter method for feels like temperature in Celsius
     * @param temp value for temp to be set to
     */
    public void setFeelsLikeC(double temp){
        this.feelsLikeTemp_C = temp;
    }

    /**
     * Setter method for current weather condition
     * @param condition String value for current weather condition
     */
    public void setCondition(String condition){
        this.condition = condition;
    }

    /**
     * Setter method for Icon representing the weather
     * @param icon Image to be displayed as weather icon
     */
    public void setIcon(Image icon){
        this.icon = icon;
    }

    /**
     * Getter method for current weather icon
     * @return Image of current icon
     */
    public Image getIcon(){
        return icon;
    }

    /**
     * Setter method for local time in city.
     * Time is returned as an Array of Strings.
     * Position [0] is the current date (yyyy/mm/dd)
     * Position [1] is the current time (hh:mm)
     * @param time String for time to be formatted with. Date and time must be delimited by a space " "
     */
    public void setTime(String time){
        formattedTime = time.split(" ", 2);
    }

    /**
     * Getter method to return time.
     * Returns from String Array.
     * Position [1] is the date (yyyy/mm/dd)
     * Position [2] is the time (hh:mm)
     * @param i index of array to be returned. [0] returns date. [1] returns time.
     * @return String value of formatted time.
     */
    public String getTime(int i){
        return formattedTime[i];
    }

    @Override
    public String toString() {
        String output = "Location:\n\n  " + town + ", " + state + 
        "\n\n\nCurrent temp:\n\n  " + temp_F +" F\u00B0 / "+temp_C +" C\u00B0"+
        "\n\nFeels Like: \n\n  " + feelsLikeTemp_F + " F\u00B0 / " + feelsLikeTemp_C + " C\u00B0"+
        "\n\n\nCurrent Condition:\n\n  " + condition +
        "\n\n\nData last updated:\n  " + formattedTime[0] +"\n  " + formattedTime[1];
        return output;
    }

}
