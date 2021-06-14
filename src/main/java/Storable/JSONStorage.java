package Storable;
import Apps.Contacts.Contact;
import Apps.Gallery.Images;
import Errors.ErrorCode;
import Errors.SmartphoneException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.net.URL;
import java.util.*;


public class JSONStorage{

    /**
     * Méthode permettant de lire un fichier contacts JSON
     * @param source Fichier source qui va être lu par l'objectMapper
     * @param contacts ArrayList de contact
     * @return ArrayList de contacts avec les infos des contacts enregistrés
     * @throws SmartphoneException Ajout des exceptions Smartphone à la méthode
     */

    public ArrayList<Contact> read(File source, ArrayList<Contact> contacts) throws SmartphoneException {
        ObjectMapper mapper = new ObjectMapper();
        Contact[] contactsArray;

        try {
            contactsArray = mapper.readValue(source, Contact[].class);
            contacts = new ArrayList<>(Arrays.asList(contactsArray));
        } catch (IOException e) {
            throw new SmartphoneException("Erreur lors de la lecture du fichier JSON", ErrorCode.JSON_FILE) ;
        }
        return contacts;
    }

    /**
     * Méthode permettant d'écrire un fichier de contacts JSON
     * @param destination Fichier de destination dans lequel les données sont enregistrées
     * @param contacts ArrayList de contacts avec les infos des contacts enregistrés
     * @throws SmartphoneException Ajout des exceptions Smartphone à la méthode
     */

    public void write(File destination, ArrayList<Contact> contacts) throws SmartphoneException{
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(destination, contacts);
        } catch (IOException e) {
            throw new SmartphoneException("Erreurs lors de l'écriture des données dans le fichier JSON", ErrorCode.JSON_FILE) ;
        }
    }

    /**
     * Méthode permettant de lire un API
     * @param sUrl
     * prend en paramètre l'URL de l'API
     * @return
     * retourne le Map de l'API s'il l'a trouvé
     * @throws SmartphoneException
     * si retourne une erreur, c'est que la ville n'existe pas (test de connexion étage plus haut)
     */

    public Map readFromUrl(String sUrl) throws SmartphoneException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            URL url = new URL(sUrl);
            Map<String, Object> map = mapper.readValue(url, Map.class);
            return map ;
        }catch (IOException i){
            throw new SmartphoneException("Ville non trouvée", ErrorCode.BAD_PARAMETER) ;
        }
    }

    /**
     * Méthode permettant de lire des images sur un JSON
     * @param source
     * @param images
     * @return
     * @throws SmartphoneException
     */

    public ArrayList<Images> readImages(File source, ArrayList<Images> images) throws SmartphoneException{
        ObjectMapper mapper = new ObjectMapper();
        Images[] imagesArray;
        try {
            imagesArray = mapper.readValue(source, Images[].class);
            images = new ArrayList<>(Arrays.asList(imagesArray)); //transformer le tableau d'iamges en Array liste images
        } catch (IOException e) {
            throw new SmartphoneException("Error while reading the images of the JSON File", ErrorCode.JSON_FILE) ;
        }

        return images;
    }

    /**
     * Méthode permettant d'écrire des images sur un JSON
     * @param destination
     * @param images
     * @throws SmartphoneException
     */

    public void writeImages(File destination, ArrayList<Images> images) throws SmartphoneException{
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(destination, images);
        } catch (IOException e) {
            throw new SmartphoneException("Error while writing the images in the JSON file", ErrorCode.JSON_FILE) ;
        }
    }

}
