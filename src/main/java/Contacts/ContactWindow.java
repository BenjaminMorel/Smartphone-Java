package Contacts;

import SmartphoneShape.Smartphone;
import Storable.JSONStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ContactWindow extends JPanel implements ScrollPaneConstants {

    private JPanel contactsPanel;                               // Panel qui contient la liste des contacts
    private JPanel panelAdd;                                    // Panel pour les fonctionnalités d'ajout de contact

    private JButton buttonAdd;                                  // Bouton d'ajout de contact
    private JButton[] buttonsContact;                           // Tableau de boutons (chaque contact est un bouton)

    private JSONStorage jsonStorage = new JSONStorage();        // Json Storage pour sauvegarder/lire les contacts
    private Contact[] contacts;                                 // Liste des contacts
    private JLabel nbContacts;                                  // Label avec le nombre de contacts

    private Smartphone switchInfoContact;


    public ContactWindow() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Permet d'ajouter des labels et panels avec setBounds
        setBackground(new Color(179, 176, 176));

        loadContact();                                          // Recharge le tableau de contacts depuis le fichier JSON

        // Settings du Panel pour ajouter un contact
        panelAdd = new JPanel();
        buttonAdd = new JButton("+");
        panelAdd.add(buttonAdd);
        add(panelAdd);

        // Settings du Panel qui contient les contacts
        contactsPanel = new JPanel();
        contactsPanel.setLayout(new GridLayout(0,1));
        contactsPanel.setPreferredSize(new Dimension(300,contacts.length*100));
        JScrollPane scrollPane = new JScrollPane(contactsPanel);
        add(scrollPane);

        // Création boutons contacts
        buttonsContact = new JButton[contacts.length];
        for (int i = 0; i < contacts.length; i++) {
            buttonsContact[i] = new JButton(contacts[i].getFirstName() + " " + contacts[i].getLastName());
            buttonsContact[i].setFont(new Font("Roboto", Font.BOLD, 12));
            buttonsContact[i].setPreferredSize(new Dimension(300,10));
            contactsPanel.add(buttonsContact[i]);
        }

        // Nb de contacts
        nbContacts = new JLabel("             " + contacts.length + " contacts");
        contactsPanel.add(nbContacts);

        saveContact();

    }



    public void saveContact() {
        jsonStorage.write(new File("Data/Contacts.json"), contacts);
    }

    public void loadContact() {
        contacts = jsonStorage.read(new File("Data/Contacts.json"), contacts);
    }

    public void showContactInformation(JPanel infoContact) {}

    class ButtonShowInfoContact implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < contacts.length; i++) {
                if (e.getSource() == buttonsContact[i]) {
                    switchInfoContact = new Smartphone(new InfoContact());
                    System.out.println(buttonsContact[i]);
                }
            }
        }
    }
}
