package com.example.webapp.service;

import com.example.webapp.model.Contact;
import com.example.webapp.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ContactService {


    @Autowired
    private ContactRepository contactRepository;

    //GET
    public List<Contact> getAllContact() {
        return contactRepository.findAll();
    }

    public Contact getContact(long id) {
        return contactRepository.findById(id).orElse(null);
    }

    //POST
    public Contact createContact(Contact contact) {
        return contactRepository.save(contact);

    }

    //DELETE
    public String deleteContact(long id) {
        contactRepository.deleteById(id);
        return "Contact supprimé"+id;
    }

    //DELETE
    public String deleteAllContacts() {
        contactRepository.deleteAll();
        return "Répertoire vide !!";
    }

    //PUT (modify)
    public Contact updateContact(Contact contact) {
        Contact exContact = contactRepository.findById(contact.getId()).orElse(null);
        exContact.setNom(contact.getNom());
        exContact.setPrenom(contact.getPrenom());
        exContact.setNumero(contact.getNumero());
        return contactRepository.save(exContact);
    }

}
