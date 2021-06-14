
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
     * @throws SmartphoneException Ajout des exceptions Smartphone à la méthode
     */

    public static void readContactsJSON() throws SmartphoneException {
        File fileContact = new File(System.getenv("HOME") + "\\Contacts.json");

        // Test si le fichier existe
        if (!fileContact.exists()) {
            try {
                fileContact.createNewFile();
            } catch (IOException i){
                throw new SmartphoneException("Le fichier de contacts n'existe pas", ErrorCode.IO_EXCEPTION) ;
            }

            String str1 = "[]";
            Path path = Paths.get(String.valueOf(fileContact));
            byte[] strToBytes = str1.getBytes();

            // Test écriture dans le fichier
            try {
                Files.write(path, strToBytes);
            } catch (IOException i){
                throw new SmartphoneException("Impossible d'écriture dans le fichier de contacts", ErrorCode.IO_EXCEPTION) ;
            }
            System.out.println("Un nouveau fichier de contacts a été créé.");

            // Test si le fichier est corrompu
        } else {
            if (!fileContact.canRead()) {
                throw new SmartphoneException("Erreur fichier corrompu", ErrorCode.IO_EXCEPTION);
            }
            else
                System.out.println("Un fichier de contacts est déjà existant, il sera chargé.");
        }

    }

    /**
     * Test lecture du fichier Images.json
     * @throws SmartphoneException Ajout des exceptions Smartphone à la méthode
     */

    public static void readImagesJSON() throws SmartphoneException {
        File fileImages = new File(System.getenv("HOME") + "\\Images.json");

        // Test si le fichier existe
        if (!fileImages.exists()) {
            try {
                fileImages.createNewFile();
            } catch (IOException i) {
                throw new SmartphoneException("Le fichier d'images n'existe pas", ErrorCode.IO_EXCEPTION);
            }

            // Test écriture dans le fichier
            String str2 = "[]";
            Path path = Paths.get(String.valueOf(fileImages));
            byte[] strToBytes = str2.getBytes();

            try {
                Files.write(path, strToBytes);
            } catch (IOException i) {
                throw new SmartphoneException("Impossible d'écriture dans le fichier de contacts", ErrorCode.IO_EXCEPTION);
            }
            System.out.println("Un nouveau fichier d'images a été créé.");

            // Test si le fichier est corrompu
        } else {
            if (!fileImages.canRead()) {
                throw new SmartphoneException("Erreur fichier corrompu", ErrorCode.IO_EXCEPTION);
            } else
                System.out.println("Un fichier d'images est déjà existant, il sera chargé.");
        }
    }
}
