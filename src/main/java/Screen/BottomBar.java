package Screen;

import Demo.Smartphone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BottomBar extends JPanel implements ActionListener {
    private JPanel panel ;
    private JLabel label1 ;
    private Dimension dimension = new Dimension(300, 78) ;
    private Smartphone switchapp ;
    private JButton button ;

    public BottomBar() {
        setLayout(null);
       setBackground(Color.yellow);
       setPreferredSize(dimension);

        ImageIcon iconContourSmartphone = new ImageIcon("src/main/java/Images/smartphone_PNG.png") ;
        JLabel labelContourSmartphone = new JLabel() ;
        labelContourSmartphone.setIcon(iconContourSmartphone);
        labelContourSmartphone.setBounds(9, -609, 320, 660);
        add(labelContourSmartphone) ;

       button = new JButton() ;
       button.addActionListener(this);
       button.setBounds(135, 7, 70, 23);
       button.setBorderPainted(false);
       button.setFocusPainted(false);
       button.setContentAreaFilled(false);
       button.setOpaque(false);
       add(button) ;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            switchapp = new Smartphone(new HomeScreen()) ;
        }
    }
}
