package Contacts;

import Gallery.GalleryWindow;

import javax.swing.*;

public class ModifyContactImage extends GalleryWindow {

    private Contact contact;
    private JButton buttonConfirm;

    public ModifyContactImage(Contact contact) {
        super();
        buttonConfirm = new JButton("Confirmer");
        add(buttonConfirm);
        buttonConfirm.setBounds(150,450,100,40);
//        contact.setImagePath();

    }

}
