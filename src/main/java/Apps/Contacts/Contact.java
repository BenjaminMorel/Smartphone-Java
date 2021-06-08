package Apps.Contacts;


import Errors.ErrorCode;
import Errors.SmartphoneException;

public class Contact {

    private String firstName, lastName, fullName, telNumber, birthDate, imagePath;

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
        if (firstName.isEmpty()) {
            System.out.println("ErrorCode.FIRSTNAME_EMPTY");
            throw new SmartphoneException("FirstName is empty", ErrorCode.FIRSTNAME_EMPTY);
        }
        else {
            this.firstName = firstName;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
