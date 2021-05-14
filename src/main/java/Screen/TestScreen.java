package Screen;

import Demo.Smartphone;
import Demo.SwitchApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestScreen extends JPanel implements ActionListener {

    private JButton button = new JButton("test") ;
    private SwitchApp switchApp;


    public TestScreen() {

        add(button) ;
        setBackground(Color.black);
        button.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            switchApp = new SwitchApp(this, new HomeScreen()) ;
        }
    }
}
