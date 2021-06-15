package Apps.Contacts;


import Errors.*;


public class Contact {

    private String firstName, lastName, fullName, telNumber, birthDate, imagePath;

    /**
     * Constructeur de la classe Contact contenant les informations personnelles du contact
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

    public Contact() throws SmartphoneException {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws SmartphoneException {

        firstName = firstName.trim();

        if (firstName.isEmpty())
            throw new SmartphoneException("Le prénom est vide", ErrorCode.BAD_PARAMETER);

        else {
            firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();                 // Mise en forme du prénom, avec 1ère lettre en majuscule et le reste en minuscule
            this.firstName = firstName;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws SmartphoneException {

        lastName = lastName.trim();

        if (lastName.isEmpty())
            throw new SmartphoneException("Le nom est vide", ErrorCode.BAD_PARAMETER);

        else {
            lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();                    // Mise en forme du nom, avec 1ère lettre en majuscule et le reste en minuscule
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

        telNumber = telNumber.replaceAll(" ", "");

        if (telNumber.isEmpty())
            throw new SmartphoneException("Le numéro de téléphone est vide", ErrorCode.BAD_PARAMETER);

        if (telNumber.length() != 10)
            throw new SmartphoneException("Le numéro de téléphone n'est pas valide\nFormat: 012 345 67 89", ErrorCode.BAD_PARAMETER);

        else {
            for (int i = 0; i < telNumber.length(); i++) {
                if (telNumber.charAt(i) > 47 && telNumber.charAt(i) < 58) {
                    this.telNumber = telNumber;
                }
                else {
                    throw new SmartphoneException("Le numéro de téléphone ne peut contenir que des chiffres", ErrorCode.BAD_PARAMETER);
                }
            }
        }
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) throws SmartphoneException {

        birthDate = birthDate.replaceAll(" ", "");

        if (birthDate.isEmpty())
            this.birthDate = birthDate;

        else {
            if (birthDate.length()!= 10) {
                throw new SmartphoneException("La date de naissance n'est pas valide\nFormat: dd/mm/yyyy", ErrorCode.BAD_PARAMETER);
            }
            for (int i = 0; i < birthDate.length(); i++) {
                if (birthDate.charAt(i) > 46 && birthDate.charAt(i) < 58)
                    this.birthDate = birthDate;

                else {
                    throw new SmartphoneException("La date ne peut contenir que des chiffres\nFormat: dd/mm/yyyy", ErrorCode.BAD_PARAMETER);
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
