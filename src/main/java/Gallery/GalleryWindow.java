package Gallery;

import Demo.Smartphone;
import Storable.JSONStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GalleryWindow extends JPanel{

    //find image thanks to a string and then put it as a parameter

    private static final int WIDTH = 300;
    private static final int HEIGTH = 623;

    private JPanel panel = new JPanel();

    private Smartphone switchApp;

    private JScrollPane scrollPane = new JScrollPane(panel);

    private Images[] images;

    private GridLayout grid;
    private JSONStorage jsonStorage = new JSONStorage();

    private JButton[] buttonImages;
   // private JButton add = new JButton("add");

    private int nbImages;

    public GalleryWindow() {
        setLayout(null);
        JScrollPane scrollPane = new JScrollPane();
        setBackground(Color.cyan);

        // ajout du contour du smartphone
        ImageIcon iconContourSmartphone = new ImageIcon("src/main/java/Images/smartphone_PNG.png") ;
        JLabel labelContourSmartphone = new JLabel() ;
        labelContourSmartphone.setIcon(iconContourSmartphone);
        labelContourSmartphone.setBounds(9, -8, 320, 600);
        add(labelContourSmartphone) ;

        loadImage();
        nbImages = images.length;


        buttonImages = new JButton[nbImages];


        //faire round
        //grid = new GridLayout(nbImages/3+1, 3, 2, 2);

        grid = new GridLayout(0, 3, 2, 2);

        //Panel ou toutes les photos sont stockées
        panel.setLayout(grid);
        panel.setBackground(Color.RED);
        panel.setPreferredSize(new Dimension(300, 100)); //NBimages/50
        scrollPane.setBounds(26, 40, 286, 580);



        for (int i = 0; i < images.length; i++) {
            System.out.println(images.length);
            System.out.println(images[i].getName());
            buttonImages[i] = new JButton(new ImageIcon(new ImageIcon(images[i].getName()).getImage().getScaledInstance(WIDTH/3, WIDTH/3, Image.SCALE_DEFAULT)));
            buttonImages[i].setPreferredSize(new Dimension(50,50));
            buttonImages[i].addActionListener(new ButtonImageGrand());
            panel.add(buttonImages[i]);
        }


        scrollPane.getVerticalScrollBar().setUnitIncrement(20);                               // paramètre du Scroll pane et ajout de ce dernier dans notre objet allContatwindows (this)
        scrollPane.setWheelScrollingEnabled(true);
        scrollPane.setViewportView(this);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getViewport().add(panel);
        scrollPane.setOpaque(false);

        add(scrollPane);
        saveImage();
    }


        public void loadImage()
        {
            images = jsonStorage.readImages(new File("Data/Images.json"), images);
        }

        public void saveImage()
        {
            jsonStorage.writeImages(new File("Data/Images.json"), images);
        }

        class ButtonImageGrand implements ActionListener
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                for (int i = 0; i < images.length; i++)
                {
                    if(e.getSource() == buttonImages[i])
                    {
                       // switchApp = new Smartphone(new ImageGrand((ImageIcon) buttonImages[i].getIcon()));//recuperer path au lieu du getIcon
                        switchApp = new Smartphone(new ImageGrand(images[i].getName()));
                    }
                }
            }
        }

    }

