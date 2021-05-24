package Contacts;

import Demo.Smartphone;
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
    private JLabel labelNbContacts;                                  // Label avec le nombre de contacts
    private int nbContacts;
    private JScrollPane scrollPane ;

    private Smartphone switchApp;


    public ContactWindow() {

        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Permet d'ajouter des labels et panels avec setBounds
        setLayout(null);
        setBackground(new Color(179, 176, 176));

        // ajout du contour du smartphone
        ImageIcon iconContourSmartphone = new ImageIcon("src/main/java/Images/smartphone_PNG.png") ;
        JLabel labelContourSmartphone = new JLabel() ;
        labelContourSmartphone.setIcon(iconContourSmartphone);
        labelContourSmartphone.setBounds(9, -8, 320, 600);
        add(labelContourSmartphone) ;

        loadContact();                                          // Recharge le tableau de contacts depuis le fichier JSON

        // Settings du Panel pour ajouter un contact
        panelAdd = new JPanel();
        buttonAdd = new JButton("+");
        buttonAdd.addActionListener(new ButtonAddContact());
        panelAdd.add(buttonAdd);
        panelAdd.setBounds(150, 0, 50, 40);
        add(panelAdd);

        // Settings du Panel qui contient les contacts
        contactsPanel = new JPanel();
        contactsPanel.setLayout(new GridLayout(0,1));
        contactsPanel.setPreferredSize(new Dimension(300,contacts.length*100));
        scrollPane = new JScrollPane(contactsPanel);
        scrollPane.setBounds(26, 40, 286, 580);
        add(scrollPane);

        // Création boutons contacts
        buttonsContact = new JButton[contacts.length];
        for (int i = 0; i < contacts.length; i++) {
            buttonsContact[i] = new JButton(contacts[i].getFirstName() + " " + contacts[i].getLastName());
            buttonsContact[i].setFont(new Font("Roboto", Font.BOLD, 12));
            buttonsContact[i].setPreferredSize(new Dimension(300,10));
            buttonsContact[i].addActionListener(new ButtonShowInfoContact());
            contactsPanel.add(buttonsContact[i]);
        }

        nbContacts = contacts.length;

        // Nb de contacts
        labelNbContacts = new JLabel("            " + contacts.length + " contacts");
        contactsPanel.add(labelNbContacts, BorderLayout.CENTER);

        saveContact();

    }


    public void saveContact() {
        jsonStorage.write(new File("Data/Contacts.json"), contacts);
    }

    public void loadContact() {
        contacts = jsonStorage.read(new File("Data/Contacts.json"), contacts);
    }

    public void showContactInformation(JPanel infoContact) {}

    class ButtonAddContact implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buttonAdd) {
                switchApp = new Smartphone(new NewContact(contacts, nbContacts));
            }
        }
    }

    class ButtonShowInfoContact implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < contacts.length; i++) {
                if (e.getSource() == buttonsContact[i]) {
                    switchApp = new Smartphone(new InfoContact(contacts[i]));
                }
            }
        }
    }
}
