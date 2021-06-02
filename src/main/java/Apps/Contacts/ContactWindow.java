package Apps.Contacts;

import Demo.Smartphone;
import TopBar.TopBarContactApp;
import Storable.JSONStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;

public class ContactWindow extends JPanel implements ScrollPaneConstants {

    private JPanel topPanel;                                                                                            // Panel qui contient le nombre de contacts + bouton ajouter
    private JButton buttonAdd;                                                                                          // Bouton d'ajout de contact
    private JLabel labelNbContacts;                                                                                     // Label avec le nombre de contacts

    private JPanel contactsPanel;                                                                                       // Panel qui contient la liste des contacts
    private JScrollPane scrollPane;                                                                                     // Scrollbar qui viendra s'ajouter au panel contactsPanel
    private JButton[] buttonsContact;                                                                                   // Tableau de boutons (chaque contact est un bouton)
    private ArrayList<Contact> contacts;                                                                                // ArrayList de contacts

    private JSONStorage jsonStorage = new JSONStorage();                                                                // Json Storage pour sauvegarder/lire les contacts

    private Smartphone switchApp;                                                                                       // Instance de smartphone qui permettra de recréer un panel central

    /**
     * Classe principale de l'application contact, où on trouve la liste des contacts
     */

    public ContactWindow() {

        // Layout null pour permettre de placer les panels avec .setBounds
        setLayout(null);

        // Ajout du contour du smartphone
        setSmartphoneShape();

        // Recharge l'arrayList de contacts depuis le fichier JSON
        loadContact();

        // Trier les contacts par ordre alphabétique
        contacts.sort(new ContactNameComparator());

        // Top panel qui contient le nombre de contacts, et l'option de créer un nouveau contact
        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.setBackground(new Color(30, 93, 144));
        topPanel.setBounds(0,0,310,40);

        // Label qui contient le nombre de contacts
        labelNbContacts = new JLabel(contacts.size() + " contacts");
        // Modifier la police d'écriture et la taille
        labelNbContacts.setFont(new Font("Roboto", Font.BOLD, 12));
        // Modifier la couleur du texte
        labelNbContacts.setForeground(Color.WHITE);
        // Ajout du label au topPanel
        topPanel.add(labelNbContacts);

        // Bouton d'ajout de contact
        buttonAdd = new JButton(new ImageIcon(new ImageIcon("src/main/java/Images/ContactApp/60807.png").getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT)));
        // Ajout de l'action Listener de la classe AddContact
        buttonAdd.addActionListener(new ContactActionListener());
        // Emplacement du boutton dans le topPanel
        buttonAdd.setBounds(250,0,30,30);
        // Modifie l'apparence des boutons
        setButtonShape(buttonAdd);
        // Ajout du bouton au topPanel
        topPanel.add(buttonAdd);
        add(topPanel);

        // Settings du Panel qui contient les contacts
        contactsPanel = new JPanel();
        contactsPanel.setLayout(new GridLayout(contacts.size(),1));
        contactsPanel.setPreferredSize(new Dimension(255,contacts.size()* 45));
        scrollPane = new JScrollPane(contactsPanel);
        scrollPane.setBounds(27, 40, 285, 530);
        scrollPane.getVerticalScrollBar().setUnitIncrement(30);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setOpaque(false);
        add(scrollPane);

        // Création de boutons (chaque bouton est un contact enregistré)
        buttonsContact = new JButton[contacts.size()];
        for (int i = 0; i < contacts.size(); i++) {
            buttonsContact[i] = new JButton(contacts.get(i).getFirstName() + " " + contacts.get(i).getLastName());
            buttonsContact[i].setHorizontalAlignment(SwingConstants.LEFT);
            setButtonShape(buttonsContact[i]);
            buttonsContact[i].setBorderPainted(true);
            buttonsContact[i].setFont(new Font("Roboto", Font.BOLD, 12));
            buttonsContact[i].addActionListener(new ContactActionListener());
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

    public void setButtonShape(JButton button) {
        // Méthode pour modifier l'apparence des boutons (évite d'écrire ces 3 lignes plusieurs fois)
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
    }

    public void loadContact() {
        System.out.println("Chargement des contacts");
        contacts = jsonStorage.read(new File("Data/Contacts.json"), contacts);
    }

    class ContactNameComparator implements Comparator<Contact> {
        // Classe implémentant Comparator pour comparer les contacts et les classer par ordre alphabétique
        public int compare(Contact contact1, Contact contact2) {
            return contact1.getFullName().toLowerCase().compareTo(contact2.getFullName().toLowerCase());
        }
    }

    class ContactActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            // Listener pour l'ajout d'un nouveau contact
            if (e.getSource() == buttonAdd) {
                System.out.println("Ajout d'un nouveau contact");
                switchApp = new Smartphone(new NewContact(contacts), new TopBarContactApp());
            }

            // Listener pour afficher un contact déjà existant
            for (int i = 0; i < contacts.size(); i++) {
                if (e.getSource() == buttonsContact[i]) {
                    System.out.println("Affiche le contact: " + contacts.get(i).getFullName());
                    switchApp = new Smartphone(new InfoContact(contacts.get(i), contacts), new TopBarContactApp());
                }
            }
        }
    }
}
