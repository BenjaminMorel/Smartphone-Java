package Errors;

import javax.swing.*;

public class ErrorPanel extends JDialog {

    public ErrorPanel(String errorMessage) {

        JOptionPane.showMessageDialog(null, errorMessage);

    }

}
