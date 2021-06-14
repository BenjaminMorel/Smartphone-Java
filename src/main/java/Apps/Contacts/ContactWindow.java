package Apps.Contacts;

import Demo.Smartphone;
import Errors.SmartphoneException;
import Storable.JSONStorage;
import TopBar.TopBarColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;

public class ContactWindow extends JPanel implements ScrollPaneConstants {

    // Variables générales
    private JButton buttonAdd;                                                                                          // Bouton d'ajout de contact
    private JPanel contactsPanel;                                                                                       // Panel qui contient la liste des contacts
    private JButton[] buttonsContact;                                                                                   // Tableau de boutons (chaque contact est un bouton)
    private ArrayList<Contact> contacts;                                                                                // ArrayList de contacts
    private final JSONStorage jsonStorage = new JSONStorage();                                                                // Json Storage pour sauvegarder/lire les contacts

    // Fonts et couleurs
    private final Color black = Color.BLACK;
    private final Color colorPanel = new Color(30, 93, 144);
    private final Font font = new Font("Roboto", Font.BOLD, 12);


    /**
     * Constructeur de la classe ContactWindow, qui est la classe principale
     * de l'application contact où se trouve la liste des contacts
     */

    public ContactWindow() {
        setLayout(null);
        setSmartphoneShape();                                                                                           // Ajout du contour du smartphone
        loadContact();                                                                                                  // Recharge l'arrayList de contacts depuis le fichier JSON
        contacts.sort(new ContactNameComparator());                                                                     // Trier les contacts par ordre alphabétique
        creationTopPanel();                                                                                             // Création du panel du sommet
        creationMainPanel();                                                                                            // Création du panel principal de l'application
        creationButtonsContact();                                                                                       // Création des boutons (Chaque contact est un bouton)
    }

    /**
     * Ajout du contour du smartphone
     */

    public void setSmartphoneShape() {
        ImageIcon iconContourSmartphone = new ImageIcon(ClassLoader.getSystemResource("Images/smartphone_PNG.png"));
        JLabel labelContourSmartphone = new JLabel();
        labelContourSmartphone.setIcon(iconContourSmartphone);
        labelContourSmartphone.setBounds(9, -8, 320, 600);
        add(labelContourSmartphone);
    }

    /**
     * Méthode pour recharger l'ArrayList des contacts
     */

    public void loadContact() {
        System.out.println("Chargement des contacts");
        try {
            contacts = jsonStorage.read(new File(System.getenv("HOME") + "\\Contacts.json"), contacts);
        } catch(SmartphoneException sm){
            System.out.println(sm.getErrorMessage());
            System.out.println(sm.getErrorCode());
        }
    }

    /**
     * Création du panel du sommet, contenant :
     *  - Un label avec le nombre de contacts enregistrés
     *  - Un bouton permettant d'ajouter un nouveau contact
     */

    public void creationTopPanel() {
        JPanel topPanel = new JPanel();                                                                                 // Top panel qui contient le nombre de contacts, et l'option de créer un nouveau contact
        topPanel.setLayout(new FlowLayout());
        topPanel.setBackground(colorPanel);
        topPanel.setBounds(0,0,310,40);

        JLabel labelNbContacts = new JLabel(contacts.size() + " contacts");                                         // Label qui contient le nombre de contacts
        labelNbContacts.setFont(font);                                                                                  // Modifier la police d'écriture et la taille
        labelNbContacts.setForeground(Color.WHITE);                                                                     // Modifier la couleur du texte
        topPanel.add(labelNbContacts);                                                                                  // Ajout du label au topPanel
        buttonAdd = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("Images/ContactApp/60807.png")).getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT)));
        buttonAdd.addActionListener(new ContactActionListener());                                                       // Ajout de l'action Listener de la classe AddContact
        buttonAdd.setBounds(250,0,30,30);                                                             // Emplacement du boutton dans le topPanel
        setButtonShape(buttonAdd);                                                                                      // Modifie l'apparence des boutons
        topPanel.add(buttonAdd);                                                                                        // Ajout du bouton au topPanel
        add(topPanel);
    }

    /**
     * Création du panel principal
     */

    public void creationMainPanel() {
        contactsPanel = new JPanel();                                                                                   // Settings du Panel qui contient les contacts
        if (contacts.size() <= 11) {
            contactsPanel.setLayout(new GridLayout(11,1));
        }
        else {
            contactsPanel.setLayout(new GridLayout(contacts.size(),1));
        }
        contactsPanel.setPreferredSize(new Dimension(255,contacts.size()* 45));

        JScrollPane scrollPane = new JScrollPane(contactsPanel);                                                        // Scrollbar qui viendra s'ajouter au panel contactsPanel
        scrollPane.setBounds(27, 40, 285, 530);
        scrollPane.getVerticalScrollBar().setUnitIncrement(30);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setOpaque(false);
        add(scrollPane);
    }


    /**
     * Création de boutons (chaque bouton est un contact enregistré)
     */

    public void creationButtonsContact() {
        buttonsContact = new JButton[contacts.size()];
        for (int i = 0; i < contacts.size(); i++) {
            buttonsContact[i] = new JButton(contacts.get(i).getFirstName() + " " + contacts.get(i).getLastName());
            buttonsContact[i].setHorizontalAlignment(SwingConstants.LEFT);
            setButtonShape(buttonsContact[i]);
            buttonsContact[i].setBorderPainted(true);
            buttonsContact[i].setFont(font);
            buttonsContact[i].addActionListener(new ContactActionListener());
            contactsPanel.add(buttonsContact[i]);
        }
    }

    /**
     * Méthode pour modifier l'apparence des boutons
     * @param button prend en paramètre le bouton à modifier
     */

    public void setButtonShape(JButton button) {
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
    }

    /**
     * Classe implémentant Comparator pour comparer les contacts et les classer par ordre alphabétique
     */

    static class ContactNameComparator implements Comparator<Contact> {
        public int compare(Contact contact1, Contact contact2) {
            return contact1.getFullName().toLowerCase().compareTo(contact2.getFullName().toLowerCase());
        }
    }

    /**
     * Classe contenant des actions listener pour ajouter un nouveau contact, ou en afficher un
     */

    class ContactActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (e.getSource() == buttonAdd) {                                                                       // Listener pour l'ajout d'un nouveau contact
                    System.out.println("Ajout d'un nouveau contact");
                    new Smartphone(new NewContact(contacts), new TopBarColor(black));
                }
                for (int i = 0; i < contacts.size(); i++) {                                                             // Listener pour afficher un contact déjà existant
                    if (e.getSource() == buttonsContact[i]) {
                        System.out.println("Affiche le contact: " + contacts.get(i).getFullName());
                        new Smartphone(new InfoContact(contacts.get(i), contacts), new TopBarColor(black));
                    }
                }
            } catch (SmartphoneException sme) {
                System.out.println(sme.getErrorMessage());
                System.out.println(sme.getErrorCode());
            }
        }
    }
}
