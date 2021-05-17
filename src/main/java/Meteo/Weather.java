package Meteo;

import Storable.JSONStorage;

import java.io.IOException;
import java.util.Map;

public class Weather {
    private Map<String, Object> map ;
    private JSONStorage jsonStorage = new JSONStorage() ;

    // arrangement des valeurs du map
    private String keySetNotSorted ;
    private String[] keySet ;
    private String keyValueNotSorted ;
    private String[] keyValue ;

    public Weather() {

        // va chercher les données de l'API
        try {
            map = jsonStorage.readFromUrl("https://api.openweathermap.org/data/2.5/weather?q=Sierre&units=metric&appid=fcd518773066748f0875a9cd8791dc49") ;
        }catch (IOException i){
        System.out.println(i.getMessage());
        }

        // création d'un tableau de string depuis un keyset
        keySetNotSorted = String.join(",",map.keySet()) ;
        keySet = keySetNotSorted.split(",") ;


        // test de la classe getWeather
        String test ;
        test = getVille();
        System.out.println(test);


    }

    public String[] getWeather(){

        //tri de l'objet weather
        String weatherContent = "" ;
        weatherContent += map.get(keySet[1]) ;
        weatherContent = weatherContent.substring(2) ;
        weatherContent = weatherContent.substring(0, weatherContent.length()-2) ;

        // séparation et retour de l'objet weather
        String[] weather = weatherContent.split(",") ;
        return weather ;
    }

    public String[] getCoordinates(){

        // tri de l'objet coordinates
        String coordinatesContent = "" ;
        coordinatesContent += map.get(keySet[0]) ;
        coordinatesContent = coordinatesContent.substring(2) ;
        coordinatesContent = coordinatesContent.substring(0, coordinatesContent.length()-2) ;

        // séparation et retour de l'objet coordinates
        String[] coordinates = coordinatesContent.split(",") ;
        return coordinates ;
    }

    public String[] getTemperature(){

        // tri de l'objet coordinates
        String temperatureContent = "" ;
        temperatureContent += map.get(keySet[3]) ;
        temperatureContent = temperatureContent.substring(1) ;
        temperatureContent = temperatureContent.substring(0, temperatureContent.length()) ;

        // séparation et retour de l'objet coordinates
        String[] temperature = temperatureContent.split(",") ;
        return temperature ;
    }

    public String[] getWind(){

        // tri de l'objet coordinates
        String windContent = "" ;
        windContent += map.get(keySet[5]) ;
        windContent = windContent.substring(1) ;
        windContent = windContent.substring(0, windContent.length()-2) ;

        // séparation et retour de l'objet coordinates
        String[] wind = windContent.split(",") ;
        return wind ;
    }

    public String[] getRain(){

        // tri de l'objet coordinates
        String rainContent = "" ;
        rainContent += map.get(keySet[6]) ;
        rainContent = rainContent.substring(1) ;
        rainContent = rainContent.substring(0, rainContent.length()-2) ;

        // séparation et retour de l'objet coordinates
        String[] rain = rainContent.split(",") ;
        return rain ;
    }

    public String getVille(){

        // tri de l'objet coordinates
        String cityContent = "" ;
        cityContent += map.get(keySet[12]) ;
        cityContent = cityContent.substring(0, cityContent.length()) ;

        return cityContent ;
    }

}
