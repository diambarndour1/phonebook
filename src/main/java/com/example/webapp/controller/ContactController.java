package com.example.webapp.controller;

import com.example.webapp.repository.ContactRepository;
import com.example.webapp.model.Contact;
import com.example.webapp.service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class ContactController {

    private ContactRepository contactRepository;

    @GetMapping(path = "index")
    public String contact(Model model,
                          @RequestParam(name = "page", defaultValue = "0") int page,
                          @RequestParam(name = "size", defaultValue = "5") int size,
                          @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Contact> pageContacts = contactRepository.findByNomContains(keyword, PageRequest.of(page, size));
        model.addAttribute("listContacts", pageContacts.getContent());
        model.addAttribute("pages", new int[pageContacts.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "contacts";
    }

    @GetMapping("/delete")
    public String delete(Long id, String keyword, int page) {
        contactRepository.deleteById(id);
        return "redirect:/index?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/formContacts")
    public String formContacts(Model model) {
        model.addAttribute("contact", new Contact());
        return "formContacts";
    }

    @PostMapping(path = "/save")
    public String save(Model model, Contact contact,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String keyword) {
        contactRepository.save(contact);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/editContact")
    public String editContact(Model model, Long id, String keyword, int page) {
        Contact contact = contactRepository.findById(id).orElse(null);
        if(contact==null) throw new RuntimeException("Contact introuvable");
        model.addAttribute("contact", contact);
        model.addAttribute("page", page);
        model.addAttribute("keyword",keyword);
        return "editContact";
    }

 /* @GetMapping("/contacts")
    public List<Contact>listContacts(){
        return contactRepository.findAll();
    }*/

/*    //get all contacts
    @GetMapping("/repertoire")
    public List<Contact> getAllContact(Contact contact) {
        return contactRepository.findAll();
    }

   //get a contact by id
    @GetMapping("/repertoire/{id}")
    public Contact getContact(@PathVariable Long id) {
        return contactRepository.getById(id);
    }

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
