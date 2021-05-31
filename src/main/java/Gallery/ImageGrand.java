package Gallery;

import Demo.Smartphone;
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
    private int width;
    private int height;

    private ArrayList<Images> images1;

    private Smartphone switchApp;

    private JButton buttonReturn = new JButton(new ImageIcon(new ImageIcon("src/main/java/Images/IconButtons/retour").getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT)));

    protected JButton buttonDelete = new JButton("Delete");

    private JSONStorage jsonStorage = new JSONStorage();

    private String pathImage;


    public ImageGrand(String name, ArrayList<Images> images) {

        images1 = images;

        // ajout du contour du smartphone
        setSmartphoneShape();

        pathImage = name;

        height = 400;
        width = 285;

        setLayout(null);

        panel = new JPanel();

        System.out.println(name);
        image = new ImageIcon(name).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);


        label = new JLabel(new ImageIcon(image));
        panel.add(label);

        panel.setBounds(30,60,width,height);
        add(panel);
        add(buttonReturn);
        add(buttonDelete);

        buttonDelete.setBounds(120,500,75,25);
        buttonDelete.addActionListener(new ButtonDeleteImage());

        buttonReturn.setBounds(30, 20,25,25);
        buttonReturn.addActionListener(new ButtonReturnImage());
        setBackground(Color.GRAY);


    }

    class ButtonReturnImage implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == buttonReturn)
            {
                switchApp = new Smartphone(new GalleryWindow());
            }
        }
    }

    class ButtonDeleteImage implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == buttonDelete)
            {
                for (int i = 0; i < images1.size(); i++) {
                    if(pathImage == images1.get(i).getName())
                    {
                        System.out.println("remove " + pathImage);
                        System.out.println("remove " + images1.get(i).getName());
                        images1.remove(images1.get(i));
                    }

                    jsonStorage.writeImages(new File("Data/Images.json"), images1);
                    switchApp = new Smartphone((new GalleryWindow()));
                }

            }
        }
    }

    public void setSmartphoneShape() {
        // Ajout du contour du smartphone
        ImageIcon iconContourSmartphone = new ImageIcon("src/main/java/Images/smartphone_PNG.png");
        JLabel labelContourSmartphone = new JLabel();
        labelContourSmartphone.setIcon(iconContourSmartphone);
        labelContourSmartphone.setBounds(9, -8, 320, 600);
        add(labelContourSmartphone);
    }
}
