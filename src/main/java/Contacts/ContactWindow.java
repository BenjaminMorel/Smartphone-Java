package Contacts;

import Storable.JSONStorage;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ContactWindow extends JPanel {

    private JPanel contactsPanel;                               // Main panel pour l'application contacts
    private JScrollPane scrollPane;                             // Scrollbar
    private GridLayout gridLayout;                              // Layout pour le panel Contacts
    private JButton[] buttonsContact;                           // Tableau de boutons (chaque contact est un bouton)
    private JSONStorage jsonStorage = new JSONStorage();        // Json Storage pour sauvegarder/lire les contacts
    private Contact[] contacts;                                 // Liste des contacts


    public ContactWindow() throws IOException {
        loadContact();

        // Ajouter un contact
        System.out.println(contacts.length);


        // Settings du Jpanel contacts
        contactsPanel = new JPanel();
        add(contactsPanel);
        contactsPanel.setPreferredSize(new Dimension(400,600));
        contactsPanel.setLayout(new GridLayout(6,1));

        // Test Panels
        setBackground(Color.RED);
        contactsPanel.setBackground(Color.green);

        // Création boutons
        buttonsContact = new JButton[contacts.length];

        for(int i = 0; i < contacts.length; i++) {
            buttonsContact[i] = new JButton(contacts[i].getFirstName() + " " + contacts[i].getLastName());
            contactsPanel.add(buttonsContact[i]);
        }

//        Scrollbar
//        scrollPane = new JScrollPane(frameContacts, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        frameContacts.setLayout(null);
//        add(frameContacts);

        saveContact();

    }

    public void saveContact() {
        jsonStorage.write(new File("Data/Contacts.json"), contacts);
    }

    public void loadContact() {
        contacts = jsonStorage.read(new File("Data/Contacts.json"), contacts);
    }

    public void showContactInformation(JPanel infoContact) {

    }

}
