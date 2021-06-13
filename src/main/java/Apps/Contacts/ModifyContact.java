package Apps.Contacts;

import Demo.Smartphone;
import Errors.ErrorCode;
import Errors.SmartphoneException;
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

public class ModifyContact extends InfoContact {

    private JButton buttonChangeImage, buttonConfirm, buttonCancel;

    private JTextField firstNameText, lastNameText, telNumberText, birthDateText;

    private ArrayList<Contact> contacts;
    private Contact contact;
    private JSONStorage storable = new JSONStorage();
    private File jsonFile = new File(System.getenv("HOME") + "\\Contacts.json");

    private static final int dimensionTextFieldWidth = 100;
    private static final int dimensionTextFieldHeight = 25;

    private Font font = new Font("Roboto", Font.BOLD, 11);
    private Color black = Color.black;

    private Smartphone switchApp;

    /**
     *
     * @param contact
     * @param contacts
     */

    public ModifyContact(Contact contact, ArrayList<Contact> contacts) {
        super(contact, contacts);
        this.contacts = contacts;
        this.contact = contact;

        setLayout(null);
        setSmartphoneShape();                                                                                           // Ajout du contour du smartphone
        panelInfoContact.setOpaque(false);                                                                              // Modification du panel infoContact
        buttonModify.setVisible(false);                                                                                 // Rendre invisible les autres boutons
        buttonDelete.setVisible(false);

        buttonChangeImage = new JButton("Changer image");                                                           // Changer image de contact
        buttonChangeImage.addActionListener(new ModifyContactListener());
        buttonChangeImage.setBounds(105, 205, 130, dimensionTextFieldHeight);
        buttonChangeImage.setFont(font);
        setButtonShape(buttonChangeImage);
        buttonChangeImage.setBorderPainted(true);
        add(buttonChangeImage);

        firstName.setText("Prénom:");                                                                                   // Modification des labels
        lastName.setText("Nom:");
        telNumber.setText("Numéro de téléphone:");
        birthDate.setText("Date de naissance:");

        firstNameText = new JTextField(contact.getFirstName());                                                         // Création textField pour recevoir les infos de l'utilisateur
        firstNameText.setBounds(180, 240, dimensionTextFieldWidth, dimensionTextFieldHeight);
        add(firstNameText);
        lastNameText = new JTextField(contact.getLastName());
        lastNameText.setBounds(180, 282, dimensionTextFieldWidth, dimensionTextFieldHeight);
        add(lastNameText);
        telNumberText = new JTextField(contact.getTelNumber());
        telNumberText.setBounds(180, 330, dimensionTextFieldWidth, dimensionTextFieldHeight);
        add(telNumberText);
        birthDateText = new JTextField(contact.getBirthDate());
        birthDateText.setBounds(180, 375, dimensionTextFieldWidth, dimensionTextFieldHeight);
        add(birthDateText);

        creationButtonConfirm();
        creationButtonCancel();

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
     * Button pour confirmer les informations entrées
     */

    public void creationButtonConfirm() {
        buttonConfirm = new JButton("Enregistrer");
        buttonConfirm.addActionListener(new ModifyContactListener());
        buttonConfirm.setBounds(60, 500, dimensionTextFieldWidth, dimensionTextFieldHeight);
        buttonConfirm.setFont(font);
        add(buttonConfirm);
    }

    /**
     * Bouton pour annuler et revenir sur la page des contacts
     */

    public void creationButtonCancel() {
        buttonCancel = new JButton("Annuler");
        buttonCancel.addActionListener(new ModifyContactListener());
        buttonCancel.setBounds(180, 500, dimensionTextFieldWidth, dimensionTextFieldHeight);
        buttonCancel.setFont(font);
        add(buttonCancel);
    }

    /**
     *
     * @param destination
     * @throws SmartphoneException
     */

    public void saveInJsonStorage(File destination) throws SmartphoneException {

        try {
            contact.setFirstName(firstNameText.getText());
            contact.setLastName(lastNameText.getText());
            contact.setTelNumber(telNumberText.getText());
            contact.setBirthDate(birthDateText.getText());

            storable.write(destination, contacts);
        } catch (SmartphoneException e) {
            throw new SmartphoneException("Erreur à l'enregistrement des données dans le fichier JSON", ErrorCode.SAVE_ERROR);
        }
    }

    /**
     * Classe contenant des actions listener pour :
     *  - confirmer les modifications des infos du contact
     *  - retourner au panel InfoContact
     *  - modifier l'image du contact
     */

    class ModifyContactListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == buttonConfirm) {
                try {
                    saveInJsonStorage(jsonFile);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                switchApp = new Smartphone(new InfoContact(contact, contacts), new TopBarColor(black));
            }
            if (e.getSource() == buttonCancel) {
                contact.setImagePath(ClassLoader.getSystemResource("Images/ContactApp/Contact.png").getPath());
                switchApp = new Smartphone(new InfoContact(contact, contacts), new TopBarColor(black));
            }
            if (e.getSource() == buttonChangeImage) {
                switchApp = new Smartphone(new ModifyContactImage(contact, contacts), new TopBarColor(black));
                System.out.println("Changement de l'image du contact: " + contact.getFullName());
            }
        }
    }
}
