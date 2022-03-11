package com.minizoid;

import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;
import javax.json.*;
import java.awt.*;

/**
 * Json parser that grabs data from a weather API and parses data down to Java Object representing a City and its current weather conditions
 */
public class JsonParser {

    private JsonObject locationData, currentData, conditionData, jsonData;
    private Image icon;
    private City cityData;
    String url;
    boolean parsed;
    
    /**
     * Constructor for Parser.
     */
    public JsonParser(){
        url = null;
        parsed = false;
    }
    
    /**
     * Parser for JSON.
     * Parses JSON from weather API, extracts the data to localized values, and stores it in a Java Object.
     * If invalid URL is given Parser Error is thrown.
     * @param url URL to parse JSON from
     * @return City object with populated fields based on the given URL.
     */
    public City parseAndLoad(String url){    
        try{
            this.url = url;
            URL site = new URL(url);
            URLConnection urlcon = site.openConnection();
            InputStream stream = urlcon.getInputStream();
            JsonReader jsonReader = Json.createReader(stream);
            JsonStructure js = jsonReader.read();
            jsonReader.close();
            stream.close();

            jsonData = (JsonObject)js;
            locationData = jsonData.getJsonObject("location"); 
            currentData = jsonData.getJsonObject("current");            
            conditionData = currentData.getJsonObject("condition");

            String unknown = "??";
            String town = locationData.getString("name",unknown);
            String state = locationData.getString("region",unknown);
            String time = locationData.getString("localtime",unknown);
            double temp_c = currentData.getJsonNumber("temp_c").doubleValue();
            double temp_f = currentData.getJsonNumber("temp_f").doubleValue();
            double feelsLikeTemp_F = currentData.getJsonNumber("feelslike_f").doubleValue();
            double feelsLikeTemp_C = currentData.getJsonNumber("feelslike_c").doubleValue();
            String condition = conditionData.getString("text",unknown);  
            String iconUrlString = conditionData.getString("icon", unknown);
            URL iconURL = new URL("https:"+iconUrlString);
            icon = ImageIO.read(iconURL);

            cityData = new City(town,state);
            cityData.setTemp_F(temp_f);
            cityData.setTemp_C(temp_c);
            cityData.setFeelsLikeF(feelsLikeTemp_F);
            cityData.setFeelsLikeC(feelsLikeTemp_C);
            cityData.setCondition(condition);
            cityData.setTime(time);
            cityData.setIcon(icon);

            parsed = true;

            return cityData;
        }
        catch (Exception e){
            if(AppController.bugReporting){
                System.err.println("Error parsing JSON from URL");
                e.printStackTrace();
            }
            parsed = false;
        }
        return null;        
    }

    public boolean isParsed() {
        return parsed;
    }
}


