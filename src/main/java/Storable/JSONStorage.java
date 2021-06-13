package Storable;
import Apps.Contacts.Contact;
import Apps.Gallery.Images;
import Errors.ErrorCode;
import Errors.SmartphoneException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;


public class JSONStorage{

    public ArrayList<Contact> read(File source, ArrayList<Contact> contacts) throws SmartphoneException {
        ObjectMapper mapper = new ObjectMapper();
        Contact[] contactsArray;

        try {
            contactsArray = mapper.readValue(source, Contact[].class);
            contacts = new ArrayList<>(Arrays.asList(contactsArray));
        } catch (IOException e) {
            throw new SmartphoneException("Error while reading JSON file", ErrorCode.JSON_FILE) ;
        }
        return contacts;
    }

    public void write(File destination, ArrayList<Contact> contacts) throws SmartphoneException{
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(destination, contacts);
        } catch (IOException e) {
            throw new SmartphoneException("Error while writing in JSON file", ErrorCode.JSON_FILE) ;
        }
    }

    public Map readFromUrl(String sUrl) throws SmartphoneException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            URL url = new URL(sUrl);
            Map<String, Object> map = mapper.readValue(url, Map.class);
            return map ;
        }catch (IOException i){
            throw new SmartphoneException("Error while reading the URL", ErrorCode.CONNEXION_ERROR) ;
        }
    }


    //read and write IMAGES
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

    public void writeImages(File destination, ArrayList<Images> images) throws SmartphoneException{
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(destination, images);
        } catch (IOException e) {
            throw new SmartphoneException("Error while writing the images in the JSON file", ErrorCode.JSON_FILE) ;
        }
    }

}
