package Screen;

import javax.swing.*;
import java.awt.*;

public class BottomBar extends JPanel{
    private JPanel panel ;
    private JLabel label1 ;
    private Dimension dimension = new Dimension(300, 78) ;

    public BottomBar() {
       setBackground(Color.yellow);
       setPreferredSize(dimension);
    }


}
