import Apps.Contacts.Contact;
import Apps.Gallery.EditImageName;
import Apps.Gallery.Images;
import Apps.Weather.WeatherWindow;
import Demo.Smartphone;
import Errors.ErrorCode;
import Errors.SmartphoneException;
import TopBar.TopBarColor;
import TopBar.TopBarWeatherApp;
import junit.framework.TestCase;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class SmartphoneTests extends TestCase {

    /**
     * Tests application contact
     */

    @Test
    public void testEmptyFirstName() {
        int errorCode = 0;
        try {
            Contact myContact = new Contact("  ", "Morel", "079 123 45 67", "", "Images/ContactApp/Contact.png");
        } catch (SmartphoneException sme) {
            errorCode = sme.getErrorCode();
        }
        assertEquals(100, errorCode);
    }

    @Test
    public void testEmptyLastName() {
        int errorCode = 0;
        try {
            Contact myContact = new Contact("Benjamin", "  ", "079 123 45 67", "", "Images/ContactApp/Contact.png");
        } catch (SmartphoneException sme) {
            errorCode = sme.getErrorCode();
        }
        assertEquals(100, errorCode);
    }

    @Test
    public void testEmptyTelNumber() {
        int errorCode = 0;
        try {
            Contact myContact = new Contact("Benjamin", "Morel", "  ", "", "Images/ContactApp/Contact.png");
        } catch (SmartphoneException sme) {
            errorCode = sme.getErrorCode();
        }
        assertEquals(100, errorCode);
    }

    @Test
    public void testWrongTelNumber() {
        int errorCode = 0;
        try {
            Contact myContact = new Contact("Benjamin", "Morel", "leNumeroDeTelephone", "", "Images/ContactApp/Contact.png");
        } catch (SmartphoneException sme) {
            errorCode = sme.getErrorCode();
        }
        assertEquals(100, errorCode);
    }

    @Test
    public void testWrongBirthDate() {
        int errorCode = 0;
        try {
            Contact myContact = new Contact("Benjamin", "Morel", "012 345 67 89", "laDateDanniversaire", "Images/ContactApp/Contact.png");
        } catch (SmartphoneException sme) {
            errorCode = sme.getErrorCode();
        }
        assertEquals(100, errorCode);
    }


    /**
     * Tests application météo
     */

    @Test
    public void testFalseCityWeather(){
        int errorcode = 0;
        String stringError = "";

        try{
            new Smartphone(null,null) ;
        }catch (SmartphoneException sm){
            errorcode = sm.getErrorCode();
            stringError = sm.getErrorMessage() ;
        }

        try{
            new Smartphone(new WeatherWindow("jskdgva"), new TopBarWeatherApp()) ;
        }catch (SmartphoneException smW){
            errorcode = smW.getErrorCode();
            stringError = smW.getErrorMessage() ;
        }
        assertEquals(ErrorCode.BAD_PARAMETER.getCode(),errorcode);
        assertEquals("Ville non trouvée", stringError);
    }

    @Test
    public void testTrueCityWeather(){
        int errorcode = 0;
        String stringError = "";

        try{
            new Smartphone(null,null) ;
        }catch (SmartphoneException sm){
            errorcode = sm.getErrorCode();
            stringError = sm.getErrorMessage() ;
        }

        try{
            new Smartphone(new WeatherWindow("Lausanne"), new TopBarWeatherApp()) ;
        }catch (SmartphoneException smW){
            errorcode = smW.getErrorCode();
            stringError = smW.getErrorMessage() ;
        }
        assertEquals(0,errorcode);
        assertEquals("", stringError);
    }


    /**
     * Tests application gallerie
     */

    @Test
    public void testRename() {

        String path = "ImagesGallery/Augustine.jpg";

        Images image = new Images("ImagesGallery/dog.png");
        //créer fichier
        File f = new File(System.getenv("HOME") + path);
        f.renameTo(new File(System.getenv("HOME") + image.getName()));

        assertEquals("ImagesGallery/dog.png", image.getName());

    }

}
