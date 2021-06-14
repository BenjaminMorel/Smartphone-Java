package Apps.Contacts;

import Demo.Smartphone;
import Errors.*;
import Storable.JSONStorage;
import TopBar.TopBarColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class NewContact extends JPanel {

    // Variables générales
    private JTextField firstNameText, lastNameText, telNumberText, birthDateText;
    private JButton buttonConfirm, buttonCancel;

    // Dimensions
    private static final int dimensionTextFieldHeight = 25;
    private static final int dimensionTextFieldWidth = 100;

    // Fonts et couleurs
    private final Color black = Color.BLACK;
    private final Font font = new Font("Roboto", Font.BOLD, 11);

    private final ArrayList<Contact> contacts;
    private final JSONStorage storable = new JSONStorage();
    private final File jsonFile = new File(System.getenv("HOME") + "\\Contacts.json");

    /**
     * Constructeur de la classe NewContact qui permet d'ajouter un nouveau contact
     * @param contacts ArrayList contentant les contacts
     */

    public NewContact(ArrayList<Contact> contacts) {
        this.contacts = contacts;
        setLayout(null);
        setSmartphoneShape();                                                                                           // Ajout du contour du smartphone

        creationPanelImage();                                                                                           // Création du panel contenant l'image par défaut
        creationMainPanel();                                                                                            // Création du panel principal
        creationButtonConfirm();                                                                                        // Création du bouton pour confirmer les données entrées
        creationButtonCancel();                                                                                         // Création du bouton pour annuler les données entrées
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
     * Création du panel qui contient l'image du contact par défaut
     */

    public void creationPanelImage() {
        JPanel panelImage = new JPanel();                                                                                      // Panel image
        panelImage.setLayout(new BorderLayout());
        JLabel labelImage = new JLabel();
        labelImage.setIcon(new ImageIcon(ClassLoader.getSystemResource("Images/ContactApp/Contact.png")));
        panelImage.setBounds(55, 50, 300, 150);
        panelImage.add(labelImage);
        add(panelImage);
    }

    /**
     * Création du panel principal contenant les label et texts fields à remplir
     */

    public void creationMainPanel() {
        JPanel panelInfoContact = new JPanel();                                                                                // Création du panel
        panelInfoContact.setLayout(new GridLayout(4,1));
        panelInfoContact.setBounds(40, 230, 300, 180);

        JLabel firstName = new JLabel("Prénom:");                                                                          // Création des labels contenants les infos du contacts
        panelInfoContact.add(firstName);
        JLabel lastName = new JLabel("Nom:");
        panelInfoContact.add(lastName);
        JLabel telNumber = new JLabel("Numéro de téléphone:");
        panelInfoContact.add(telNumber);
        JLabel birthDate = new JLabel("Date de naissance:");
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
     * Méthode pour sauver les modifications dans le fichier JSON
     * @param destination Spécifier le fichier de destination
     * @throws SmartphoneException Ajout des exceptions Smartphone à la méthode
     */

    public void saveInJsonStorage(File destination) throws SmartphoneException {
        try {
            contacts.add(new Contact(firstNameText.getText(), lastNameText.getText(), telNumberText.getText(), birthDateText.getText(), "Images/ContactApp/Contact.png"));
            storable.write(destination, contacts);
        } catch (SmartphoneException e) {
            new ErrorPanel(e.getErrorCode(), e.getErrorMessage());
            throw new SmartphoneException(e.getErrorMessage(), ErrorCode.BAD_PARAMETER);
        }
    }

    /**
     * Classe contenant des actions listener pour :
     *   - Confirmer les infos du nouveau contact
     *   - Annuler et revenir à la classe ContactWindow avec la liste des contacts
     */

    class NewContactListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (e.getSource() == buttonConfirm) {
                    saveInJsonStorage(jsonFile);
                    System.out.println("Enregistrement du nouveau contact");
                    new Smartphone(new ContactWindow(), new TopBarColor(black));
                }
                if (e.getSource() == buttonCancel) {
                    System.out.println("Annulation");
                    new Smartphone(new ContactWindow(), new TopBarColor(black));
                }
            } catch (SmartphoneException sme) {
                System.out.println(sme.getErrorMessage());
                System.out.println(sme.getErrorCode());
            }
        }
    }
}
