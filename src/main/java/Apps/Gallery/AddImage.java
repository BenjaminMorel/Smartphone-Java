package Apps.Gallery;

import Demo.Smartphone;
import TopBar.TopBarGalleryApp;
import Storable.JSONStorage;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class AddImage extends JPanel {

    private Smartphone switchApp;

    private JPanel panelChooser ;
    private JFileChooser fileChooser;

    private JSONStorage storable = new JSONStorage();

    private ArrayList<Images> images;

    private FileNameExtensionFilter filter;

    private File f;                                                //fichier choisi
    private File jsonFile = new File("Data/Images.json"); //fichier pour stocker les path

    /**
     * constructeur de la classe Add images qui a en paramètre
     * @param images
     */
    public AddImage(ArrayList<Images> images) {

        setLayout(null);

        this.images = images;

        panelChooser = new JPanel();
        panelChooser.setLayout(null);
        panelChooser.setBounds(40,40,260,500);

        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("src/main/resources/ImagesGallery"));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setBounds(0,-40,260,500);
        fileChooser.setPreferredSize(new Dimension(280,400));

        filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg"); //choisir seulement fichiers type images
        fileChooser.setFileFilter(filter);


        fileChooser.addActionListener(new ButtonsFileChooser());
        panelChooser.add(fileChooser);
        add(panelChooser);

        setSmartphoneShape();
    }

    class ButtonsFileChooser implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION))
            {
                f = fileChooser.getSelectedFile();
                String path = "src/main/resources/ImagesGallery" +"/" + f.getName();
                images.add(new Images(path));
                try {
                    storable.writeImages(jsonFile, images);
                } catch (Exception exception)
                {
                    System.out.println("failed to confirm");
                }
                switchApp = new Smartphone(new GalleryWindow(), new TopBarGalleryApp());
            }
            if (e.getActionCommand().equals(JFileChooser.CANCEL_SELECTION))
            {
                switchApp = new Smartphone(new GalleryWindow(), new TopBarGalleryApp());
            }
        }
    }

    public void setSmartphoneShape()
    {
        // Ajout du contour du smartphone
        ImageIcon iconContourSmartphone = new ImageIcon("src/main/resources/Images/smartphone_PNG.png");
        JLabel labelContourSmartphone = new JLabel();
        labelContourSmartphone.setIcon(iconContourSmartphone);
        labelContourSmartphone.setBounds(9, -8, 320, 600);
        add(labelContourSmartphone);
    }
}
