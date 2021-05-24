package Contacts;

import Demo.Smartphone;
import Storable.JSONStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class NewContact extends JPanel implements ActionListener {

    private JPanel mainPanel;
    private JLabel firstName, lastName, telNumber, birthDate;
    private JTextField firstNameText, lastNameText, telNumberText, birthDateText;

    private Contact[] contacts;
    private JButton buttonConfirm;
    private JSONStorage storable = new JSONStorage();
    private int nbContacts;

    private Smartphone retourContactWindow;

    public NewContact(Contact[] contacts, int nbContacts) {
        setLayout(null);
        this.nbContacts = nbContacts;

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4,2));

        // Création labels
        firstName = new JLabel("Prénom:");
        lastName = new JLabel("Nom:");
        telNumber = new JLabel("Numéro de téléphone:");
        birthDate = new JLabel("Date de naissance:");

        // Création textField
        firstNameText = new JTextField();
        lastNameText = new JTextField();
        telNumberText = new JTextField();
        birthDateText = new JTextField();

        mainPanel.add(firstName); mainPanel.add(firstNameText);
        mainPanel.add(lastName); mainPanel.add(lastNameText);
        mainPanel.add(telNumber); mainPanel.add(telNumberText);
        mainPanel.add(birthDate); mainPanel.add(birthDateText);

        // Confirmation des infos
        buttonConfirm = new JButton();
        mainPanel.add(buttonConfirm);
        this.contacts = contacts;
        buttonConfirm.addActionListener(this);

        // Ajout du main panel
        add(mainPanel);
        mainPanel.setBounds(50,100,300,400);



    }

    public void saveNewContact(File destination) throws Exception {

        Contact[] nouveauContacts = new Contact[nbContacts];
        System.arraycopy(contacts, 0, nouveauContacts, 0, nbContacts);

        storable.write(destination, contacts);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonConfirm) {




            try {
                saveNewContact(new File("Data/Contacts.json"));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            retourContactWindow = new Smartphone(new ContactWindow());
        }
    }
}
