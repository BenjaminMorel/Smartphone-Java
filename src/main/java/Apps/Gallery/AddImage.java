package Apps.Gallery;

import Demo.Smartphone;

import Errors.SmartphoneException;
import TopBar.TopBarColor;
import Storable.JSONStorage;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class AddImage extends JPanel {

    private JFileChooser fileChooser;

    private final JSONStorage storable = new JSONStorage();

    private final ArrayList<Images> images;

    private final File jsonFile = new File(System.getenv("HOME") + "\\Images.json"); //fichier pour stocker les path
    private final Color black = new Color(0,0,0);

    /**
     * constructeur de la classe Add images qui a en paramètre
     * @param images
     */
    public AddImage(ArrayList<Images> images) {

        setLayout(null);

        this.images = images;
        creationFileChooser();
        creationPanelChooser();


        setSmartphoneShape();
    }

    /**
     * methode qui crée un file chooser (chercher les images dans l'ordi)
     * que jpg, png, gif, jpeg acceptées
     */
    public void creationFileChooser()
    {
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getenv("HOME") + "\\ImagesGallery"));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setBounds(0,-40,260,500);
        fileChooser.setPreferredSize(new Dimension(280,400));

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg"); //choisir seulement fichiers type images
        fileChooser.setFileFilter(filter);


        fileChooser.addActionListener(new ButtonsFileChooser());
    }

    /**
     * creation d'un panel, qui va contenir le file chooser
     */
    public void creationPanelChooser()
    {
        JPanel panelChooser = new JPanel();
        panelChooser.setLayout(null);
        panelChooser.setBounds(40,40,260,500);

        panelChooser.add(fileChooser);
        add(panelChooser);
    }

    /**
     * donner des fonctionnalités aux boutons du file chooser(cancel, open)
     */
    class ButtonsFileChooser implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
                    //fichier choisi
                    File f = fileChooser.getSelectedFile();

                    String path = "ImagesGallery/" + f.getName();
                    images.add(new Images(path));
                    try {
                        storable.writeImages(jsonFile, images);
                    }catch(SmartphoneException sm){
                        System.out.println(sm.getErrorMessage());
                        System.out.println(sm.getErrorCode());
                    }
                    new Smartphone(new GalleryWindow(), new TopBarColor(black));
                }
                if (e.getActionCommand().equals(JFileChooser.CANCEL_SELECTION)) {
                    new Smartphone(new GalleryWindow(), new TopBarColor(black));
                }
            }catch (SmartphoneException sme) {
                System.out.println(sme.getErrorMessage());
                System.out.println(sme.getErrorCode());
            }
        }
    }

    /**
     * donne les contours du natel
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
