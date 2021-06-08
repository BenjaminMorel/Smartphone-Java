package Apps.Gallery;

import Apps.Contacts.Contact;
import Apps.Contacts.ContactWindow;
import Demo.Smartphone;
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

    private int width;                                                                                                  //largeur fixe pour le panel et l'image affichée en grand
    private int height;                                                                                                 //hauteur fixe pour le panel et l'image affichée en grand

    private ArrayList<Images> images;                                                                                   //liste de images

    private Smartphone switchApp;                                                                                       //pouvoir changer de classe

    //button retour, qui contient une icone dimensionnée à la taille du bouton
    private JButton buttonReturn = new JButton(new ImageIcon(new ImageIcon("src/main/java/Images/ContactApp/BackButton.png").getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT)));

    protected JButton buttonDelete = new JButton("Delete");                                                         //création du bouton delete

    private JSONStorage jsonStorage = new JSONStorage();

    private String name;                                                                                                //String qui contiendra le path de l'iamge donné en paramètre


    /**
     * Classe du constructeur qui a en parametre
     * @param name String name, qui contient le path de l'image
     * @param images Array list avec toutes les images
     */
    public ImageGrand(String name, ArrayList<Images> images) {

        this.images = images;
        this.name = name;                                                                                               // pathImage est égal au nom em paramètre qui est aussi un path

        setSmartphoneShape();                                                                                           // ajout du contour du smartphone

        height = 400;
        width = 285;

        setLayout(null);

        panel = new JPanel();
        panel.setOpaque(false);

        image = new ImageIcon(name).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);                   //redimenssionner l'image en allant chercher le path qui est donné en paramètre

        label = new JLabel(new ImageIcon(image));                                                                       //ajouter l'image à un Jlabel
        panel.add(label);                                                                                               //ajouter le label au panel

        panel.setBounds(30,60,width,height);                                                                      //donner taille et emplacement du panel qui contient l'image

        add(panel);                                                                           //ajouter panel qui contien l'image au panel principal, le panel de l'application
        add(buttonReturn);                                                                    //ajouter un bouton retour au panel principal
        add(buttonDelete);                                                                    //ajouter un bouton supprimer au panel principal

        buttonDelete.setBounds(120,500,75,25);                              //donner une taille et un emplacement au bouton supprimer dans le panel principal
        buttonDelete.addActionListener(new ButtonDeleteImage());                              //attribuer un actionlistener qui va appeler une classe, pour que celui-ci ait une fonction

        buttonReturn.setBounds(30, 20,25,25);                               //donner une taille et un emplacement au bouton supprimer dans le panel principal
        buttonReturn.addActionListener(new ButtonReturnImage());                              //attribuer un actionlistener qui va appeler une classe, pour que le bouton ait une fonction

        setBackground(Color.GRAY);                                                                                      //couleur de fond gris


    }

    /**
     *
     */
    class ButtonReturnImage implements ActionListener                                       //classe bouton retour qui contient un actionListener pour donner une fonction au bouton
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == buttonReturn)
            {
                switchApp = new Smartphone(new GalleryWindow(), new TopBarGalleryApp());                            //revient à la page Apps.Gallery Window
            }
        }
    }

    /**
     *
     */
    class ButtonDeleteImage implements ActionListener                                                                   //classe bouton supprimer qui contient un actionListener pour donner une fonction au bouton
    {
        ArrayList<Contact> contacts;
        JSONStorage jsonStorage = new JSONStorage();

        @Override
        public void actionPerformed(ActionEvent e) {

            contacts = jsonStorage.read(new File("Data/Contacts.json"), contacts);

            if(e.getSource() == buttonDelete)
            {
                for (int i = 0; i < images.size(); i++) {                                                               //parcourir toute la liste des images
                    if(name == images.get(i).getName())    //regarder adresse                                           //si le nom en parametre est le m'eme sur l'image cliqué
                    {
                        for (int j = 0; j < contacts.size(); j++) {
                            if ((contacts.get(j).getImagePath().equals(images.get(i).getName()))) {
                                contacts.get(j).setImagePath("src/main/java/Images/ContactApp/Contact.png");
                            }
                        }
                        images.remove(images.get(i));                                                                   //supprimer l'image de la Liste d'Images
                    }
                                                                                                                        //revenir sur la page Apps.Gallery Windows, il n'y aura pas l'image supprimée
                }

                try
                {
                    jsonStorage.write(new File("Data/Contacts.json"), contacts);
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                    System.out.println("Erreur lors de la confirmation");
                }

                System.out.println("remove " + name);

                //mettre à jour le fichier JSon avec tous les paths
                try
                {
                    jsonStorage.writeImages(new File("Data/Images.json"), images);
                }
                catch (Exception exception)
                {
                    exception.printStackTrace();
                    System.out.println("Erreur lors de la confirmation");
                }

                switchApp = new Smartphone((new GalleryWindow()), new TopBarGalleryApp());
            }
        }
    }

    /**
     * methode qui donne les bordures du natel
     */
    public void setSmartphoneShape() {
        // Ajout du contour du smartphone
        ImageIcon iconContourSmartphone = new ImageIcon("src/main/java/Images/smartphone_PNG.png");
        JLabel labelContourSmartphone = new JLabel();
        labelContourSmartphone.setIcon(iconContourSmartphone);
        labelContourSmartphone.setBounds(9, -8, 320, 600);
        add(labelContourSmartphone);
    }
}
