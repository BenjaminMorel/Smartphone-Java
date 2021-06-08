package Apps.Contacts;

import Demo.Smartphone;
import Errors.ErrorPanel;
import Storable.JSONStorage;
import TopBar.TopBarColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class NewContact extends JPanel implements ContactInterace {

    private JPanel panelInfoContact;
    private JLabel firstName, lastName, telNumber, birthDate;
    private JTextField firstNameText, lastNameText, telNumberText, birthDateText;

    private JPanel panelImage;
    private JLabel labelImage;

    private JButton buttonConfirm, buttonCancel;

    private ArrayList<Contact> contacts;
    private JSONStorage storable = new JSONStorage();
    private File jsonFile = new File("Data/Contacts.json");
    private int nbContacts;

    private Smartphone switchApp;

    public NewContact(ArrayList<Contact> contacts) {
        // SetLayout pour le panel principal
        setLayout(null);
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

        // Création du panel
        panelInfoContact = new JPanel();
        panelInfoContact.setLayout(new GridLayout(4,1));
        panelInfoContact.setBounds(40, 230, 300, 180);

        // Création des labels contenants les infos du contacts
        firstName = new JLabel("Prénom:");
        panelInfoContact.add(firstName);
        lastName = new JLabel("Nom:");
        panelInfoContact.add(lastName);
        telNumber = new JLabel("Numéro de téléphone:");
        panelInfoContact.add(telNumber);
        birthDate = new JLabel("Date de naissance:");
        panelInfoContact.add(birthDate);

        // Création textField pour recevoir les infos de l'utilisateur
        firstNameText = new JTextField();
        firstNameText.setBounds(180, 240, 100, 25);
        add(firstNameText);
        lastNameText = new JTextField();
        lastNameText.setBounds(180, 282, 100, 25);
        add(lastNameText);
        telNumberText = new JTextField();
        telNumberText.setBounds(180, 330, 100, 25);
        add(telNumberText);
        birthDateText = new JTextField();
        birthDateText.setBounds(180, 375, 100, 25);
        add(birthDateText);

        // Button pour confirmer les informations entrées
        buttonConfirm = new JButton("Enregistrer");
        buttonConfirm.addActionListener(new NewContactListener());
        buttonConfirm.setBounds(60, 500, 100, 25);
        buttonConfirm.setFont(new Font("Roboto", Font.BOLD, 11));
        add(buttonConfirm);


        // Bouton pour annuler et revenir sur la page des contacts
        buttonCancel = new JButton("Annuler");
        buttonCancel.addActionListener(new NewContactListener());
        buttonCancel.setBounds(180, 500, 100, 25);
        buttonCancel.setFont(new Font("Roboto", Font.BOLD, 11));
        add(buttonCancel);

        // Ajout des panels
        add(panelImage);
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

    public boolean validateInformation() {

        // Vérifie si le contact existe déjà
        String strFullName = lastNameText.getText() + firstNameText.getText();
        for (int i = 0; i < contacts.size(); i++) {
            if (strFullName.equals(contacts.get(i).getFullName())) {
                System.out.println("Contact déjà existant");
                new ErrorPanel(122);
                return false;
            }
        }

        // Vérifie si le prénom est vide
        if (firstNameText.getText().isEmpty()) {
            System.out.println("Prénom vide");
            new ErrorPanel(100);
            return false;
        }

        // Vérifie si le nom est vide
        if (lastNameText.getText().isEmpty()) {
            System.out.println("Nom vide");
            return false;
        }
//        Double num;
//        System.out.println(num = Double.parseDouble(firstNameText.getText()));

        if (firstNameText.getText().matches("[0-9]")) {
            System.out.println("pas de numéro autorisés");
            return false;
        }

        if (lastNameText.getText().matches("[0-9]")) {
            System.out.println("pas de numéro autorisés");
            return false;
        }

        return true;
    }


    @Override
    public void saveInJsonStorage(File destination) throws Exception {

        // Mise en forme du prénom, avec 1ère lettre en majuscule et le reste en minuscule
        String strFirstName = firstNameText.getText();
        strFirstName = strFirstName.substring(0, 1).toUpperCase() + strFirstName.substring(1).toLowerCase();

        // Mise en forme du nom, avec 1ère lettre en majuscule et le reste en minuscule
        String strLastName = lastNameText.getText();
        strLastName = strLastName.substring(0, 1).toUpperCase() + strLastName.substring(1).toLowerCase();

        // Mise en forme du téléphone, avec format sur l'heure


        // Mise en forme de la date de naissance, avec format sur la date
//        SimpleDateFormat formatter = new SimpleDateFormat("\"dd.mm.yyyy\"");
//        String strDate = formatter.format(birthDateText.getText());

        // Création du nouveau contact en prenant les infos des textFields et image par défaut
        contacts.add(new Contact(strFirstName, strLastName, telNumberText.getText(), birthDateText.getText(), "src/main/java/Images/ContactApp/Contact.png"));

        // Appel du jsonStorage et modifications de l'arrayList des contacts en y ajoutant le nouveau contact
        storable.write(destination, contacts);

    }

    class NewContactListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            // Listener pour confirmer le nouveau contact
            if (e.getSource() == buttonConfirm) {

                if (validateInformation()) {
                    try {
                        saveInJsonStorage(jsonFile);
                        System.out.println("Enregistrement du nouveau contact");
                    } catch (Exception exception) {
                        exception.printStackTrace();
                        System.out.println("Erreur lors de la confirmation");
                    }
                    switchApp = new Smartphone(new ContactWindow(), new TopBarColor(new Color(0,0,0)));
                }
            }

            // Listener pour annuler
            if (e.getSource() == buttonCancel) {
                System.out.println("Annulation");
                switchApp = new Smartphone(new ContactWindow(), new TopBarColor(new Color(0,0,0)));
            }
        }
    }
}
