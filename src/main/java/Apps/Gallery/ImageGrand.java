package Apps.Gallery;

import Apps.Contacts.Contact;

import Demo.Smartphone;

import Errors.ErrorCode;
import Errors.SmartphoneException;
import TopBar.TopBarColor;
import TopBar.TopBarGalleryApp;
import Storable.JSONStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import java.util.ArrayList;

public class ImageGrand extends JPanel {

    protected JPanel panel;
    protected JLabel label;
    protected Image image;

    private static final int width = 285;                                                                               //largeur fixe pour le panel et l'image affichée en grand
    private static final int height = 400;                                                                              //hauteur fixe pour le panel et l'image affichée en grand

    private ArrayList<Images> images;                                                                                   //liste de images
    protected ArrayList<Contact> contacts;
    private final JSONStorage jsonStorage = new JSONStorage();

    //button retour, qui contient une icone dimensionnée à la taille du bouton
    private final JButton buttonReturn = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("Images/ContactApp/BackButton.png")).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)));

    protected JButton buttonDelete;                                                         //création du bouton delete
    protected JButton buttonEdit;
    protected JButton buttonConfirmation = new JButton("OK");

    private String name;                                                                                                //String qui contiendra le path de l'iamge donné en paramètre

    protected JLabel nomPhoto;
    protected JTextField textField;

    private final Color black = new Color(0, 0, 0);

    /**
     * Classe du constructeur qui a en parametre
     *
     * @param name   String name, qui contient le path de l'image
     * @param images Array list avec toutes les images
     */
    public ImageGrand(String name, ArrayList<Images> images) {

        this.images = images;
        this.name = name;                                                                                               // pathImage est égal au nom em paramètre qui est aussi un path

        setSmartphoneShape();                                                                                           // ajout du contour du smartphone
        setLayout(null);

        creationPanel();
        creationBoutonReturn();
        creationBoutonDelete();
        creationBoutonEdit();

        setBackground(Color.GRAY);


    }

    /**
     * creation du panel secondaire qui contient le path de l'image dans un label
     */
    public void creationPanel() {

        panel = new JPanel();
        panel.setOpaque(false);

        image = new ImageIcon(ClassLoader.getSystemResource(name)).getImage();

        label = new JLabel(new ImageIcon(image));                                                                       //ajouter l'image à un Jlabel
        nomPhoto = new JLabel(name);

        panel.add(nomPhoto);
        panel.add(label);                                                                                               //ajouter le label au panel

        panel.setBounds(30, 60, width, height);                                                                   //donner taille et emplacement du panel qui contient l'image
        add(panel);                                                                                                     //ajouter panel qui contien l'image au panel principal, le panel de l'application
    }

    /**
     * creation et adaptation du bouton edit dans le panel principal
     */
    public void creationBoutonEdit() {
        buttonEdit = new JButton("Edit");
        add(buttonEdit);
        buttonEdit.setBounds(120, 20, 75, 25);
        buttonEdit.addActionListener(new ModifyImage());
    }

    /**
     * creation et adaptation du bouton delete dans le panel principal
     */
    public void creationBoutonDelete() {
        buttonDelete = new JButton("Delete");
        add(buttonDelete);                                                                    //ajouter un bouton supprimer au panel principal
        buttonDelete.setBounds(120, 500, 75, 25);                              //donner une taille et un emplacement au bouton supprimer dans le panel principal
        buttonDelete.addActionListener(new ModifyImage());                              //attribuer un actionlistener qui va appeler une classe, pour que celui-ci ait une fonction

    }

    /**
     * creation et adaptation du bouton return dans le panel principal
     */
    public void creationBoutonReturn() {

        add(buttonReturn);                                                                    //ajouter un bouton retour au panel principal
        buttonReturn.setBounds(30, 20, 25, 25);                               //donner une taille et un emplacement au bouton supprimer dans le panel principal
        buttonReturn.addActionListener(new ModifyImage());                              //attribuer un actionlistener qui va appeler une classe, pour que le bouton ait une fonction
        buttonReturn.setBorderPainted(false);
        buttonReturn.setFocusPainted(false);
        buttonReturn.setContentAreaFilled(false);

    }


    /**
     * methode qui charge l'arraylist contacts, et lecture dans le fichier json
     */
    public void loadContact()
    {
        try {
            contacts = jsonStorage.read(new File(System.getenv("HOME") + "\\Contacts.json"), contacts);
        } catch (SmartphoneException sm) {
            System.out.println(sm.getErrorMessage());
            System.out.println(sm.getErrorCode());
        }
    }

    /**
     * methode qui charge l'arraylist images, et lecture dans le fichier json
     */
    public void loadImage()
    {
        try {
            images = jsonStorage.readImages(new File(System.getenv("HOME") + "\\Images.json"), images);
        } catch (SmartphoneException sm) {
            System.out.println(sm.getErrorMessage());
            System.out.println(sm.getErrorCode());
        }
    }

    /**
     * methode qui enregistre l'arraylist images, et lecture dans le fichier json
     */
    public void saveImage()
    {
        try {
            jsonStorage.writeImages(new File(System.getenv("HOME") + "\\Images.json"), images);
        } catch (SmartphoneException sm) {
            System.out.println(sm.getErrorMessage());
            System.out.println(sm.getErrorCode());
        }
    }



    /**
     * methode qui implemente tous les actions listeners de tous les boutons
     */
    class ModifyImage implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {


            loadContact();

            //button return
            if (e.getSource() == buttonReturn) {
                try {
                    new Smartphone(new GalleryWindow(), new TopBarColor(black));
                } catch (SmartphoneException sme) {
                    System.out.println(sme.getErrorMessage());
                    System.out.println(sme.getErrorCode());
                }
            }

            //button edit
            if (e.getSource() == buttonEdit) {


                try {
                    new Smartphone(new EditImageName(name, images), new TopBarColor(black));
                } catch (SmartphoneException smartphoneException) {
                    smartphoneException.printStackTrace();
                }

            }


                //button delete
                if (e.getSource() == buttonDelete) {
                    for (int i = 0; i < images.size(); i++) {                                                               //parcourir toute la liste des images
                        if (name == images.get(i).getName())    //regarder adresse                                           //si le nom en parametre est le m'eme sur l'image cliqué
                        {
                            for (int j = 0; j < contacts.size(); j++) {
                                if ((contacts.get(j).getImagePath().equals(images.get(i).getName()))) {
                                    contacts.get(j).setImagePath("Images/ContactApp/Contact.png");
                                }
                            }
                            images.remove(images.get(i));                                                                   //supprimer l'image de la Liste d'Images
                        }
                        //revenir sur la page Apps.Gallery Windows, il n'y aura pas l'image supprimée
                    }

                    try {
                        jsonStorage.write(new File(System.getenv("HOME") + "\\Contacts.json"), contacts);
                        System.out.println("remove " + name);
                        jsonStorage.writeImages(new File(System.getenv("HOME") + "\\Images.json"), images);     //mettre à jour le fichier JSon avec tous les paths
                    } catch (SmartphoneException sm) {
                        System.out.println(sm.getErrorMessage());
                        System.out.println(sm.getErrorCode());
                    }

                    try {
                        new Smartphone((new GalleryWindow()), new TopBarColor(black));
                    } catch (SmartphoneException sme) {
                        System.out.println(sme.getErrorMessage());
                        System.out.println(sme.getErrorCode());
                    }
                }
            }
        }



        /**
         * methode qui donne les bordures du natel
         */
        public void setSmartphoneShape()
        {
            // Ajout du contour du smartphone
            ImageIcon iconContourSmartphone = new ImageIcon(ClassLoader.getSystemResource("Images/smartphone_PNG.png"));
            JLabel labelContourSmartphone = new JLabel();
            labelContourSmartphone.setIcon(iconContourSmartphone);
            labelContourSmartphone.setBounds(9, -8, 320, 600);
            add(labelContourSmartphone);
        }

}
