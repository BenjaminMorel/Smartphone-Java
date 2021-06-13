package Apps.Contacts;

import Demo.Smartphone;
import Storable.JSONStorage;
import TopBar.TopBarColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class InfoContact extends JPanel {

    protected JPanel panelInfoContact;
    protected JLabel firstName, lastName, telNumber, birthDate;

    private JPanel panelImage;
    private JLabel labelImage;

    protected JButton buttonModify, buttonDelete, buttonReturn;

    private Color black = Color.black;
    private Font font = new Font("Roboto", Font.BOLD, 11);

    private ImageIcon imageContact;

    private ArrayList<Contact> contacts;
    private Contact contact;

    private Smartphone switchApp;

    private JSONStorage jsonStorage = new JSONStorage();

    public InfoContact(Contact contact, ArrayList<Contact> contacts) {
        this.contact = contact;
        this.contacts = contacts;

        setLayout(null);
        setSmartphoneShape();                                                                                           // Ajout du contour du smartphone

        panelImage = new JPanel();
        panelImage.setLayout(new BorderLayout());
        labelImage = new JLabel();

        if (contact.getImagePath().equals("Images/ContactApp/Contact.png"))
        labelImage.setIcon(new ImageIcon(ClassLoader.getSystemResource(contact.getImagePath())));
        else
        labelImage.setIcon(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource(contact.getImagePath())).getImage().getScaledInstance(250, 150, Image.SCALE_SMOOTH)));

        panelImage.setBounds(50, 50, 230, 150);
        panelImage.add(labelImage);
        add(panelImage);

        panelInfoContact = new JPanel();
        panelInfoContact.setLayout(new GridLayout(4, 1));

        firstName = new JLabel("Prénom: " + contact.getFirstName());                                                // Création label prénom
        lastName = new JLabel("Nom: " + contact.getLastName());                                                     // Création label nom
        telNumber = new JLabel("Numéro de téléphone: " + contact.getTelNumber());                                   // Création label numéro de téléphone
        birthDate = new JLabel("Date de naissance: " + contact.getBirthDate());                                     // Création label date de naissance
        panelInfoContact.add(firstName); panelInfoContact.add(lastName);
        panelInfoContact.add(telNumber); panelInfoContact.add(birthDate);
        panelInfoContact.setBounds(40, 230, 300, 180);

        buttonReturn = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("Images/ContactApp/BackButton.png")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        buttonReturn.addActionListener(new InfoContactListener());                                                      // Ajout du bouton pour retourner sur la page des contacts
        buttonReturn.setBounds(15, -12, 80, 80);
        setButtonShape(buttonReturn);
        add(buttonReturn);

        creationButtonModify();
        creationButtonDelete();

        add(panelInfoContact);                                                                                          // Ajout du panel panelInfoContact au panel principal

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
     *
     * @param fullName
     */

    public void deleteContact(String fullName) {                                                                            // Méthode pour supprimer les contacts, elle prend en paramètre le "fullName" du contact affiché à l'écran
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getFullName() == fullName) {
                contacts.remove(i);
            }
        }
        jsonStorage.write(new File(System.getenv("HOME") + "\\Contacts.json"), contacts);                // Enregistrement des modifications dans le fichier JSON
    }

    /**
     *
     */

    class InfoContactListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == buttonReturn) {                                                                        // Listener pour retourner sur la classe ContactWindow
                switchApp = new Smartphone(new ContactWindow(), new TopBarColor(black));
            }
            if (e.getSource() == buttonModify) {                                                                        // Listener pour modifier un contact
                System.out.println("Modification du contact " + contact.getFullName());
                switchApp = new Smartphone(new Apps.Contacts.ModifyContact(contact, contacts), new TopBarColor(black));
            }
            if (e.getSource() == buttonDelete) {                                                                        // Listener pour supprimer un contact
                deleteContact(contact.getFullName());                                                                   // Appel de la méthode deleteContact
                System.out.println("Supprime le contact: " + contact.getFullName());
                switchApp = new Smartphone(new ContactWindow(), new TopBarColor(black));                                // Puis on recréé un panel et retour sur la classe ContactWindow
            }
        }
    }
}
