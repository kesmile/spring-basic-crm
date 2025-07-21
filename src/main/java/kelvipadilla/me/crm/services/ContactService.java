package kelvipadilla.me.crm.services;

import kelvipadilla.me.crm.dtos.ContactDto;
import kelvipadilla.me.crm.models.Contact;
import kelvipadilla.me.crm.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Page<Contact> findAllContacts(Pageable pageable) {
        return contactRepository.findAll(pageable);
    }

    public Optional<Contact> findContactById(Integer id) {
        return contactRepository.findById(id);
    }

    public Contact createContact(ContactDto contact) {
        Contact newContact = new Contact();
        newContact.setName(contact.getName());
        newContact.setEmail(contact.getEmail());
        newContact.setPhone(contact.getPhone());
        return contactRepository.save(newContact);
    }

    public Optional<Contact> updateContact(Integer id, ContactDto updatedContact) {
        return contactRepository.findById(id)
                .map(contact -> {
                    contact.setName(updatedContact.getName());
                    contact.setEmail(updatedContact.getEmail());
                    contact.setPhone(updatedContact.getPhone());
                    return contactRepository.save(contact);
                });
    }

    public boolean deleteContact(Integer id) {
        return contactRepository.findById(id)
                .map(contact -> {
                    contactRepository.delete(contact);
                    return true;
                }).orElse(false);
    }
}