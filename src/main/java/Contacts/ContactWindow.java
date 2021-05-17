package Contacts;

import Storable.JSONStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ContactWindow extends JPanel {

    private JPanel contactsPanel;                               // Main panel pour l'application contacts
    private JPanel panelAdd;
    private JButton buttonAdd;
    private JScrollPane scrollPane;                             // Scrollbar
    private JButton[] buttonsContact;                           // Tableau de boutons (chaque contact est un bouton)
    private JSONStorage jsonStorage = new JSONStorage();        // Json Storage pour sauvegarder/lire les contacts
    private Contact[] contacts;                                 // Liste des contacts
    private JLabel nbContacts;
    private ContactWindow switchInfoContact;


    public ContactWindow() {
        loadContact();

        // Settings du Jpanel contacts
        contactsPanel = new JPanel();
        contactsPanel.setPreferredSize(new Dimension(400,600));
        contactsPanel.setLayout(new GridLayout(6,1));
        add(contactsPanel);

        // Test Panels
        setBackground(new Color(179, 176, 176));
        contactsPanel.setBackground(Color.green);

        // Ajouter un contact
        panelAdd = new JPanel();
        buttonAdd = new JButton("Nouveau contact");
        panelAdd.add(buttonAdd);
        buttonAdd.addActionListener(new ButtonAdd());
        contactsPanel.add(panelAdd);
        contactsPanel.setPreferredSize(new Dimension(300,600));

        // Création boutons
        buttonsContact = new JButton[contacts.length];

        for (int i = 0; i < contacts.length; i++) {
            buttonsContact[i] = new JButton(contacts[i].getFirstName() + " " + contacts[i].getLastName());
//            buttonsContact[i].addActionListener(new ButtonShowInfoContact());
            contactsPanel.add(buttonsContact[i]);
        }

        // Nb de contacts
        nbContacts = new JLabel("" + contacts.length + " contacts");
        contactsPanel.add(nbContacts, BorderLayout.SOUTH);

        //Scrollbar
        //scrollPane = new JScrollPane(contactsPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        saveContact();

    }

    public void saveContact() {
        jsonStorage.write(new File("Data/Contacts.json"), contacts);
    }

    public void loadContact() {
        contacts = jsonStorage.read(new File("Data/Contacts.json"), contacts);
    }

    // public void showContactInformation(JPanel infoContact) {}

//    class ButtonShowInfoContact implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            for (int i = 0; i < contacts.length; i++) {
//                if (e.getSource() == buttonsContact[i]) {
//                    switchInfoContact = new InfoContact();
//                    System.out.println(buttonsContact[i]);
//                }
//            }
//        }
//    }

    class ButtonAdd implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buttonAdd) {
                System.out.println("oui");
            }
        }
    }
}
