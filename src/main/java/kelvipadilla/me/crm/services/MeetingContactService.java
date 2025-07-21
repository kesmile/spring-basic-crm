package kelvipadilla.me.crm.services;

import kelvipadilla.me.crm.dtos.MeetingContactDto;
import kelvipadilla.me.crm.models.Contact;
import kelvipadilla.me.crm.models.Meeting;
import kelvipadilla.me.crm.models.MeetingContact;
import kelvipadilla.me.crm.models.MeetingContactId;
import kelvipadilla.me.crm.repositories.ContactRepository;
import kelvipadilla.me.crm.repositories.MeetingContactRepository;
import kelvipadilla.me.crm.repositories.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MeetingContactService {

    private final MeetingContactRepository meetingContactRepository;
    private final MeetingRepository meetingRepository;
    private final ContactRepository contactRepository;

    @Autowired
    public MeetingContactService(MeetingContactRepository meetingContactRepository,
                                MeetingRepository meetingRepository,
                                ContactRepository contactRepository) {
        this.meetingContactRepository = meetingContactRepository;
        this.meetingRepository = meetingRepository;
        this.contactRepository = contactRepository;
    }

    public List<MeetingContact> findAllMeetingContacts() {
        return meetingContactRepository.findAll();
    }

    public List<MeetingContact> findContactsByMeetingId(Integer meetingId) {
        return meetingContactRepository.findByMeetingId(meetingId);
    }

    public List<MeetingContact> findMeetingsByContactId(Integer contactId) {
        return meetingContactRepository.findByContactId(contactId);
    }

    public Optional<MeetingContact> findMeetingContactById(Integer meetingId, Integer contactId) {
        MeetingContactId id = new MeetingContactId(meetingId, contactId);
        return meetingContactRepository.findById(id);
    }

    public MeetingContact addContactToMeeting(MeetingContactDto meetingContactDto) {
        Meeting meeting = meetingRepository.findById(meetingContactDto.getMeetingId())
                .orElseThrow(() -> new RuntimeException("Meeting not found with id: " + meetingContactDto.getMeetingId()));

        Contact contact = contactRepository.findById(meetingContactDto.getContactId())
                .orElseThrow(() -> new RuntimeException("Contact not found with id: " + meetingContactDto.getContactId()));

        MeetingContact meetingContact = new MeetingContact(meeting, contact);
        return meetingContactRepository.save(meetingContact);
    }

    public boolean removeContactFromMeeting(Integer meetingId, Integer contactId) {
        MeetingContactId id = new MeetingContactId(meetingId, contactId);
        return meetingContactRepository.findById(id)
                .map(meetingContact -> {
                    meetingContactRepository.delete(meetingContact);
                    return true;
                }).orElse(false);
    }

    public void removeAllContactsFromMeeting(Integer meetingId) {
        meetingContactRepository.deleteByMeetingId(meetingId);
    }

    public void removeContactFromAllMeetings(Integer contactId) {
        meetingContactRepository.deleteByContactId(contactId);
    }
}
