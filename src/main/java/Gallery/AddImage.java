package Gallery;

import Demo.Smartphone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AddImage extends JPanel {

    private Smartphone switchApp;

    private JPanel panelChooser ;
    private JFileChooser fileChooser;
    JFileChooser chooser;

    public AddImage() {

        panelChooser = new JPanel();
        panelChooser.setLayout(new BorderLayout());
        panelChooser.setPreferredSize(new Dimension(300,400));
        panelChooser.setMinimumSize(new Dimension(100,50));

        chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setDialogTitle("Wesh la NeZOOO");
        panelChooser.add(chooser, BorderLayout.NORTH);

        chooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION))
                {
                    System.out.println("file selected : " + chooser.getSelectedFile());
                }
                if (e.getActionCommand().equals(JFileChooser.CANCEL_SELECTION))
                {
                    switchApp = new Smartphone(new GalleryWindow());
                }
            }
        });

        panelChooser.add(chooser);
        add(panelChooser);

    }
}
