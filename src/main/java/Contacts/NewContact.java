package Contacts;

import Demo.Smartphone;
import Storable.JSONStorage;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class NewContact extends JPanel {

    private JPanel panelInfoContact;
    private JLabel firstName, lastName, telNumber, birthDate;
    private JTextField firstNameText, lastNameText, telNumberText, birthDateText;

    private JPanel panelImage;
    private JLabel labelImage;

    private JButton buttonConfirm;
    private JButton buttonCancel;

    private ArrayList<Contact> contacts;
    private JSONStorage storable = new JSONStorage();
    private int nbContacts;

    private Smartphone switchApp;

    public NewContact(ArrayList<Contact> contacts, int nbContacts) {
        // SetLayout pour le panel principal
        setLayout(null);
        this.contacts = contacts;

        // Ajout du contour du smartphone
        setSmartphoneShape();

        // Panel image
        panelImage = new JPanel();
        panelImage.setLayout(new BorderLayout());
        labelImage = new JLabel();
        labelImage.setIcon(new ImageIcon("src/main/java/Images/ContactApp/Contact.png"));
        panelImage.setBounds(55, 50, 300, 150);
        panelImage.add(labelImage);

        // Création du panel
        panelInfoContact = new JPanel();
        panelInfoContact.setLayout(new GridLayout(4,1));
        panelInfoContact.setBounds(40, 230, 300, 180);

        // Création des labels contenants les infos du contacts
        firstName = new JLabel("Prénom:");
        panelInfoContact.add(firstName);
        lastName = new JLabel("Nom:");
        panelInfoContact.add(lastName);
        telNumber = new JLabel("Numéro de téléphone:");
        panelInfoContact.add(telNumber);
        birthDate = new JLabel("Date de naissance:");
        panelInfoContact.add(birthDate);

        // Création textField pour recevoir les infos de l'utilisateur
        firstNameText = new JTextField();
        firstNameText.setBounds(180, 240, 100, 25);
        add(firstNameText);
        lastNameText = new JTextField();
        lastNameText.setBounds(180, 282, 100, 25);
        add(lastNameText);
        telNumberText = new JTextField();
        telNumberText.setBounds(180, 330, 100, 25);
        add(telNumberText);
        birthDateText = new JTextField();
        birthDateText.setBounds(180, 375, 100, 25);
        add(birthDateText);

        // Button pour confirmer les informations entrées
        buttonConfirm = new JButton("Enregistrer");
        buttonConfirm.addActionListener(new ConfirmInformation());
        buttonConfirm.setBounds(60, 500, 100, 25);
        buttonConfirm.setFont(new Font("Roboto", Font.BOLD, 11));
        add(buttonConfirm);
        this.contacts = contacts;
        this.nbContacts = nbContacts;

        // Bouton pour annuler et revenir sur la page des contacts
        buttonCancel = new JButton("Annuler");
        buttonCancel.addActionListener(new CancelInformation());
        buttonCancel.setBounds(180, 500, 100, 25);
        buttonCancel.setFont(new Font("Roboto", Font.BOLD, 11));
        add(buttonCancel);

        // Ajout des panels
        add(panelImage);
        add(panelInfoContact);

    }

    public void setSmartphoneShape() {
        // Ajout du contour du smartphone
        ImageIcon iconContourSmartphone = new ImageIcon("src/main/java/Images/smartphone_PNG.png");
        JLabel labelContourSmartphone = new JLabel();
        labelContourSmartphone.setIcon(iconContourSmartphone);
        labelContourSmartphone.setBounds(9, -8, 320, 600);
        add(labelContourSmartphone);
    }

    public void saveNewContact(File destination) throws Exception {

        String firstName = firstNameText.getText();
        firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();

        String lastName = lastNameText.getText();
        lastName = firstName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();

        // Créé le nouveau contact en prenant les infos des textFields
        contacts.add(new Contact(firstName, lastName, telNumberText.getText(), birthDateText.getText()));

        // Appel du jsonStorage et modifications de l'arrayList des contacts en y ajoutant le nouveau contact
        storable.write(destination, contacts);
    }

    class ConfirmInformation implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buttonConfirm) {
                try {
                    saveNewContact(new File("Data/Contacts.json"));
                } catch (Exception exception) {
                    exception.printStackTrace();
                    System.out.println("Erreur lors de la confirmation");
                }
                switchApp = new Smartphone(new ContactWindow());
            }
        }
    }

    class CancelInformation implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switchApp = new Smartphone(new ContactWindow());
        }
    }

}
