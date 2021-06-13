package Apps.Contacts;

import Demo.Smartphone;
import Apps.Gallery.GalleryWindow;
import Errors.SmartphoneException;
import TopBar.TopBarColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ModifyContactImage extends GalleryWindow {

    private final Contact contact;
    private final ArrayList<Contact> contacts;

    /**
     * Classe utilisée pour modifier l'image du contact passé en paramètre
     * @param contact Contact pour lequel l'image va être modifiée
     * @param contacts ArrayList contenant les contacts
     */

    public ModifyContactImage(Contact contact, ArrayList<Contact> contacts) {
        super();
        this.contact = contact;
        this.contacts = contacts;
        buttonsAddDeletePanel.setVisible(false);
    }

    /**
     * Ajout d'un actionListener pour choisir l'image du contact
     */

    @Override
    public void setListener()  {
        for (JButton b : buttonImages) {
            b.addActionListener(new ChooseImage());
        }
    }

    /**
     * Classe interne pour modifier l'image d'un contact
     */

    class ChooseImage implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                for (int i = 0; i < buttonImages.length; i++) {
                    if (e.getSource() == buttonImages[i]) {
                        contact.setImagePath(images.get(i).getName());
                        System.out.println(images.get(i).getName());
                        new Smartphone(new ModifyContact(contact, contacts), new TopBarColor(Color.BLACK));
                    }
                }
            }catch (SmartphoneException sme) {
                System.out.println(sme.getErrorMessage());
                System.out.println(sme.getErrorCode());
            }
        }
    }
}
