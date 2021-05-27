package Contacts;

import Demo.Smartphone;
import Storable.JSONStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ContactWindow extends JPanel implements ScrollPaneConstants {

    private JPanel contactsPanel;                               // Panel qui contient la liste des contacts
    private JPanel panelAdd;                                    // Panel pour les fonctionnalités d'ajout de contact

    private JButton buttonAdd;                                  // Bouton d'ajout de contact
    private JButton[] buttonsContact;                           // Tableau de boutons (chaque contact est un bouton)

    private JSONStorage jsonStorage = new JSONStorage();        // Json Storage pour sauvegarder/lire les contacts
    private Contact[] contacts;                                 // Liste des contacts
    private JLabel labelNbContacts;                             // Label avec le nombre de contacts
    private int nbContacts;
    private JScrollPane scrollPane;

    ArrayList<Contact> ar = new ArrayList<Contact>();

    private Smartphone switchApp;


    public ContactWindow() {

        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // Layout null pour permettre d'ajouter des labels et panels avec setBounds
        setLayout(null);
        setBackground(new Color(179, 176, 176));

        // Ajout du contour du smartphone
        ImageIcon iconContourSmartphone = new ImageIcon("src/main/java/Images/smartphone_PNG.png");
        JLabel labelContourSmartphone = new JLabel();
        labelContourSmartphone.setIcon(iconContourSmartphone);
        labelContourSmartphone.setBounds(9, -8, 320, 600);
        add(labelContourSmartphone);

        // Recharge le tableau de contacts depuis le fichier JSON
        loadContact();

//        // Trie les contacts par ordre alphabétique
        for (int i = 0; i < contacts.length; i++) {
            ar.add(contacts[i]);
            //ar.add(new Contact(contacts[i].getFirstName(), contacts[i].getLastName(), contacts[i].getTelNumber(), contacts[i].getBirthDate()));
            System.out.println(contacts[i].getFullName());
        }
        Collections.sort(ar, new ContactNameComparator());

        // Settings du Panel pour ajouter un contact
        panelAdd = new JPanel();
        buttonAdd = new JButton("+");
        buttonAdd.addActionListener(new AddContact());
        panelAdd.add(buttonAdd);
        panelAdd.setBackground(Color.RED);
        panelAdd.setBounds(230, 0, 50, 40);
        add(panelAdd);

        // Settings du Panel qui contient les contacts
        contactsPanel = new JPanel();
        contactsPanel.setLayout(new GridLayout(contacts.length+1,1));
        contactsPanel.setPreferredSize(new Dimension(256,contacts.length*100));
        scrollPane = new JScrollPane(contactsPanel);
        scrollPane.setBounds(26, 40, 286, 580);
        scrollPane.getVerticalScrollBar().setUnitIncrement(30);                                                        // paramètre du Scroll pane et ajout de ce dernier dans notre objet allContatwindows (this)
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setOpaque(false);
        add(scrollPane);

        // Création de boutons (chaque bouton est un contact enregistré)
        buttonsContact = new JButton[contacts.length];
        for (int i = 0; i < contacts.length; i++) {
            buttonsContact[i] = new JButton(contacts[i].getFirstName() + " " + contacts[i].getLastName());
//            buttonsContact[i].setBorderPainted(false);
//            buttonsContact[i].setFocusPainted(false);
//            buttonsContact[i].setContentAreaFilled(false);


            buttonsContact[i].setFont(new Font("Roboto", Font.BOLD, 12));
            // buttonsContact[i].setPreferredSize(new Dimension(300,5));
            buttonsContact[i].addActionListener(new ShowInfoContact());
            contactsPanel.add(buttonsContact[i]);
        }

        nbContacts = contacts.length;

        // Nb de contacts
        labelNbContacts = new JLabel(contacts.length + " contacts");
        contactsPanel.add(labelNbContacts);

        saveContact();

    }


    public void saveContact() {
        jsonStorage.write(new File("Data/Contacts.json"), contacts);
    }

    public void loadContact() {
        contacts = jsonStorage.read(new File("Data/Contacts.json"), contacts);
    }

    class AddContact implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buttonAdd) {
                switchApp = new Smartphone(new NewContact(contacts, nbContacts));
            }
        }
    }

    class ShowInfoContact implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < contacts.length; i++) {
                if (e.getSource() == buttonsContact[i]) {
                    switchApp = new Smartphone(new InfoContact(contacts[i]));
                }
            }
        }
    }

    class ContactNameComparator implements Comparator<Contact> {
        // Classe implémentant Comparator pour comparer les contacts et les classer par ordre alphabétique
        public int compare(Contact contact1, Contact contact2) {
            return contact1.getFullName().toLowerCase().compareTo(contact2.getFullName().toLowerCase());
        }
    }

}
