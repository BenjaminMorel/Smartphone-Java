import Apps.Contacts.Contact;
import Apps.Gallery.Images;
import Errors.SmartphoneException;
import junit.framework.TestCase;
import org.junit.Test;

public class SmartphoneTests extends TestCase {

    /**
     * Tests application contact
     */

    @Test
    public void testEmptyFirstName() {
        int errorCode = 0;
        try {
            Contact myContact = new Contact("  ", "Morel", "079 123 45 67", "01/02/0345", "Images/ContactApp/Contact.png");
        } catch (SmartphoneException sme) {
            errorCode = sme.getErrorCode();
        }
        assertEquals(100, errorCode);
    }

    @Test
    public void testEmptyLastName() {
        int errorCode = 0;
        try {
            Contact myContact = new Contact("Benjamin", "  ", "079 123 45 67", "01/02/0345", "Images/ContactApp/Contact.png");
        } catch (SmartphoneException sme) {
            errorCode = sme.getErrorCode();
        }
        assertEquals(100, errorCode);
    }

    @Test
    public void testEmptyTelNumber() {
        int errorCode = 0;
        try {
            Contact myContact = new Contact("Benjamin", "Morel", "  ", "01/02/0345", "Images/ContactApp/Contact.png");
        } catch (SmartphoneException sme) {
            errorCode = sme.getErrorCode();
        }
        assertEquals(100, errorCode);
    }

    @Test
    public void testWrongTelNumber() {
        int errorCode = 0;
        try {
            Contact myContact = new Contact("Benjamin", "Morel", "ouiLeNumero", "01/02/0345", "Images/ContactApp/Contact.png");
        } catch (SmartphoneException sme) {
            errorCode = sme.getErrorCode();
        }
        assertEquals(100, errorCode);
    }

    @Test
    public void testWrongBirthDate() {
        int errorCode = 0;
        try {
            Contact myContact = new Contact("Benjamin", "Morel", "079 123 45 67", " ", "Images/ContactApp/Contact.png");
        } catch (SmartphoneException sme) {
            errorCode = sme.getErrorCode();
        }
        assertEquals(100, errorCode);
    }






    /**
     * Tests application météo
     */






    /**
     * Tests application gallerie
     */



}
