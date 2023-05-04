package com.campusdual.appmazing.controller;

import com.campusdual.appmazing.api.IContactService;
import com.campusdual.appmazing.model.Contact;
import com.campusdual.appmazing.model.dto.ContactDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private IContactService contactService;

    @GetMapping
    public String testController(){
        return "Contacts controller works!";
    }

    @PostMapping
    public String testController(@RequestBody String name){
        return "Contacts controller works, " + name + "!";
    }

    @GetMapping(value = "/testMethod")
    public String testControllerMethos(){
        return "Contact controller method works!";
    }

    @PostMapping(value = "/get")
    public ContactDto queryContact(@RequestBody ContactDto contactDto){
        return contactService.queryContact(contactDto);
    }

    @GetMapping(value = "/getAll")
    public List<ContactDto> queryAllContacts(){
        return contactService.queryAllContacts();
    }

    @PostMapping(value = "/add")
    public int addContact(@RequestBody ContactDto contactDto){
        return contactService.insertContact(contactDto);
    }

    @PostMapping(value = "/addAndShow")
    public ContactDto addContactAndShow(@RequestBody ContactDto contactDto){
        int idContact = contactService.insertContact(contactDto);
        ContactDto newContact = new ContactDto();
        newContact.setId(idContact);
        return contactService.queryContact(newContact);
    }

    @PutMapping(value = "/update")
    public int updateContact(@RequestBody ContactDto contactDto){
        return contactService.updateContact(contactDto);
    }

    //Hace un update solo de aquellos campos no vacíos
    @PutMapping(value = "/secureUpdate")
    public int secureUpdateContact(@RequestBody ContactDto contactoDto) {
        return contactService.secureUpdateContact(contactoDto);
    }

    @DeleteMapping(value = "/delete")
    public int deletecontact(@RequestBody ContactDto contactDto){
        return contactService.deleteContact(contactDto);
    }

}
