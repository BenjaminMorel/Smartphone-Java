package Apps.Gallery;

import Apps.Contacts.Contact;
import Errors.ErrorCode;
import Errors.SmartphoneException;
import Storable.JSONStorage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class EditImageName extends ImageGrand{

    private String name;
    private ArrayList<Images> images;                                                                                   //liste de images
    private ArrayList<Contact> contacts;
    private final File jsonFile = new File(System.getenv("HOME") + "\\Contacts.json");
    private final JSONStorage storable = new JSONStorage();

    /**
     * Classe du constructeur qui a en parametre
     *
     * @param name   String name, qui contient le path de l'image
     * @param images Array list avec toutes les images
     */
    public EditImageName(String name, ArrayList<Images> images) {
        super(name, images);

        this.images = images;
        this.name = name;

        buttonConfirmation.addActionListener(new BoutonConfirm());

        textField = new JTextField();
        textField.setBounds(90, 20, 100, 25);

        buttonEdit.setVisible(false);
        nomPhoto.setVisible(false);

        add(textField);
        add(buttonConfirmation);

        buttonConfirmation.setBounds(190, 20, 75, 25);
        buttonConfirmation.addActionListener(new BoutonConfirm());

        loadContact();
        loadImage();

    }

    class BoutonConfirm implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buttonConfirmation) {
                try {
                    changerLeNom();
                } catch (SmartphoneException smartphoneException) {
                    smartphoneException.printStackTrace();
                }

                buttonConfirmation.setVisible(false);
                textField.setVisible(false);

                buttonEdit.setVisible(true);
                nomPhoto.setVisible(true);

                try {
                    changerNomImageContact();
                } catch (SmartphoneException smartphoneException) {
                    smartphoneException.printStackTrace();
                }

            }
        }
    }

    /**
     * methode qui enregistre l'arraylist images, et lecture dans le fichier json
     */
    public void saveImage()
    {
        try {
            storable.writeImages(new File(System.getenv("HOME") + "\\Images.json"), images);
        } catch (SmartphoneException sm) {
            System.out.println(sm.getErrorMessage());
            System.out.println(sm.getErrorCode());
        }
    }

    /**
     *
     * @throws SmartphoneException
     */
    public void changerLeNom() throws SmartphoneException
    {
        for (int i = 0; i < images.size(); i++) {                                                               //parcourir toute la liste des images
            if (name.equals(images.get(i).getName()))    //regarder adresse                                           //si le nom en parametre est le m'eme sur l'image cliqué
            {
                nomPhoto.setText(textField.getText());

                //créer fichjeir
                try
                {
                    File f = new File(System.getenv("HOME") + "\\" + images.get(i).getName());
                    System.out.println(f);
                    f.renameTo(new File(System.getenv("HOME") + "\\ImagesGallery\\" + nomPhoto.getText() + ".png"));
                    System.out.println(f);
                    images.get(i).setName("ImagesGallery/" + nomPhoto.getText() + ".png");
                    saveImage();
                    break;
                }
                catch (NullPointerException npe)
                {
                    throw new SmartphoneException("Pas trouvé le bon fichier", ErrorCode.BAD_PARAMETER);
                }
            }
        }
    }

    /**
     *
     * @throws SmartphoneException
     */
    public void changerNomImageContact() throws SmartphoneException {

        for (int i = 0; i < contacts.size(); i++) {
            if(contacts.get(i).getImagePath().equals(name))
            {
                contacts.get(i).setImagePath("ImagesGallery/" + nomPhoto.getText() + ".png");

            }
        }
        saveContact();
    }


    /**
     * methode qui charge l'arraylist contacts, et lecture dans le fichier json
     */
    public void loadContact()
    {
        try {
            contacts = storable.read(new File(System.getenv("HOME") + "\\Contacts.json"), contacts);
        } catch (SmartphoneException sm) {
            System.out.println(sm.getErrorMessage());
            System.out.println(sm.getErrorCode());
        }
    }

    /**
     *
     * @throws SmartphoneException
     */
    public void saveContact() throws SmartphoneException
    {
        try
        {
            storable.write(jsonFile, contacts);
        } catch (SmartphoneException e)
        {
            throw new SmartphoneException(e.getErrorMessage(), ErrorCode.BAD_PARAMETER);
        }
    }

}
