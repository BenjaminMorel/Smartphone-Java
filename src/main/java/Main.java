
import Demo.Smartphone;
import Errors.*;
import java.io.*;
import java.nio.file.*;

/*********************************************************************************************
 *                     Main class de notre application smartphone
 *
 * Pour pouvoir lancer l'application, il est nécessaire de créer un variable d'environnement
 * nommée "HOME" pointant sur le \target\classes\ du projet
 *
 * Lien vers le GitLab : https://gitlab.com/Benjamin_Morel/smartphone
 * ******************************************************************************************/

public class Main {
    public static void main(String[] args) throws SmartphoneException {

        try {
            Smartphone smartphone = new Smartphone(null, null);
            smartphone.setVisible(true);
        } catch (SmartphoneException e) {
            System.out.println(e.getErrorMessage());
        }

        String envVariable = System.getenv("HOME");
        System.out.println("PATH de la variable d'environnement : " + envVariable);

        readContactsJSON();
        readImagesJSON();

    }

    /**
     * Test lecture du fichier Contacts.json
     * @throws SmartphoneException Exceptions smartphone
     */

    public static void readContactsJSON() throws SmartphoneException {
        File fileContact = new File(System.getenv("HOME") + "\\Contacts.json");

        if (!fileContact.exists()) {
            try {
                fileContact.createNewFile();
            } catch (IOException i){
                throw new SmartphoneException("no existing file", ErrorCode.IO_EXCEPTION) ;
            }

            String str1 = "[]";
            Path path = Paths.get(String.valueOf(fileContact));
            byte[] strToBytes = str1.getBytes();

            try {
                Files.write(path, strToBytes);
            } catch (IOException i){
                throw new SmartphoneException("Erreur lors de l'écriture dans le main", ErrorCode.IO_EXCEPTION) ;
            }
            System.out.println("Un nouveau fichier de contacts a été créé.");
        } else
            System.out.println("Un fichier de contacts est déjà existant, il sera chargé.");
    }

    /**
     * Test lecture du fichier Images.json
     * @throws SmartphoneException Exceptions smartphone
     */

    public static void readImagesJSON() throws SmartphoneException {
        File fileImages = new File(System.getenv("HOME") + "\\Images.json");

        if (!fileImages.exists()) {
            try {
                fileImages.createNewFile();
            } catch (IOException i){
                throw new SmartphoneException("Fail to create a new file in the Main", ErrorCode.IO_EXCEPTION) ;
            }

            String str2 = "[]";
            Path path = Paths.get(String.valueOf(fileImages));
            byte[] strToBytes = str2.getBytes();

            try {
                Files.write(path, strToBytes);
            } catch (IOException i){
                throw new SmartphoneException("Erreur lors de l'écriture dans le main", ErrorCode.IO_EXCEPTION) ;
            }
            System.out.println("Un nouveau fichier d'images a été créé.");
        } else
            System.out.println("Un fichier d'images est déjà existant, il sera chargé.");
    }
}
