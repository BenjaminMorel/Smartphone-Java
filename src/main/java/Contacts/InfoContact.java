package Contacts;

import Demo.Smartphone;
import Storable.JSONStorage;

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

    protected JButton buttonModify;
    protected JButton buttonDelete;
    protected JButton buttonReturn;

    private ImageIcon imageContact;

    private ArrayList<Contact> contacts;
    private Contact contact;

    private Smartphone switchApp;

    private JSONStorage jsonStorage = new JSONStorage();

    public InfoContact(Contact contact, ArrayList<Contact> contacts) {
        setLayout(null);
        this.contact = contact;
        this.contacts = contacts;

        // Ajout du contour du smartphone
        setSmartphoneShape();

        // Panel image
        panelImage = new JPanel();
        panelImage.setLayout(new BorderLayout());
        labelImage = new JLabel();
        labelImage.setIcon(new ImageIcon("src/main/java/Images/ContactApp/Contact.png"));
        panelImage.setBounds(55, 50, 300, 150);
        panelImage.add(labelImage);
        add(panelImage);


        // Panel info Contact
        panelInfoContact = new JPanel();
        panelInfoContact.setLayout(new GridLayout(4, 1));

        // Création labels
        firstName = new JLabel("Prénom: " + contact.getFirstName());
        lastName = new JLabel("Nom: " + contact.getLastName());
        telNumber = new JLabel("Numéro de téléphone: " + contact.getTelNumber());
//        SimpleDateFormat formatter = new SimpleDateFormat("\"MM/dd/yyyy\"");
//        String strDate = formatter.format(contact.getBirthDate());
        birthDate = new JLabel("Date de naissance: " + contact.getBirthDate());
        panelInfoContact.add(firstName);
        panelInfoContact.add(lastName);
        panelInfoContact.add(telNumber);
        panelInfoContact.add(birthDate);
        panelInfoContact.setBounds(40, 230, 300, 180);

        // Ajout du bouton pour retourner sur la page des contacts
        buttonReturn = new JButton(new ImageIcon(new ImageIcon("src/main/java/Images/ContactApp/BackButton.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        buttonReturn.addActionListener(new BackContactWindow());
        buttonReturn.setBounds(15, -12, 80, 80);
        buttonReturn.setBorderPainted(false);
        buttonReturn.setFocusPainted(false);
        buttonReturn.setContentAreaFilled(false);
        add(buttonReturn);

        // Ajout du bouton modifier contact
        buttonModify = new JButton("Modifier");
        buttonModify.addActionListener(new ModifyContact());
        buttonModify.setBounds(60, 500, 100, 25);
        buttonModify.setFont(new Font("Roboto", Font.BOLD, 11));
        add(buttonModify);

        // Ajout du bouton supprimer contact
        buttonDelete = new JButton("Supprimer");
        buttonDelete.addActionListener(new DeleteContact());
        buttonDelete.setBounds(180, 500, 100, 25);
        buttonDelete.setFont(new Font("Roboto", Font.BOLD, 11));
        add(buttonDelete);

        // Ajout du panel panelInfoContact au panel principal
        add(panelInfoContact);

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
        button.setBorderPainted(true);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
    }

    public void deleteContact(String fullName) {
        // Méthode pour supprimer les contacts, elle prend en paramètre le "fullName" du contact affiché à l'écran
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getFullName() == fullName) {
                contacts.remove(i);
            }
        }

        // Enregistrement dans le fichier JSON
        jsonStorage.write(new File("Data/Contacts.json"), contacts);
    }

    class BackContactWindow implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buttonReturn) {
                // Si clic sur le bouton retour, on recréé un panel et retour sur la classe ContactWindow
                switchApp = new Smartphone(new ContactWindow());
            }
        }
    }

    class ModifyContact implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buttonModify) {
                System.out.println("Modification du contact " + contact.getFullName());
                switchApp = new Smartphone(new Contacts.ModifyContact(contact, contacts));
            }
        }
    }

    class DeleteContact implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buttonDelete) {
                // Si clic sur le bouton supprimer, le contact est supprimé grâce à la méthode deleteContact
                deleteContact(contact.getFullName());
                System.out.println("Supprime le contact: " + contact.getFullName());
                // Puis on recréé un panel et retour sur la classe ContactWindow
                switchApp = new Smartphone(new ContactWindow());
            }
        }
    }
}
