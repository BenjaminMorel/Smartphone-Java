package Errors;

import javax.swing.*;

public class ErrorPanel extends JOptionPane {

    /**
     * JOptionPane permettant d'afficher un message d'erreur
     * @param error, code de l'erreur
     * @param errorMessage message de l'erreur
     */
    public ErrorPanel(int error, String errorMessage) {

        JOptionPane optionPane = new JOptionPane();
        optionPane.showMessageDialog(null, errorMessage, "Erreur: " + error , WARNING_MESSAGE);
    }

}
