package Contacts;

import javax.swing.*;
import java.awt.*;

public class InfoContact extends JPanel {

    private JPanel panelImage;
    private JPanel panelInfoContact;

    private JLabel firstName, lastName, telNumber, birthDate;
    private JLabel image;
    private ImageIcon imageContact;


    public InfoContact(Contact contact) {
        setLayout(null);

        // Panel image
        panelImage = new JPanel();
        panelImage.setLayout(new BorderLayout());
        image = new JLabel();
        image.setIcon(new ImageIcon("src/main/java/Contacts/Contact.png"));
        panelImage.setBounds(55,50,300,150);
        panelImage.add(image);
        add(panelImage);

        // Panel info Contact
        panelInfoContact = new JPanel();
        panelInfoContact.setLayout(new GridLayout(4,1));

        // Création labels
        firstName = new JLabel("Prénom: " + contact.getFirstName());
        lastName = new JLabel("Nom: " + contact.getLastName());
        telNumber = new JLabel("Numéro de téléphone: " + contact.getTelNumber());
//        SimpleDateFormat formatter = new SimpleDateFormat("\"MM/dd/yyyy\"");
//        String strDate = formatter.format(contact.getBirthDate());
        birthDate = new JLabel("Date de naissance: " + contact.getBirthDate());
        panelInfoContact.add(firstName);
        panelInfoContact.add(lastName);
        panelInfoContact.add(telNumber);
        panelInfoContact.add(birthDate);
        panelInfoContact.setBounds(40,220,300,200);
        add(panelInfoContact);



    }

}
