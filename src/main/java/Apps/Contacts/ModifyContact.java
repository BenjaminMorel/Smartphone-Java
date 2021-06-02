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

/**
 * Classe contenant un panel où le contact passé en paramètre pourra être modifié
 */

public class ModifyContact extends InfoContact implements ContactInterace {

    private JButton buttonChangeImage, buttonConfirm, buttonCancel;

    private JTextField firstNameText, lastNameText, telNumberText, birthDateText;

    private ArrayList<Contact> contacts;
    private Contact contact;
    private JSONStorage storable = new JSONStorage();
    private File jsonFile = new File("Data/Contacts.json");

    private Smartphone switchApp;

    public ModifyContact(Contact contact, ArrayList<Contact> contacts) {
        super(contact, contacts);
        this.contacts = contacts;
        this.contact = contact;

        setLayout(null);

        // Ajout du contour du smartphone
        setSmartphoneShape();

        // Modification du panel infoContact
        panelInfoContact.setOpaque(false);

        // Changer image de contact
        buttonChangeImage = new JButton("Changer image");
        buttonChangeImage.addActionListener(new ModifyContactListener());
        buttonChangeImage.setBounds(105, 205, 130, 25);
        buttonChangeImage.setFont(new Font("Roboto", Font.BOLD, 11));
        setButtonShape(buttonChangeImage);
        buttonChangeImage.setBorderPainted(true);
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
        buttonConfirm.addActionListener(new ModifyContactListener());
        buttonConfirm.setBounds(60, 500, 100, 25);
        buttonConfirm.setFont(new Font("Roboto", Font.BOLD, 11));
        add(buttonConfirm);

        // Bouton pour annuler et revenir sur la page des contacts
        buttonCancel = new JButton("Annuler");
        buttonCancel.addActionListener(new ModifyContactListener());
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

    @Override
    public void saveInJsonStorage(File destination) throws Exception {

        contact.setFirstName(firstNameText.getText());
        contact.setLastName(lastNameText.getText());
        contact.setTelNumber(telNumberText.getText());
        contact.setBirthDate(birthDateText.getText());

        storable.write(destination, contacts);

    }

    class ModifyContactListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            // Listener pour confirmer les modifications
            if (e.getSource() == buttonConfirm) {
                try {
                    saveInJsonStorage(jsonFile);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    System.out.println("Erreur lors de la confirmation");
                }
                switchApp = new Smartphone(new InfoContact(contact, contacts), new TopBarColor(new Color(0,0,0)));
            }

            // Listener pour retourner au panel InfoContact
            if (e.getSource() == buttonCancel) {
                contact.setImagePath("src/main/java/Images/ContactApp/Contact.png");
                switchApp = new Smartphone(new InfoContact(contact, contacts), new TopBarColor(new Color(0,0,0)));
            }

            // Listener pour modifier l'image du contact
            if (e.getSource() == buttonChangeImage) {
                switchApp = new Smartphone(new ModifyContactImage(contact, contacts), new TopBarColor(new Color(0,0,0)));
                System.out.println("Changement de l'image du contact: " + contact.getFullName());
            }
        }
    }
}
