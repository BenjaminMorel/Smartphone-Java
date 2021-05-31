package Gallery;

import Demo.Smartphone;
import Storable.JSONStorage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.spi.FileTypeDetector;
import java.util.ArrayList;

public class AddImage extends JPanel {

    private Smartphone switchApp;

    private JPanel panelChooser ;
    private JFileChooser fileChooser;

    private JSONStorage storable = new JSONStorage();

    File f; //fichier choisi
    File jsonFile = new File("Data/Images.json"); //fichier pour stocker les path

    public AddImage(ArrayList<Images> images) {

        panelChooser = new JPanel();
        panelChooser.setLayout(new BorderLayout());
        panelChooser.setPreferredSize(new Dimension(300,400));
        panelChooser.setMinimumSize(new Dimension(100,50));

        fileChooser = new JFileChooser(new File(""));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setDialogTitle("Wesh la NeZOOO");
        panelChooser.add(fileChooser, BorderLayout.NORTH);


        fileChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION))
                {

                    f = fileChooser.getSelectedFile();
                    String path = "ImagesGallery" +"/" + f.getName();
                    images.add(new Images(path));
                    storable.writeImages(jsonFile, images);
                    switchApp = new Smartphone(new GalleryWindow());
                }
                if (e.getActionCommand().equals(JFileChooser.CANCEL_SELECTION))
                {
                    switchApp = new Smartphone(new GalleryWindow());
                }
            }
        });

        panelChooser.add(fileChooser);
        add(panelChooser);

    }

}
