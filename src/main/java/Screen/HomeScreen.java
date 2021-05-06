package Screen;
import Demo.Smartphone ;
import General.Time;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;


public class HomeScreen extends JFrame implements ActionListener {
    private Image wallpaper ;
    private String time ;
    private String date ;
    private Time hours ;
    private Font fontBold ;
    private Font font ;
    private Image googleSearchBar ;



    public HomeScreen() {
        this.setSize(600,690);
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(20, 15, 300, 623, 70, 70));

        wallpaper = new ImageIcon("src/main/java/Images/wallpaper_PNG.png").getImage();

        // on recherche l'heure
        hours = new Time() ;
        time = hours.getTime() ;
        fontBold = new Font("Helvetica", Font.BOLD, 30) ;
        font = new Font("Helvetica", Font.PLAIN, 12);

        date = hours.getDate();

        googleSearchBar = new ImageIcon("src/main/java/Images/googleSearchBar_PNG.png").getImage() ;


    }


    public void paint(Graphics g){
        Graphics2D g2D = (Graphics2D) g;

        // wallpaper
        g2D.drawImage(wallpaper, 20, 15, null);

        // date et heure
        g2D.setColor(Color.lightGray);
        g2D.fillRoundRect(48, 70, 240, 90, 30, 30);

        g2D.setColor(Color.black);
        g2D.setFont(fontBold);
        g2D.drawString(time, 197, 127);

        g2D.setColor(Color.white);
        g2D.setFont(font);
        g2D.drawString(time, 50, 47);

        g2D.setColor(Color.black);
        g2D.setFont(font);
        g2D.drawString(date, 62, 120);

        g2D.drawImage(googleSearchBar, 38, 400, null) ;

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == googleSearchBar){

        }
    }
}
