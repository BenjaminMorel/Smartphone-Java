package Apps.Gallery;

import Errors.ErrorCode;
import Errors.SmartphoneException;

public class Images {

    private String name = ""; //path complet avec nom

    /**
     * constructor qui contient a comme paramètre le path complet avec nom
     * @param name
     */
    public Images(String name)
    {
        this.name = name;
    }

    /**
     * constructor par defaut
     */
    public Images()
    {

    }

    /**
     * methode getName
     * @return name
     */
    public String getName()
    {
        return name;
    }

    /**
     *
     * @param name
     * @throws SmartphoneException
     */
    public void setName(String name) {
        this.name = name;
    }
}
