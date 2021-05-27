package Contacts;

import Demo.Smartphone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoContact extends JPanel {

    private JPanel panelImage;
    private JPanel panelInfoContact;
    private JPanel panelRetour;

    private JLabel firstName, lastName, telNumber, birthDate;
    private JLabel image;
    private ImageIcon imageContact;

    private JButton buttonRetour;
    private Smartphone switchApp;


    public InfoContact(Contact contact) {
        setLayout(null);

        // Ajout du contour du smartphone
        ImageIcon iconContourSmartphone = new ImageIcon("src/main/java/Images/smartphone_PNG.png");
        JLabel labelContourSmartphone = new JLabel();
        labelContourSmartphone.setIcon(iconContourSmartphone);
        labelContourSmartphone.setBounds(9, -8, 320, 600);
        add(labelContourSmartphone);

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
        panelInfoContact.setLayout(new GridLayout(5,1));

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

        // Ajout du bouton retour
        panelRetour = new JPanel();
        buttonRetour = new JButton("Retour");
        panelRetour.add(buttonRetour);
        buttonRetour.addActionListener(new BackContactWindow());
        panelRetour.setBounds(20,450,300,40);
        add(panelRetour);

        // Ajout du panel panelInfoContact au panel principal
        add(panelInfoContact);

    }

    class BackContactWindow implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buttonRetour) {
                switchApp = new Smartphone(new ContactWindow());
            }
        }
    }

}
