import Demo.Smartphone;

import java.io.*;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) throws IOException {

        Smartphone smartphone = new Smartphone(null, null);
        smartphone.setVisible(true);
        System.out.println(System.getenv("HOME"));

        // Test lecture du fichier Contacts.json
        String strContacts = System.getenv("HOME");
        File contactFolder = new File(strContacts);
        contactFolder.mkdir();
        File fileContact = new File(System.getenv("HOME") + "\\Contacts.json");
        if (!fileContact.exists()) {
            fileContact.createNewFile();
            String str1 = "[]";
            Path path = Paths.get(String.valueOf(fileContact));
            byte[] strToBytes = str1.getBytes();
            Files.write(path, strToBytes);
            System.out.println("Contacts file has been created.");
        } else {
            System.out.println("Contacts file already exists.");
        }

        // Test lecture du fichier Images.json
        String strImages = System.getenv("HOME");
        File ImagesFolder = new File(strImages);
        contactFolder.mkdir();
        File fileImages = new File(System.getenv("HOME") + "\\Images.json");
        if (!fileImages.exists()) {
            fileImages.createNewFile();
            String str2 = "[]";
            Path path = Paths.get(String.valueOf(fileImages));
            byte[] strToBytes = str2.getBytes();
            Files.write(path, strToBytes);
            System.out.println("Images file has been created.");
        } else {
            System.out.println("Images file already exists.");
        }
    }
}
