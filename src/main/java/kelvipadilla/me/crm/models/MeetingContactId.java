// File: src/main/java/kelvipadilla/me/crm/models/MeetingContactId.java
package kelvipadilla.me.crm.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MeetingContactId implements Serializable {

    @Column(name = "meeting_id")
    private Integer meetingId;

    @Column(name = "contact_id")
    private Integer contactId;

    public MeetingContactId() {}

    public MeetingContactId(Integer meetingId, Integer contactId) {
        this.meetingId = meetingId;
        this.contactId = contactId;
    }

    public Integer getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Integer meetingId) {
        this.meetingId = meetingId;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MeetingContactId)) return false;
        MeetingContactId that = (MeetingContactId) o;
        return Objects.equals(meetingId, that.meetingId) &&
               Objects.equals(contactId, that.contactId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meetingId, contactId);
    }
}