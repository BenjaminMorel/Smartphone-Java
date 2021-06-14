package Apps.Gallery;

import Demo.Smartphone;
import Errors.SmartphoneException;
import Screen.HomeScreen;
import TopBar.TopBarColor;
import TopBar.TopBarHomeScreen;
import Storable.JSONStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class GalleryWindow extends JPanel{

    private static final int WIDTH = 300;

    private JPanel panel;
    protected JPanel buttonsAddDeletePanel;

    private final JScrollPane scrollPane = new JScrollPane();

    private final JSONStorage jsonStorage = new JSONStorage();

    protected JButton[] buttonImages;
    protected final JButton buttonAdd = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("Images/GalleryApp/AddIcon.png")).getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT)));
    protected final JButton buttonReturn = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("Images/ContactApp/BackButton.png")).getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT)));

    protected ArrayList<Images> images;

    private final Color black = new Color(0,0,0);

    /**
     * Constructeur de la classe
     */
    public GalleryWindow() {
        setLayout(null);
        JScrollPane scrollPane = new JScrollPane(panel);

        buttonsAddDeletePanel = new JPanel();
        buttonsAddDeletePanel.setBounds(15,5,300,35);

        // ajout du contour du smartphone
        setSmartphoneShape();
        loadImage();

        //donner taille tableau JButton
        int nbImages = images.size();
        buttonImages = new JButton[nbImages];

        if((nbImages %3) == 0)
        {
            nbImages = nbImages /3;
        }
        else
        {
            nbImages = nbImages /3+1;
        }

        //faire grille
        GridLayout grid = new GridLayout(nbImages, 3, 2, 2);

        if(nbImages < 3)
        {
            grid.setRows(3);
        }
        else
        {
            grid.setRows(nbImages);
        }
        grid.setColumns(3);


        //Panel ou toutes les photos sont stockées
        panel = new JPanel(grid);
        panel.setPreferredSize(new Dimension(250, nbImages *100));                                           //
        scrollPane.setBounds(30, 40, 280, 500);


        for (int i = 0; i < images.size(); i++)
        {
            System.out.println(images.size());
            System.out.println(images.get(i).getName());
            buttonImages[i] = new JButton(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource(images.get(i).getName())).getImage().getScaledInstance(WIDTH/3, WIDTH/3, Image.SCALE_DEFAULT)));
            buttonImages[i].setBorderPainted(false);
            buttonImages[i].setFocusPainted(false);
            buttonImages[i].setContentAreaFilled(false);
            panel.add(buttonImages[i]);
        }

        setListener(); //ajouter a tous les boutons l'action listener

        for(int i=images.size(); i<7; i++) { //ajouter cases libres pour ensuite les écraser
            JLabel caseVide = new JLabel();
            caseVide.setPreferredSize(new Dimension(50, 50));
            panel.add(caseVide);
        }

        creationJScrollPane();
        creationPanelAddDelete();

        buttonReturn.setBounds(30,5,25,25);
        buttonReturn.setBorderPainted(false);
        buttonReturn.setFocusPainted(false);
        buttonReturn.setContentAreaFilled(false);

        buttonAdd.setBounds(100,5,60,30);
        buttonAdd.addActionListener(new ButtonListener());
        buttonAdd.setBorderPainted(false);
        buttonAdd.setFocusPainted(false);
        buttonAdd.setContentAreaFilled(false);

        saveImage();
    }


    /**
     * creation d'un panel qui va contenir les boutons add et return
     */
    public void creationPanelAddDelete()
    {
        buttonsAddDeletePanel.setLayout(null);
        buttonsAddDeletePanel.add(buttonReturn);
        buttonsAddDeletePanel.add(buttonAdd);
        add(buttonsAddDeletePanel);
    }

    /**
     *créer un JScrollPanel, pour avoir la scrollbar
     */
    public void creationJScrollPane()
    {
        JScrollPane scrollPane = new JScrollPane(panel);
        //paramètres du JScrollPanel
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);                               // paramètre du Scroll pane et ajout de ce dernier dans notre objet GalleryWindow (this)
        scrollPane.setWheelScrollingEnabled(true);
        scrollPane.setViewportView(this);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setOpaque(true);
        scrollPane.getViewport().setOpaque(true);
        scrollPane.getViewport().add(panel);
        scrollPane.setBackground(Color.GRAY);

        scrollPane.setBounds(30, 40, 280, 500);

        add(scrollPane);
    }

    /**
     *donner une un action listener à chaque bouton du tableau
     */
    public void setListener()
    {
        for (int i = 0; i < buttonImages.length; i++)
        {
            buttonImages[i].addActionListener(new ButtonListener());
        }

        buttonReturn.addActionListener(new ButtonListener());
    }

    /**
     * methode qui lit l'arraylist images, et lecture dans le fichier json
     */
    public void loadImage()
    {
        try {
            images = jsonStorage.readImages(new File(System.getenv("HOME") + "\\Images.json"), images);
        }catch(SmartphoneException sm){
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
        }catch(SmartphoneException sm){
            System.out.println(sm.getErrorMessage());
            System.out.println(sm.getErrorCode());
        }
    }

    /**
     *fonctionalité aux boutons :
     * - retourner
     * - cliquer sur l'image
     * - additionner l'image
     */
    class ButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                if (e.getSource() == buttonReturn) {
                    System.out.println(images.size());
                    new Smartphone(new HomeScreen(), new TopBarHomeScreen());
                }
            }catch (SmartphoneException sme) {
                System.out.println(sme.getErrorMessage());
                System.out.println(sme.getErrorCode());
            }

            try {
                for (int i = 0; i < images.size(); i++) {
                    if (e.getSource() == buttonImages[i]) {
                        new Smartphone(new ImageGrand(images.get(i).getName(), images), new TopBarColor(black));
                    }
                }
            }catch (SmartphoneException sme) {
                System.out.println(sme.getErrorMessage());
                System.out.println(sme.getErrorCode());
            }


            try {
                if (e.getSource() == buttonAdd) {
                    new Smartphone(new AddImage(images), new TopBarColor(black));
                }
            }catch (SmartphoneException sme) {
                System.out.println(sme.getErrorMessage());
                System.out.println(sme.getErrorCode());
            }
        }
    }

    /**
     *ajout du contour du smartphone
     */
    public void setSmartphoneShape() {
        // Ajout du contour du smartphone
        ImageIcon iconContourSmartphone = new ImageIcon(ClassLoader.getSystemResource("Images/smartphone_PNG.png"));
        JLabel labelContourSmartphone = new JLabel();
        labelContourSmartphone.setIcon(iconContourSmartphone);
        labelContourSmartphone.setBounds(9, -8, 320, 600);
        add(labelContourSmartphone);
    }

}

