package Contacts;

public class Contact {

    private String firstName;
    private String lastName;
    private String telNumber;

    public Contact(String firstName, String lastName, String telNumber) {
        setFirstName(firstName);
        setLastName(lastName);
    }

    public Contact() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }
}
