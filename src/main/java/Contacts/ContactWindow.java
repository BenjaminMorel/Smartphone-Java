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
    private JPanel topPanel;

    private JButton buttonAdd;                                  // Bouton d'ajout de contact
    private JButton[] buttonsContact;                           // Tableau de boutons (chaque contact est un bouton)

    private JSONStorage jsonStorage = new JSONStorage();        // Json Storage pour sauvegarder/lire les contacts
    private ArrayList<Contact> contacts;                         // Liste des contacts
    private JLabel labelNbContacts;                             // Label avec le nombre de contacts
    private int nbContacts;
    private JScrollPane scrollPane;

    private Smartphone switchApp;


    public ContactWindow() {

        // Layout null pour permettre d'ajouter des labels et panels avec setBounds
        setLayout(null);

        // Ajout du contour du smartphone
        setSmartphoneShape();

        // Recharge le tableau de contacts depuis le fichier JSON
        loadContact();

        // Trier les contacts par ordre alphabétique
        contacts.sort(new ContactNameComparator());

        // Top panel qui contient le nombre de contacts, et l'option de créer un nouveau contact
        topPanel = new JPanel();
        topPanel.setBackground(new Color(30, 93, 144));
        topPanel.setBounds(0,0,310,40);

        // Nombre de contacts
        labelNbContacts = new JLabel(contacts.size() + " contacts");
        labelNbContacts.setFont(new Font("Roboto", Font.BOLD, 12));
        labelNbContacts.setForeground(Color.WHITE);
        topPanel.add(labelNbContacts);
        labelNbContacts.setBounds(0,0,100,40);

        // Bouton ajouter
        buttonAdd = new JButton(new ImageIcon(new ImageIcon("src/main/java/Images/ContactApp/60807.png").getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT)));
        buttonAdd.addActionListener(new AddContact());
        buttonAdd.setBounds(250,0,30,30);
        buttonAdd.setBorderPainted(false);
        buttonAdd.setFocusPainted(false);
        buttonAdd.setContentAreaFilled(false);
        topPanel.add(buttonAdd);
        add(topPanel);

        // Settings du Panel qui contient les contacts
        contactsPanel = new JPanel();
        contactsPanel.setLayout(new GridLayout(contacts.size(),1));
        contactsPanel.setPreferredSize(new Dimension(255,contacts.size()*45));
        scrollPane = new JScrollPane(contactsPanel);
        scrollPane.setBounds(27, 40, 285, 530);
        scrollPane.getVerticalScrollBar().setUnitIncrement(30);                                                        // paramètre du Scroll pane et ajout de ce dernier dans notre objet allContatwindows (this)
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setOpaque(false);
        add(scrollPane);

        // Création de boutons (chaque bouton est un contact enregistré)
        buttonsContact = new JButton[contacts.size()];
        for (int i = 0; i < contacts.size(); i++) {
            buttonsContact[i] = new JButton(contacts.get(i).getFirstName() + " " + contacts.get(i).getLastName());
            buttonsContact[i].setHorizontalAlignment(SwingConstants.LEFT);
            buttonsContact[i].setBorderPainted(true);
            buttonsContact[i].setFocusPainted(false);
            buttonsContact[i].setContentAreaFilled(false);
            buttonsContact[i].setFont(new Font("Roboto", Font.BOLD, 12));
            buttonsContact[i].addActionListener(new ShowInfoContact());
            contactsPanel.add(buttonsContact[i]);
        }
    }

    public void setSmartphoneShape() {
        // Ajout du contour du smartphone
        ImageIcon iconContourSmartphone = new ImageIcon("src/main/java/Images/smartphone_PNG.png");
        JLabel labelContourSmartphone = new JLabel();
        labelContourSmartphone.setIcon(iconContourSmartphone);
        labelContourSmartphone.setBounds(9, -8, 320, 600);
        add(labelContourSmartphone);
    }

    public void loadContact() {
        System.out.println("Chargement des contacts");
        contacts = jsonStorage.read(new File("Data/Contacts.json"), contacts);
    }

    class AddContact implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buttonAdd) {
                System.out.println("Ajout d'un nouveau contact");
                switchApp = new Smartphone(new NewContact(contacts, nbContacts));
            }
        }
    }

    class ShowInfoContact implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < contacts.size(); i++) {
                if (e.getSource() == buttonsContact[i]) {
                    System.out.println("Affiche le contact: " + contacts.get(i).getFullName());
                    switchApp = new Smartphone(new InfoContact(contacts.get(i), contacts));
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
