package Apps.Contacts;

import java.io.File;

/**
 * Interface à implémenter dans les classes de modifications, afin de modifier le fichier Json
 */

public interface ContactInterace {

    void saveInJsonStorage (File destination) throws Exception;


}
