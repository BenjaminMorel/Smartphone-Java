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
     * @throws SmartphoneException Ajout des exceptions Smartphone
     */

    public Contact(String firstName, String lastName, String telNumber, String birthDate, String imagePath) throws SmartphoneException {
        setFirstName(firstName);
        setLastName(lastName);
        setTelNumber(telNumber);
        setBirthDate(birthDate);
        this.imagePath = imagePath;
        setFullName();
    }

    public Contact() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws SmartphoneException {

        firstName = firstName.replaceAll("\\W", "");

        if (firstName.isEmpty())
            throw new SmartphoneException("Le prénom est vide", ErrorCode.BAD_PARAMETER);

        else {
                                                                         // Mise en forme du prénom, avec 1ère lettre en majuscule et le reste en minuscule
            firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
            this.firstName = firstName;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws SmartphoneException {

        lastName = lastName.replaceAll("\\W", "");

        if (lastName.isEmpty())
            throw new SmartphoneException("Le nom est vide", ErrorCode.BAD_PARAMETER);

        else {                                                                               // Mise en forme du nom, avec 1ère lettre en majuscule et le reste en minuscule
            lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();
            this.lastName = lastName;
        }
    }

    public void setFullName() {
        this.fullName = getLastName() + getFirstName();
    }

    public String getFullName() {
        return fullName;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) throws SmartphoneException {
        if (telNumber.isEmpty())
            throw new SmartphoneException("Le numéro de téléphone est vide", ErrorCode.BAD_PARAMETER);

        else {
            for (int i = 0; i < telNumber.length(); i++) {
                if (telNumber.charAt(i) > 47 && telNumber.charAt(i) < 58) {
                    this.telNumber = telNumber;
                }
                else {
                    throw new SmartphoneException("Erreur dans le numéro de téléphone", ErrorCode.BAD_PARAMETER);
                }
            }
            // .replace all (/.,)
        }
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) throws SmartphoneException {
        if (birthDate.isEmpty()) {
            this.birthDate = birthDate;
        }
        else {
            for (int i = 0; i < birthDate.length(); i++) {
                if (birthDate.charAt(i) > 47 && birthDate.charAt(i) < 58) {
                    this.birthDate = birthDate;
                } else {
                    throw new SmartphoneException("Erreur dans la date", ErrorCode.BAD_PARAMETER);
                }
            }
        }
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
