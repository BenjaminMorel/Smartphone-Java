import Demo.Smartphone;
import Errors.ErrorCode;
import Errors.SmartphoneException;

import java.io.*;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) throws SmartphoneException {


        try {
            Smartphone smartphone = new Smartphone(null, null);
            smartphone.setVisible(true);
        }catch (SmartphoneException e){
            System.out.println(e.getErrorMessage());
        }




        System.out.println(System.getenv("HOME"));

        // Test lecture du fichier Contacts.json
        String strContacts = System.getenv("HOME");
        File contactFolder = new File(strContacts);
        contactFolder.mkdir();
        File fileContact = new File(System.getenv("HOME") + "\\Contacts.json");

            if (!fileContact.exists()) {
                try {
                    fileContact.createNewFile();
                }catch (IOException i){
                    throw new SmartphoneException("no existing file", ErrorCode.IO_EXCEPTION) ;
                }
                String str1 = "[]";
                Path path = Paths.get(String.valueOf(fileContact));
                byte[] strToBytes = str1.getBytes();
                try {
                    Files.write(path, strToBytes);
                }catch (IOException i){
                    throw new SmartphoneException("fail to write in the main", ErrorCode.IO_EXCEPTION) ;
                }
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
            try {
                fileImages.createNewFile();
            }catch (IOException i){
                throw new SmartphoneException("Fail to create a new file in the Main", ErrorCode.IO_EXCEPTION) ;
            }
            String str2 = "[]";
            Path path = Paths.get(String.valueOf(fileImages));
            byte[] strToBytes = str2.getBytes();
            try {
                Files.write(path, strToBytes);
            }catch (IOException i){
                throw new SmartphoneException("fail to write in the main", ErrorCode.IO_EXCEPTION) ;
            }
            System.out.println("Images file has been created.");
        } else {
            System.out.println("Images file already exists.");
        }
    }
}
