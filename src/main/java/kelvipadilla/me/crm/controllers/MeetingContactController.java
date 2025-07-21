package kelvipadilla.me.crm.controllers;

import jakarta.validation.Valid;
import kelvipadilla.me.crm.dtos.MeetingContactDto;
import kelvipadilla.me.crm.models.MeetingContact;
import kelvipadilla.me.crm.services.MeetingContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meeting-contacts")
public class MeetingContactController {

    private final MeetingContactService meetingContactService;

    @Autowired
    public MeetingContactController(MeetingContactService meetingContactService) {
        this.meetingContactService = meetingContactService;
    }

    @GetMapping
    public List<MeetingContact> getAllMeetingContacts() {
        return meetingContactService.findAllMeetingContacts();
    }

    @GetMapping("/meeting/{meetingId}")
    public List<MeetingContact> getContactsByMeeting(@PathVariable Integer meetingId) {
        return meetingContactService.findContactsByMeetingId(meetingId);
    }

    @GetMapping("/contact/{contactId}")
    public List<MeetingContact> getMeetingsByContact(@PathVariable Integer contactId) {
        return meetingContactService.findMeetingsByContactId(contactId);
    }

    @GetMapping("/meeting/{meetingId}/contact/{contactId}")
    public ResponseEntity<MeetingContact> getMeetingContact(@PathVariable Integer meetingId,
                                                          @PathVariable Integer contactId) {
        return meetingContactService.findMeetingContactById(meetingId, contactId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MeetingContact> addContactToMeeting(@Valid @RequestBody MeetingContactDto meetingContactDto) {
        try {
            MeetingContact createdRelation = meetingContactService.addContactToMeeting(meetingContactDto);
            return new ResponseEntity<>(createdRelation, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/meeting/{meetingId}/contact/{contactId}")
    public ResponseEntity<Void> removeContactFromMeeting(@PathVariable Integer meetingId,
                                                        @PathVariable Integer contactId) {
        boolean removed = meetingContactService.removeContactFromMeeting(meetingId, contactId);
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/meeting/{meetingId}/contacts")
    public ResponseEntity<Void> removeAllContactsFromMeeting(@PathVariable Integer meetingId) {
        meetingContactService.removeAllContactsFromMeeting(meetingId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/contact/{contactId}/meetings")
    public ResponseEntity<Void> removeContactFromAllMeetings(@PathVariable Integer contactId) {
        meetingContactService.removeContactFromAllMeetings(contactId);
        return ResponseEntity.noContent().build();
    }
}
