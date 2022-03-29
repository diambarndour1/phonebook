package com.example.webapp.controller;

import com.example.webapp.repository.ContactRepository;
import com.example.webapp.model.Contact;
import com.example.webapp.service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class ContactController {

    private ContactRepository contactRepository;
    @GetMapping(path="index")
    public String contact(Model model){

        List<Contact> contacts=contactRepository.findAll();
        model.addAttribute("listContacts",contacts);
        return "contacts";
    }

    //get all contacts
    @GetMapping("/repertoire")
    public List<Contact> getAllContact(Contact contact) {
        return contactRepository.findAll();
    }

   //get a contact by id
    @GetMapping("/repertoire/{id}")
    public Contact getContact(@PathVariable Long id) {
        return contactRepository.getById(id);
    }
/*
    //create a contact
    @PostMapping("/new")
    public Contact createContact(@RequestBody Contact contact) {
        return contactService.createContact(contact);
    }

    @DeleteMapping("/clear")
    public void vider() {
        contactService.deleteAllContacts();
    }

    @DeleteMapping("/supprimer/{id}")
    public void viderById(@PathVariable long id) {
        contactService.deleteContact(id);
    }

    @PutMapping("/update")
    public Contact updateContact(@RequestBody Contact contact) {
        return contactService.updateContact(contact);
    }
*/

}
