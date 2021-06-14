package Apps.Contacts;

import Demo.Smartphone;
import Errors.SmartphoneException;
import Storable.JSONStorage;
import TopBar.TopBarColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class InfoContact extends JPanel {

    // Variables générales
    protected JPanel panelInfoContact;
    protected JLabel firstName, lastName, telNumber, birthDate;
    protected JButton buttonModify, buttonDelete, buttonReturn;
    private final JSONStorage jsonStorage = new JSONStorage();

    // Fonts et couleurs
    private final Color black = Color.black;
    private final Font font = new Font("Roboto", Font.BOLD, 11);

    // Contacts
    private final ArrayList<Contact> contacts;
    private final Contact contact;

    /**
     * Constructeur de la classe InfoContact dans laquelle les infos du contact sont affichées
     * @param contact Contact à afficher
     * @param contacts ArrayList des contacts
     */

    public InfoContact(Contact contact, ArrayList<Contact> contacts) {
        this.contact = contact;
        this.contacts = contacts;

        setLayout(null);
        setSmartphoneShape();                                                                                           // Ajout du contour du smartphone

        creationPanelImage();                                                                                           // Création du panel de l'image
        creationMainPanel();                                                                                            // Création du panel principal

        creationButtonReturn();                                                                                         // Création du bouton retour à la liste des contacts
        creationButtonModify();                                                                                         // Création du bouton modifier le contact
        creationButtonDelete();                                                                                         // Création du bouton pour supprimer le contact

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
     * Création du panel qui contient l'image du contact
     */

    public void creationPanelImage() {
        JPanel panelImage = new JPanel();
        panelImage.setLayout(new BorderLayout());
        JLabel labelImage = new JLabel();

        if (contact.getImagePath().equals("Images/ContactApp/Contact.png"))
            labelImage.setIcon(new ImageIcon(ClassLoader.getSystemResource(contact.getImagePath())));
        else
            labelImage.setIcon(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource(contact.getImagePath())).getImage().getScaledInstance(250, 150, Image.SCALE_SMOOTH)));

        panelImage.setBounds(50, 50, 230, 150);
        panelImage.add(labelImage);
        add(panelImage);
    }

    /**
     * Création du panel principal contenant les différents labels des infos du contact
     */

    public void creationMainPanel() {
        panelInfoContact = new JPanel();
        panelInfoContact.setLayout(new GridLayout(4, 1));

        firstName = new JLabel("Prénom: " + contact.getFirstName());                                                // Création label prénom
        lastName = new JLabel("Nom: " + contact.getLastName());                                                     // Création label nom
        telNumber = new JLabel("Numéro de téléphone: " + contact.getTelNumber());                                   // Création label numéro de téléphone
        birthDate = new JLabel("Date de naissance: " + contact.getBirthDate());                                     // Création label date de naissance
        panelInfoContact.add(firstName); panelInfoContact.add(lastName);
        panelInfoContact.add(telNumber); panelInfoContact.add(birthDate);
        panelInfoContact.setBounds(40, 230, 300, 180);
        add(panelInfoContact);                                                                                          // Ajout du panel panelInfoContact au panel principal
    }

    /**
     * Création du bouton pour retourner à la page des contacts
     */

    public void creationButtonReturn() {
        buttonReturn = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("Images/ContactApp/BackButton.png")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        buttonReturn.addActionListener(new InfoContactListener());                                                      // Ajout du bouton pour retourner sur la page des contacts
        buttonReturn.setBounds(15, -12, 80, 80);
        setButtonShape(buttonReturn);
        add(buttonReturn);
    }

    /**
     * Création du bouton modifier contact
     */

    public void creationButtonModify() {
        buttonModify = new JButton("Modifier");
        buttonModify.addActionListener(new InfoContactListener());
        buttonModify.setBounds(60, 500, 100, 25);
        buttonModify.setFont(font);
        add(buttonModify);
    }

    /**
     * Création du bouton supprimer contact
     */

    public void creationButtonDelete() {
        buttonDelete = new JButton("Supprimer");
        buttonDelete.addActionListener(new InfoContactListener());
        buttonDelete.setBounds(180, 500, 100, 25);
        buttonDelete.setFont(font);
        add(buttonDelete);
    }

    /**
     * Méthode pour modifier l'apparence des boutons
     * @param button prend en paramètre
     */

    public void setButtonShape(JButton button) {
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
    }

    /**
     * Méthode pour supprimer un contact
     * @param fullName Prend le fullname (prénom + nom) du contact à supprimer
     */

    public void deleteContact(String fullName) {                                                                            // Méthode pour supprimer les contacts, elle prend en paramètre le "fullName" du contact affiché à l'écran
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getFullName() == fullName) {
                contacts.remove(i);
            }
        }
        try {
            jsonStorage.write(new File(System.getenv("HOME") + "\\Contacts.json"), contacts);                // Enregistrement des modifications dans le fichier JSON
        }catch(SmartphoneException sm){
            System.out.println(sm.getErrorMessage());
            System.out.println(sm.getErrorCode());
        }
    }

    /**
     * Classe contenant des actions listener pour :
     *  - Retourner à la liste des contacts
     *  - Modifier les données du contact
     *  - Supprimer le contact enregistré
     */

    class InfoContactListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (e.getSource() == buttonReturn) {                                                                        // Listener pour retourner sur la classe ContactWindow
                    new Smartphone(new ContactWindow(), new TopBarColor(black));
                }
                if (e.getSource() == buttonModify) {                                                                        // Listener pour modifier un contact
                    System.out.println("Modification du contact " + contact.getFullName());
                    new Smartphone(new ModifyContact(contact, contacts), new TopBarColor(black));
                }
                if (e.getSource() == buttonDelete) {                                                                        // Listener pour supprimer un contact
                    deleteContact(contact.getFullName());                                                                   // Appel de la méthode deleteContact
                    System.out.println("Supprime le contact: " + contact.getFullName());
                    new Smartphone(new ContactWindow(), new TopBarColor(black));
                }
            } catch (SmartphoneException sme) {
                System.out.println(sme.getErrorMessage());
                System.out.println(sme.getErrorCode());
            }
        }
    }
}
