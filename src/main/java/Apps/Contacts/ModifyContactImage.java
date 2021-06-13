package Apps.Contacts;

import Demo.Smartphone;
import Apps.Gallery.GalleryWindow;
import TopBar.TopBarColor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ModifyContactImage extends GalleryWindow {

    private Contact contact;
    private ArrayList<Contact> contacts;
    private Smartphone switchApp;

    /**
     * Classe utilisée pour modifier l'image du contact passé en paramètre
     * @param contact
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
        for (int i = 0; i < buttonImages.length; i++) {
            buttonImages[i].addActionListener(new ChooseImage());
        }
    }

    /**
     * Classe interne pour modifier l'image d'un contact
     */

    class ChooseImage implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < buttonImages.length; i++) {
                if (e.getSource() == buttonImages[i]) {
                    contact.setImagePath(images.get(i).getName());
                    System.out.println(images.get(i).getName());
                    switchApp = new Smartphone(new ModifyContact(contact, contacts), new TopBarColor(Color.BLACK));
                }
            }
        }
    }
}
