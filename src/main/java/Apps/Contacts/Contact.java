package Apps.Contacts;


import Errors.ErrorCode;
import Errors.SmartphoneException;

public class Contact {

    private String firstName, lastName, fullName, telNumber, birthDate, imagePath;

    /**
     * @param firstName String qui contient le prénom du contact
     * @param lastName String qui contient le nom du contatc
     * @param telNumber String qui contient le téléphone du contact
     * @param birthDate String qui contient la date de naissance du contact
     * @param imagePath String qui contient le path de l'image du contact
     * @throws SmartphoneException
     */

    public Contact(String firstName, String lastName, String telNumber, String birthDate, String imagePath) throws SmartphoneException {
        setFirstName(firstName);
        setLastName(lastName);
        setTelNumber(telNumber);
        setBirthDate(birthDate);
        this.imagePath = imagePath;
        setFullName(null);
    }

    public Contact() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws SmartphoneException {
        if (firstName.isEmpty())
            throw new SmartphoneException("Le prénom est vide", ErrorCode.BAD_PARAMETER);

        else {
            String strFirstName = firstName;                                                                            // Mise en forme du prénom, avec 1ère lettre en majuscule et le reste en minuscule
            strFirstName = strFirstName.substring(0, 1).toUpperCase() + strFirstName.substring(1).toLowerCase();
            this.firstName = strFirstName;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws SmartphoneException {
        if (lastName.isEmpty())
            throw new SmartphoneException("Le nom est vide", ErrorCode.BAD_PARAMETER);

        else {
            String strLastName = lastName;                                                                              // Mise en forme du nom, avec 1ère lettre en majuscule et le reste en minuscule
            strLastName = strLastName.substring(0, 1).toUpperCase() + strLastName.substring(1).toLowerCase();
            this.lastName = lastName;
        }
    }

    public void setFullName(String fullName) {
        fullName = getLastName() + getFirstName();
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", telNumber='" + telNumber + '\'' +
                ", birthDate='" + birthDate + '\'' +
                '}';
    }
}
