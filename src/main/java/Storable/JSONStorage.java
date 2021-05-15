package Storable;
import Contacts.Contact;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JSONStorage {

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
}
