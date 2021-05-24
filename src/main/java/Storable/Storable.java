package Storable;

import Contacts.Contact;

import java.io.File;

public interface Storable {

    Contact[] read (File source, Contact[] contacts) ;
    void write(File destination, Contact[] contacts) ;
}
