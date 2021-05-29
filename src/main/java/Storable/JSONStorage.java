package Storable;
import Contacts.Contact;
import Gallery.Images;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;


public class JSONStorage implements Storable {

    public Contact[] read(File source, Contact[] contacts) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            contacts = mapper.readValue(source, Contact[].class);
        } catch (IOException e) {
            e.printStackTrace(); // Donne les détails de l'erreur
            //throw new IOException("Error while reading JSON file");
        }

        return contacts;
    }

    public void write(File destination, Contact[] contacts) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(destination, contacts);
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Crash while writing");
            //throw new IOException("Error while writing in JSON file");
        }
    }

    public Map readFromUrl(String sUrl) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        URL url = new URL(sUrl) ;
        Map<String,Object> map = mapper.readValue(url, Map.class);

        //sout de test
        System.out.println(map.toString());
        return map ;
    }


    //read and write IMAGES
    public ArrayList<Images> readImages(File source, ArrayList<Images> images) {
        ObjectMapper mapper = new ObjectMapper();
        Images[] imagesArray;
        try {
            imagesArray = mapper.readValue(source, Images[].class);
            images = new ArrayList<>(Arrays.asList(imagesArray)); //transformer le tableau d'iamges en Array liste images
        } catch (IOException e) {
            e.printStackTrace(); // Donne les détails de l'erreur
            //throw new IOException("Error while reading JSON file");
        }

        return images;
    }

    public void writeImages(File destination, ArrayList<Images> images) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(destination, images);
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Crash while writing Image");
            //throw new IOException("Error while writing in JSON file");
        }
    }

}
