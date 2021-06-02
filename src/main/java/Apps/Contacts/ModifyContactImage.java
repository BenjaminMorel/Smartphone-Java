package Apps.Contacts;

import Demo.Smartphone;
import Apps.Gallery.GalleryWindow;
import TopBar.TopBarContactApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ModifyContactImage extends GalleryWindow {

    private Contact contact;                    // Instance de contact, afin de savoir de quel contact on veut changer l'image
    private ArrayList<Contact> contacts;        // Import de l'arrayListe des contacts
    private Smartphone switchApp;               // Instance de smartphone qui permettra de recréer un panel central

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

    @Override
    public void setListener()  {
        for (int i = 0; i < buttonImages.length; i++) {
            buttonImages[i].addActionListener(new ChooseImage());
        }
    }

    class ChooseImage implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < buttonImages.length; i++) {
                if (e.getSource() == buttonImages[i]) {
                    contact.setImagePath(images.get(i).getName());
                    System.out.println(images.get(i).getName());
                    switchApp = new Smartphone(new ModifyContact(contact, contacts), new TopBarContactApp());
                }
            }
        }
    }
}
