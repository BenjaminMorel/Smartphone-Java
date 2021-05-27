package Contacts;

import Demo.Smartphone;
import Storable.JSONStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class NewContact extends JPanel {

    private JPanel mainPanel;
    private JLabel firstName, lastName, telNumber, birthDate;
    private JTextField firstNameText, lastNameText, telNumberText, birthDateText;

    private Contact[] contacts;
    private JButton buttonConfirm;
    private JButton buttonCancel;
    private JPanel panelCancel;
    private JSONStorage storable = new JSONStorage();
    private int nbContacts;

    private Smartphone switchApp;

    public NewContact(Contact[] contacts, int nbContacts) {
        // SetLayout pour le panel principal
        setLayout(null);

        // Création du panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4,2));

        // Création des labels contenants les infos du contacts
        firstName = new JLabel("Prénom:");
        mainPanel.add(firstName);
        lastName = new JLabel("Nom:");
        mainPanel.add(lastName);
        telNumber = new JLabel("Numéro de téléphone:");
        mainPanel.add(telNumber);
        birthDate = new JLabel("Date de naissance:");
        mainPanel.add(birthDate);

        // Création textField pour recevoir les infos de l'utilisateur
        firstNameText = new JTextField();
        mainPanel.add(firstNameText);
        lastNameText = new JTextField();
        mainPanel.add(lastNameText);
        telNumberText = new JTextField();
        mainPanel.add(telNumberText);
        birthDateText = new JTextField();
        mainPanel.add(birthDateText);

        // Button pour confirmer les informations entrées
        buttonConfirm = new JButton();
        buttonConfirm.addActionListener(new ConfirmInformation());
        mainPanel.add(buttonConfirm);
        this.contacts = contacts;
        this.nbContacts = nbContacts;

        // Bouton pour annuler et revenir sur la page des contacts
        buttonCancel = new JButton("Annuler");
        panelCancel = new JPanel();
        buttonCancel.addActionListener(new CancelInformation());
        panelCancel.add(buttonCancel);
        panelCancel.setBounds(20,450,300,40);
        add(panelCancel);

        // Ajout du main panel
        add(mainPanel);
        mainPanel.setBounds(50,100,300,400);

    }

    public void saveNewContact(File destination) throws Exception {

        Contact[] nouveauContacts = new Contact[nbContacts+1];
        System.arraycopy(contacts, 0, nouveauContacts, 0, nbContacts);
        Contact nouveauContact = new Contact(firstNameText.getText(), lastNameText.getText(), telNumberText.getText(), birthDateText.getText());

        storable.write(destination, nouveauContacts);
    }

    class ConfirmInformation implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buttonConfirm) {
                try {
                    saveNewContact(new File("Data/Contacts.json"));
                } catch (Exception exception) {
                    exception.printStackTrace();
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
