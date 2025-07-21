// File: src/main/java/kelvipadilla/me/crm/models/MeetingContact.java
package kelvipadilla.me.crm.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "meeting_contacts")
public class MeetingContact {

    @EmbeddedId
    private MeetingContactId id;

    @ManyToOne
    @MapsId("meetingId")
    @JoinColumn(name = "meeting_id", nullable = false)
    private Meeting meeting;

    @ManyToOne
    @MapsId("contactId")
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact contact;

    public MeetingContact() {}

    public MeetingContact(Meeting meeting, Contact contact) {
        this.meeting = meeting;
        this.contact = contact;
        this.id = new MeetingContactId(meeting.getId(), contact.getId());
    }

    public MeetingContactId getId() {
        return id;
    }

    public void setId(MeetingContactId id) {
        this.id = id;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}