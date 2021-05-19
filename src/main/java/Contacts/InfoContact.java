package Contacts;

import javax.swing.*;

public class InfoContact extends ContactWindow {

    private JPanel panelInfoContact;
    private JLabel firstName;
    private JLabel lastName;
    private JLabel telNumber;
    private ImageIcon imageContact = new ImageIcon("src/main/java/Contacts/Contact.png");

    public InfoContact() {

        // Création label InfoContact
        panelInfoContact = new JPanel();
        add(panelInfoContact);

        // Création labels
        firstName = new JLabel();
        lastName = new JLabel();
        telNumber = new JLabel();
        panelInfoContact.add(firstName);
        panelInfoContact.add(lastName);
        panelInfoContact.add(telNumber);








    }

}
