package Contacts;

import Demo.Smartphone;
import Gallery.GalleryWindow;
import Storable.JSONStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class ModifyContact extends InfoContact {

    private JButton buttonChangeImage;
    private JButton buttonConfirm;
    private JButton buttonCancel;

    private JTextField firstNameText, lastNameText, telNumberText, birthDateText;

    private ArrayList<Contact> contacts;
    private Contact contact;
    private JSONStorage storable = new JSONStorage();

    private Smartphone switchApp;

    public ModifyContact(Contact contact, ArrayList<Contact> contacts) {
        super(contact, contacts);
        setLayout(null);
        this.contacts = contacts;
        this.contact = contact;

        // Ajout du contour du smartphone
        setSmartphoneShape();

        // Opaque = non
        panelInfoContact.setOpaque(false);

        // Changer image de contact
        buttonChangeImage = new JButton("Changer image");
        buttonChangeImage.addActionListener(new ChangeContactImage());
        buttonChangeImage.setBounds(105, 205, 130, 25);
        buttonChangeImage.setFont(new Font("Roboto", Font.BOLD, 11));
        setButtonShape(buttonChangeImage);
        add(buttonChangeImage);

        // Modification des labels
        firstName.setText("Prénom:");
        lastName.setText("Nom:");
        telNumber.setText("Numéro de téléphone:");
        birthDate.setText("Date de naissance:");

        // Création textField pour recevoir les infos de l'utilisateur
        firstNameText = new JTextField(contact.getFirstName());
        firstNameText.setBounds(180, 240, 100, 25);
        add(firstNameText);
        lastNameText = new JTextField(contact.getLastName());
        lastNameText.setBounds(180, 282, 100, 25);
        add(lastNameText);
        telNumberText = new JTextField(contact.getTelNumber());
        telNumberText.setBounds(180, 330, 100, 25);
        add(telNumberText);
        birthDateText = new JTextField(contact.getBirthDate());
        birthDateText.setBounds(180, 375, 100, 25);
        add(birthDateText);

        // Ajout bouton enregistrer modifications et cacher les autres bouttons
        buttonModify.setVisible(false);
        buttonDelete.setVisible(false);

        // Button pour confirmer les informations entrées
        buttonConfirm = new JButton("Enregistrer");
        buttonConfirm.addActionListener(new ConfirmInformation());
        buttonConfirm.setBounds(60, 500, 100, 25);
        buttonConfirm.setFont(new Font("Roboto", Font.BOLD, 11));
        add(buttonConfirm);

        // Bouton pour annuler et revenir sur la page des contacts
        buttonCancel = new JButton("Annuler");
        buttonCancel.addActionListener(new CancelInformation());
        buttonCancel.setBounds(180, 500, 100, 25);
        buttonCancel.setFont(new Font("Roboto", Font.BOLD, 11));
        add(buttonCancel);

    }

    public void setSmartphoneShape() {
        // Ajout du contour du smartphone
        ImageIcon iconContourSmartphone = new ImageIcon("src/main/java/Images/smartphone_PNG.png");
        JLabel labelContourSmartphone = new JLabel();
        labelContourSmartphone.setIcon(iconContourSmartphone);
        labelContourSmartphone.setBounds(9, -8, 320, 600);
        add(labelContourSmartphone);
    }

    public void saveModifications(File destination) throws Exception {

        contact.setFirstName(firstNameText.getText());
        contact.setLastName(lastNameText.getText());
        contact.setTelNumber(telNumberText.getText());
        contact.setBirthDate(birthDateText.getText());

        storable.write(destination, contacts);
    }

    class ConfirmInformation implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buttonConfirm) {
                try {
                    saveModifications(new File("Data/Contacts.json"));
                } catch (Exception exception) {
                    exception.printStackTrace();
                    System.out.println("Erreur lors de la confirmation");
                }
                switchApp = new Smartphone(new InfoContact(contact, contacts));
            }
        }
    }

    class CancelInformation implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switchApp = new Smartphone(new InfoContact(contact, contacts));
        }
    }

    class ChangeContactImage implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switchApp = new Smartphone(new ModifyContactImage(contact));
            System.out.println("Changement de l'image du contact: " + contact.getFullName());
        }
    }
}
