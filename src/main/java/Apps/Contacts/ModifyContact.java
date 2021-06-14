package Apps.Contacts;

import Demo.Smartphone;
import Errors.ErrorCode;
import Errors.ErrorPanel;
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

    // Variables générales
    private JButton buttonChangeImage, buttonConfirm, buttonCancel;
    private JTextField firstNameText, lastNameText, telNumberText, birthDateText;
    private final ArrayList<Contact> contacts;
    private final Contact contact;
    private final JSONStorage storable = new JSONStorage();
    private final File jsonFile = new File(System.getenv("HOME") + "\\Contacts.json");

    // Dimensions
    private static final int dimensionTextFieldWidth = 100;
    private static final int dimensionTextFieldHeight = 25;

    // Fonts et Couleurs
    private final Font font = new Font("Roboto", Font.BOLD, 11);
    private final Color black = Color.black;

    /**
     *
     * @param contact Contact à modifier
     * @param contacts ArrayList des contacts
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

        creationJTextFields();
        creationButtonChangeImage();
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
     * Création des différents JTextFields qui vont contenir les infos du contact (prénom, nom, num téléphone et date de naissance)
     */

    public void creationJTextFields() {
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
    }

    /**
     * Création du bouton pour pouvoir changer l'image du contact
     */

    public void creationButtonChangeImage() {
        buttonChangeImage = new JButton("Changer image");                                                           // Changer image de contact
        buttonChangeImage.addActionListener(new ModifyContactListener());
        buttonChangeImage.setBounds(105, 205, 130, dimensionTextFieldHeight);
        buttonChangeImage.setFont(font);
        setButtonShape(buttonChangeImage);
        buttonChangeImage.setBorderPainted(true);
        add(buttonChangeImage);
    }


    /**
     * Création du button pour confirmer les informations entrées
     */

    public void creationButtonConfirm() {
        buttonConfirm = new JButton("Enregistrer");
        buttonConfirm.addActionListener(new ModifyContactListener());
        buttonConfirm.setBounds(60, 500, dimensionTextFieldWidth, dimensionTextFieldHeight);
        buttonConfirm.setFont(font);
        add(buttonConfirm);
    }

    /**
     * Création du bouton pour annuler et revenir sur la page des contacts
     */

    public void creationButtonCancel() {
        buttonCancel = new JButton("Annuler");
        buttonCancel.addActionListener(new ModifyContactListener());
        buttonCancel.setBounds(180, 500, dimensionTextFieldWidth, dimensionTextFieldHeight);
        buttonCancel.setFont(font);
        add(buttonCancel);
    }

    /**
     * Méthode pour sauver les modifications dans le fichier JSON
     * @param destination Spécifier le fichier de destination
     * @throws SmartphoneException Ajout des exceptions à la méthode
     */

    public void saveInJsonStorage(File destination) throws SmartphoneException {
        try {
            contact.setFirstName(firstNameText.getText());
            contact.setLastName(lastNameText.getText());
            contact.setTelNumber(telNumberText.getText());
            contact.setBirthDate(birthDateText.getText());
        } catch (SmartphoneException e) {
            new ErrorPanel(e.getErrorMessage());
            throw new SmartphoneException(e.getErrorMessage(), ErrorCode.BAD_PARAMETER);
        }

        try {
            storable.write(destination, contacts);
        } catch (SmartphoneException e) {
            new ErrorPanel(e.getErrorMessage());
            throw new SmartphoneException(e.getErrorMessage(), ErrorCode.SAVE_ERROR);
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
            try {
                if (e.getSource() == buttonConfirm) {
                    saveInJsonStorage(jsonFile);
                    new Smartphone(new InfoContact(contact, contacts), new TopBarColor(black));
                }
                if (e.getSource() == buttonCancel) {
                    if (contact.getImagePath().equals("Images/ContactApp/Contact.png"))
                        contact.setImagePath("Images/ContactApp/Contact.png");
                    else
                        contact.setImagePath(contact.getImagePath());
                    new Smartphone(new InfoContact(contact, contacts), new TopBarColor(black));
                }
                if (e.getSource() == buttonChangeImage) {
                    new Smartphone(new ModifyContactImage(contact, contacts), new TopBarColor(black));
                    System.out.println("Changement de l'image du contact: " + contact.getFullName());
                }
            } catch (SmartphoneException sme) {
                System.out.println(sme.getErrorMessage());
                System.out.println(sme.getErrorCode());
            }
        }
    }
}
