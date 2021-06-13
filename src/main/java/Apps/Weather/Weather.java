package Apps.Weather;

import Errors.SmartphoneException;
import Storable.JSONStorage;
import java.util.Map;

public class Weather {
    private Map<String, Object> map ;
    private final String[] keySet ;

    /**
     *
     * @param sVille
     * va chercher les informations selon l'input de la ville
     */

    public Weather(String sVille) {
        String ville ;                                                                                                      // création d'une variable ville
        if(sVille == null){                                                                                                  // si le nom de la ville en input est nul, alors il prend la ville de Sierre
            ville = "Sierre" ;
        }
        else{
            ville = sVille ;                                                                                                // sinon, il prend la ville sélectionnée dans l'input du constructeur
        }
        String preURL = "https://api.openweathermap.org/data/2.5/weather?q=";                                               // création d'un string contenant le début de l'URL qui accèdera à l'API
        String postURL = "&units=metric&appid=fcd518773066748f0875a9cd8791dc49";                                            // création d'un string contenant la fin de l'URL
        String sURL = preURL + ville + postURL ;                                                                            // assemblage de l'URL "finale"
        try {
            JSONStorage jsonStorage = new JSONStorage() ;                                                                   // création de la classe JSONStorage
            map = jsonStorage.readFromUrl(sURL) ;                                                                           // va chercher le map selon l'URL modifiée
        } catch(SmartphoneException sm){                                                                                    // remonte les erreurs possibles au passage
            System.out.println(sm.getErrorMessage());                                                                       // affiche le message d'erreur
            System.out.println(sm.getErrorCode());                                                                          // affiche le conde d'erreur
        }
        String keySetNotSorted = String.join(",",map.keySet()) ;                                                    // création d'un string contenant le contenu du keyset délimité par une virgule
        keySet = keySetNotSorted.split(",") ;                                                                         // création d'un tableau de string depuis le string du dessus (grâce à la méthode .split)
    }

    /**
     *
     * @return
     * retourne un tableau de string contenant les informations relatives à la météo
     */

    public String[] getWeather(){
        String weatherContent = "" ;
        weatherContent += map.get(keySet[1]) ;                                                                              // création d'un string qui possède le contenu du tableau de string voulu (ici la météo)
        weatherContent = weatherContent.substring(2) ;                                                                      // sélection et tronquage du string (on enlève les "{}")
        weatherContent = weatherContent.substring(0, weatherContent.length()-2) ;
        return weatherContent.split(",") ;                                                                // séparation du string en tableau de string et retour de l'objet weather
    }

    /**
     *
     * @return
     * retourne un tableau de string contenant les informations relatives à la température
     */

    public String[] getTemperature(){
        String temperatureContent = "" ;
        temperatureContent += map.get(keySet[3]) ;
        temperatureContent = temperatureContent.substring(1) ;                                                              // tronquage afin d'enlever les parties non voulues
        return temperatureContent.split(",") ;                                                                        // from String to String[] et retour
    }

    /**
     *
     * @return
     * retourne un tableau de string contenant les informations relatives à la ville
     */

    public String getVille(){                                                                                               // problème : il peut y avoir un élément en plus dans le tableau, on gère ca en faisant un check de l'élément no7 (élément en plus se trouve toujours à la position no.6)
        String checkRain ="{all" ;                                                                                          // si les 3 premières lettres du contenu de la case no.7 est {all, c'est qu'il y a cette fameuse case en plus
        String checkRain2 =  "" ;
        checkRain2 += map.get(keySet[7]);                                                                                   // va rechercher ce qu'il y a dans la case avec le contenu no.7. si egal => pluie => une case en plus
        checkRain2 = checkRain2.substring(0, 4) ;                                                                           // découpe le résultat afin de ne garder que les 3 premières lettres
        String cityContent = "" ;
        if(checkRain.equals(checkRain2)){                                                                                   // if else permettant la comparaison
            cityContent += map.get(keySet[12]) ;
        }
        else{
            cityContent += map.get(keySet[11]) ;
        }
        return cityContent ;                                                                                                // retour du tableau de string
    }

    /**
     *
     * @return
     * retourne un tableau de string contenant les informations relatives au soleil
     */

    public String[] getSun(){
        String checkRain ="{all" ;                                                                                          // même processus que plus haut
        String checkRain2 =  "" ;
        checkRain2 += map.get(keySet[7]);                                                                                   // va rechercher le string de comparaison
        checkRain2 = checkRain2.substring(0, 4) ;
        String sunContent = "" ;
        if(checkRain.equals(checkRain2)) {                                                                                  // if else qui va faire le tri et choisir la bonne case
            sunContent += map.get(keySet[9]);
            sunContent = sunContent.substring(1);
            sunContent = sunContent.substring(0, sunContent.length() - 1);
        }
        else {
            sunContent += map.get(keySet[8]);
            sunContent = sunContent.substring(1);
            sunContent = sunContent.substring(0, sunContent.length() - 1);
        }
        return sunContent.split(",") ;                                                                                // redécoupage du String en tableau de String[] et retour de celui-ci
    }
}
