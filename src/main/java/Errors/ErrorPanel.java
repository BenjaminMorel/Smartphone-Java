package Errors;

import javax.swing.*;
import java.awt.*;

public class ErrorPanel extends JDialog {

    private JPanel mainPanel;
    private JButton button = new JButton();
    private JLabel errorMessage;

    public ErrorPanel(int error) {

        mainPanel = new JPanel();
        mainPanel.add(errorMessage);
        setOpacity(0.50f);
        setBackground(Color.red);
        setErrorMessage(error);
        add(mainPanel);


    }

    public void setErrorMessage(int error) {

        switch (error) {
            case 100 :
                errorMessage.setText("The firstname could not be empty");
                break;
            case 110 :
                errorMessage.setText("The lastname could not be empty");
                break;
            case 120:
                errorMessage.setText("The phone number could not be empty");
                break;
            case 121 :
                errorMessage.setText("The phone number is not valid");
                break;
            case 122 :
                errorMessage.setText("This phone number is already assigned to another contact");
                break;
            case 130 :
                errorMessage.setText("The birthdate is not valid");
                break;
        }
    }


}
