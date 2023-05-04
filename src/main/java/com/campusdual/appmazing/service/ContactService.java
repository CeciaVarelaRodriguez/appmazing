package com.campusdual.appmazing.service;

import com.campusdual.appmazing.api.IContactService;
import com.campusdual.appmazing.model.Contact;
import com.campusdual.appmazing.model.dao.ContactDao;
import com.campusdual.appmazing.model.dto.ContactDto;
import com.campusdual.appmazing.model.dto.dtomapper.ContactMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("ContactService")
@Lazy
public class ContactService implements IContactService {

    @Autowired
    private ContactDao contactDao;

    @Override
    public ContactDto queryContact(ContactDto contactDto) {
        Contact contact = ContactMapper.INSTANCE.toEntity(contactDto);
        return ContactMapper.INSTANCE.toDTO(contactDao.getReferenceById(contact.getId()));
    }

    @Override
    public List<ContactDto> queryAllContacts() {
        return ContactMapper.INSTANCE.toDTOList(contactDao.findAll());
    }

    @Override
    public int insertContact(ContactDto contactDto) {
        Contact contact = ContactMapper.INSTANCE.toEntity(contactDto);
        contactDao.saveAndFlush(contact);
        return contact.getId();
    }

    @Override
    public int updateContact(ContactDto contactDto) {
        return insertContact(contactDto);
    }

    @Override
    public int deleteContact(ContactDto contactDto) {
        Contact contact = ContactMapper.INSTANCE.toEntity(contactDto);
        contactDao.delete(contact);
        return contact.getId();
    }

    @Override
    public int secureUpdateContact(ContactDto newContact) {
        ContactDto oldContact = ContactMapper.INSTANCE.toDTO(contactDao.getReferenceById(newContact.getId()));
        if (oldContact != null) {
            if (newContact.getName() != null) {
                oldContact.setName(newContact.getName());
            }
            if (newContact.getSurname1() != null) {
                oldContact.setSurname1(newContact.getSurname1());
            }
            if (newContact.getSurname2() != null) {
                oldContact.setSurname2(newContact.getSurname2());
            }
            if (newContact.getPhone() != null) {
                oldContact.setPhone(newContact.getPhone());
            }
            if (newContact.getEmail() != null) {
                oldContact.setEmail(newContact.getEmail());
            }
            return updateContact(oldContact);
        }else{
            return -1;
        }
    }

}
