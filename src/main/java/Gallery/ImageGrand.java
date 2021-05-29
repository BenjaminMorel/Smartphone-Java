package Gallery;

import Demo.Smartphone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImageGrand extends JPanel {

    private JPanel panel;
    private JLabel label;
    private Image image;
    private int width;
    private int height;

    private Smartphone switchApp;

    private JButton buttonReturn = new JButton(new ImageIcon(new ImageIcon("src/main/java/Images/IconButtons/retour").getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT)));

    private JButton buttonDelete = new JButton("Delete");

    public ImageGrand(String name) {

        // ajout du contour du smartphone
        setSmartphoneShape();

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

    public void setSmartphoneShape() {
        // Ajout du contour du smartphone
        ImageIcon iconContourSmartphone = new ImageIcon("src/main/java/Images/smartphone_PNG.png");
        JLabel labelContourSmartphone = new JLabel();
        labelContourSmartphone.setIcon(iconContourSmartphone);
        labelContourSmartphone.setBounds(9, -8, 320, 600);
        add(labelContourSmartphone);
    }
}
