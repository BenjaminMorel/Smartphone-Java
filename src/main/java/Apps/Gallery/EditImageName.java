package Apps.Gallery;

import Apps.Contacts.Contact;
import Errors.ErrorCode;
import Errors.ErrorPanel;
import Errors.SmartphoneException;
import Storable.JSONStorage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class EditImageName extends ImageGrand{

    private final String name;
    private final ArrayList<Images> images;
    private ArrayList<Contact> contacts;
    private final File jsonFile = new File(System.getenv("HOME") + "\\Contacts.json");
    private final JSONStorage storable = new JSONStorage();

    /**
     * Classe du constructeur qui extend de ImageGrand et reprend name et image
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

    /**
     * Donner une fonctionalité au bouton confirm
     * qui va appeler changerLeNom
     * et va cacher le bouton Confirmation et le text Field
     * et re-afficher le bouton edit et le nom de l'image
     */
    class BoutonConfirm implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buttonConfirmation) {
                try {
                    changerLeNom(name);
                } catch (SmartphoneException smartphoneException) {
                    System.out.println(smartphoneException.getErrorMessage());
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
     * methode qui met à jour le fichier json images et l'arraylist imagaes
     * @throws SmartphoneException, ajout des exceptions dans la méthode
     */
    public void saveImage() throws SmartphoneException {

        try {
            storable.writeImages(new File(System.getenv("HOME") + "\\Images.json"), images);
        } catch (SmartphoneException sm) {
            new ErrorPanel(sm.getErrorCode(), sm.getErrorMessage());
            throw new SmartphoneException("Pas trouvé le bon fichier", ErrorCode.BAD_PARAMETER);
        }
    }

    /**
     * changer le nom de l'image
     * parcourir la arraylist images, s'assurer qu'on est sur l'image séléctionnée
     * @throws SmartphoneException, ajout des exceptions Smartphone dans la methode
     */
    public void changerLeNom(String name) throws SmartphoneException
    {
        try {
            for (int i = 0; i < images.size(); i++) {                                                                   //parcourir toute la liste des images
                if (name.equals(images.get(i).getName()))                                                               //si le nom en parametre est le m'eme sur l'image cliqué
                {
                    if(textField.getText().equals(""))                                                                  //assurer l'erreur si rien est rentré
                    {
                        throw new SmartphoneException("Le texte ne peut pas être vide", ErrorCode.BAD_PARAMETER);
                    }
                    else
                    {
                        nomPhoto.setText(textField.getText().trim());                                                      //Trim pour enlever les espaces avant et après le nom
                        //créer fichier
                        File f = new File(System.getenv("HOME") + "\\" + images.get(i).getName());        //créer un fichier qui a le nom avec le path
                        f.renameTo(new File(System.getenv("HOME") + "\\ImagesGallery\\" + nomPhoto.getText() + ".png")); //renommer le fichier avec le nom rentré dans le texte field
                        images.get(i).setName("ImagesGallery/" + nomPhoto.getText() + ".png");
                    }
                }
            }
        }

        catch (SmartphoneException sm)
        {
            new ErrorPanel(sm.getErrorCode() ,sm.getErrorMessage());
            throw new SmartphoneException("Le nom est incorrect", ErrorCode.BAD_PARAMETER);
        }

        saveImage();

    }

    /**
     * Mettre à jour le fichier contacts, pour pouvoir afficher l'image
     * @throws SmartphoneException, ajout des exceptions à la méthode
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
     * methode pour write (mettre à jour) le fichier Json contact et arraylist contacts
     * @throws SmartphoneException, ajout des exceptions à la méthode
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
