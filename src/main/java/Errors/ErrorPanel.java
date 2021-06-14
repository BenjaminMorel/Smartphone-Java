package Errors;

import javax.swing.*;

public class ErrorPanel extends JOptionPane {

    public ErrorPanel(int error, String errorMessage) {

        JOptionPane optionPane = new JOptionPane();
        optionPane.showMessageDialog(null, errorMessage, "Erreur: " + error , WARNING_MESSAGE);
    }

}
