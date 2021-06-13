package Apps.Weather;

import Errors.ErrorCode;
import Errors.SmartphoneException;
import Storable.JSONStorage;

import java.io.IOException;
import java.util.Map;

public class Weather {
    private Map<String, Object> map ;
    private JSONStorage jsonStorage = new JSONStorage() ;

    // arrangement des valeurs du map
    private String keySetNotSorted ;
    private String[] keySet ;
    private String ville ;

    public Weather(String sVille) throws SmartphoneException {

        // on gère si la recherche est nulle
        if(sVille== null){
            ville = "Sierre" ;
        }
        else{
            ville = sVille ;
        }

        // création de l'URL
        String preURL = "https://api.openweathermap.org/data/2.5/weather?q=";
        String postURL = "&units=metric&appid=fcd518773066748f0875a9cd8791dc49";
        String sURL = preURL + ville + postURL ;

        // appel de la méthode qui va retourner le map selon l'url donnée
        try {
            map = jsonStorage.readFromUrl(sURL) ;
        } catch(SmartphoneException sm){
            System.out.println(sm.getErrorMessage());
            System.out.println(sm.getErrorCode());
        }

        // création d'un tableau de string depuis un keyset
        keySetNotSorted = String.join(",",map.keySet()) ;
        keySet = keySetNotSorted.split(",") ;

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


    public String getVille(){

        String checkRain ="{all" ;                                     // contenu des 4 premières cases de l'objet no.7
        String checkRain2 =  "" ;                                       // va rechercher ce qu'il y a dans la case avec le contenu no.7. si egal => pluie => une case en plus
        checkRain2 += map.get(keySet[7]);
        checkRain2 = checkRain2.substring(0, 4) ;
        System.out.println(checkRain2);


        // tri de l'objet coordinates
        String cityContent = "" ;
        if(checkRain.equals(checkRain2)){
            cityContent += map.get(keySet[12]) ;
            cityContent = cityContent.substring(0, cityContent.length()) ;
        }
        else{
            cityContent += map.get(keySet[11]) ;
            cityContent = cityContent.substring(0, cityContent.length()) ;
        }

        return cityContent ;
    }

    public String[] getSun(){

        String checkRain ="{all" ;                                     // contenu des 4 premières cases de l'objet no.7
        String checkRain2 =  "" ;                                       // va rechercher ce qu'il y a dans la case avec le contenu no.7. si egal => pluie => une case en plus
        checkRain2 += map.get(keySet[7]);
        checkRain2 = checkRain2.substring(0, 4) ;


        String sunContent = "" ;
        if(checkRain.equals(checkRain2)) {
            sunContent += map.get(keySet[9]);
            sunContent = sunContent.substring(1);
            sunContent = sunContent.substring(0, sunContent.length() - 1);
        }
        else {
            sunContent += map.get(keySet[8]);
            sunContent = sunContent.substring(1);
            sunContent = sunContent.substring(0, sunContent.length() - 1);
        }
        String[] sun = sunContent.split(",") ;


        return sun ;
    }

}
