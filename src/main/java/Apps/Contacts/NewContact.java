package Apps.Contacts;

import Demo.Smartphone;
import Errors.ErrorCode;
import Errors.SmartphoneException;
import Storable.JSONStorage;
import TopBar.TopBarColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class NewContact extends JPanel {

    private JPanel panelInfoContact;
    private JLabel firstName, lastName, telNumber, birthDate;
    private JTextField firstNameText, lastNameText, telNumberText, birthDateText;

    private JPanel panelImage;
    private JLabel labelImage;

    private JButton buttonConfirm, buttonCancel;

    private static final int dimensionTextFieldHeight = 25;
    private static final int dimensionTextFieldWidth = 100;

    private Color black = Color.BLACK;
    private Font font = new Font("Roboto", Font.BOLD, 11);

    private ArrayList<Contact> contacts;
    private JSONStorage storable = new JSONStorage();
    private File jsonFile = new File(System.getenv("HOME") + "\\Contacts.json");
    private int nbContacts;

    private Smartphone switchApp;

    public NewContact(ArrayList<Contact> contacts) {
        this.contacts = contacts;

        setLayout(null);
        setSmartphoneShape();                                                                                           // Ajout du contour du smartphone

        panelImage = new JPanel();                                                                                      // Panel image
        panelImage.setLayout(new BorderLayout());
        labelImage = new JLabel();
        labelImage.setIcon(new ImageIcon(ClassLoader.getSystemResource("Images/ContactApp/Contact.png")));
        panelImage.setBounds(55, 50, 300, 150);
        panelImage.add(labelImage);

        panelInfoContact = new JPanel();                                                                                // Création du panel
        panelInfoContact.setLayout(new GridLayout(4,1));
        panelInfoContact.setBounds(40, 230, 300, 180);

        firstName = new JLabel("Prénom:");                                                                          // Création des labels contenants les infos du contacts
        panelInfoContact.add(firstName);
        lastName = new JLabel("Nom:");
        panelInfoContact.add(lastName);
        telNumber = new JLabel("Numéro de téléphone:");
        panelInfoContact.add(telNumber);
        birthDate = new JLabel("Date de naissance:");
        panelInfoContact.add(birthDate);

        firstNameText = new JTextField();                                                                               // Création textField pour recevoir les infos de l'utilisateur
        firstNameText.setBounds(180, 240, dimensionTextFieldWidth, dimensionTextFieldHeight);
        add(firstNameText);
        lastNameText = new JTextField();
        lastNameText.setBounds(180, 282, dimensionTextFieldWidth, dimensionTextFieldHeight);
        add(lastNameText);
        telNumberText = new JTextField();
        telNumberText.setBounds(180, 330, dimensionTextFieldWidth, dimensionTextFieldHeight);
        add(telNumberText);
        birthDateText = new JTextField();
        birthDateText.setBounds(180, 375, dimensionTextFieldWidth, dimensionTextFieldHeight);
        add(birthDateText);

        creationButtonConfirm();
        creationButtonCancel();

        add(panelImage);
        add(panelInfoContact);
    }

    /**
     * Bouton pour annuler et revenir sur la page des contacts
     */

    public void creationButtonCancel() {
        buttonCancel = new JButton("Annuler");
        buttonCancel.addActionListener(new NewContactListener());
        buttonCancel.setBounds(180, 500, dimensionTextFieldWidth, dimensionTextFieldHeight);
        buttonCancel.setFont(font);
        add(buttonCancel);
    }

    /**
     * Button pour confirmer les informations entrées
     */

    public void creationButtonConfirm() {
        buttonConfirm = new JButton("Enregistrer");
        buttonConfirm.addActionListener(new NewContactListener());
        buttonConfirm.setBounds(60, 500, dimensionTextFieldWidth, dimensionTextFieldHeight);
        buttonConfirm.setFont(new Font("Roboto", Font.BOLD, 11));
        add(buttonConfirm);
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
     *
     * @param destination
     * @throws SmartphoneException
     */

    public void saveInJsonStorage(File destination) throws SmartphoneException {
        try {
            contacts.add(new Contact(firstNameText.getText(), lastNameText.getText(), telNumberText.getText(), birthDateText.getText(), "Images/ContactApp/Contact.png"));
            storable.write(destination, contacts);
        } catch (SmartphoneException e) {
            throw new SmartphoneException("Erreur lors de l'enregistrement des données dans le fichier JSON", ErrorCode.SAVE_ERROR);
        }
    }

    /**
     * Classe contenant des actions listener pour :
     *   - Confirmer les infos du nouveau contact
     *   - Annuler et revenu à la classe ContactWindow avec la liste des contacts
     */

    class NewContactListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == buttonConfirm) {
                try {
                    saveInJsonStorage(jsonFile);
                    System.out.println("Enregistrement du nouveau contact");
                } catch (Exception exception) {
                    exception.printStackTrace();
                    System.out.println("Erreur lors de la confirmation");
                }
                switchApp = new Smartphone(new ContactWindow(), new TopBarColor(black));
            }

            if (e.getSource() == buttonCancel) {
                System.out.println("Annulation");
                switchApp = new Smartphone(new ContactWindow(), new TopBarColor(black));
            }
        }
    }
}
