package General;

import Errors.ErrorCode;
import Errors.SmartphoneException;

public class Internet {
    private final String OS = System.getProperty("os.name").toLowerCase();                                                  // nom du système mis en minuscule afin de pouvoir le comparer

    /**
     * Constructeur
     */

    public Internet() { }

    /**
     * Méthode qui check si l'OS tourne sous windows
     * @return
     * return true si oui, false si non
     */

    public boolean isWindows() {
        return OS.contains("win");                                                                                          // méthode qui va vérifier le sysstème d'exploitation de votre ordinateur
    }

    /**
     * Méthode qui check si l'OS tourne sous Mac
     * @return
     * return true si oui, false si non
     */

    public boolean isMac() {
        return OS.contains("mac");                                                                                          // méthode qui va vérifier le sysstème d'exploitation de votre ordinateur
    }

    /**
     * Méthode qui va ping un site internet
     * @param host
     * String contenant le site internet devant être accédé
     * @return
     * return true si il y a accès, false si il n'y a pas accès
     */

    public boolean isReachableByPing(String host) throws SmartphoneException {
        try {
            String cmd = "";
            if (isWindows()) {
                cmd = "ping -n 1 " + host;                                                                                  // commande pour ping sur Windows : "ping -n 1 " + host;
            }
            if (isMac()) {
                cmd = "ping -c 1 " + host;                                                                                  // commande pour ping sur Mac : "ping -c 1 " + host;
            }

            Process pingProcess = Runtime.getRuntime().exec(cmd);                                                           // action d'exécuter le ping en fonction de la commande choisie ci-dessus
            pingProcess.waitFor();                                                                                          // attends la réponse avant de continuer

            if (pingProcess.exitValue() == 0) {                                                                             // pingProcess retourne 0 si il a accès à internet
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            throw new SmartphoneException("Erreur lors du PING", ErrorCode.CONNEXION_ERROR) ;                               // au cas ou il y a un problème lors de l'exécution de la commande du PING, balance une erreur
        }
    }
}
