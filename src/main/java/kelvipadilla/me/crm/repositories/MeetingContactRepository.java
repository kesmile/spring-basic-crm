package kelvipadilla.me.crm.repositories;

import kelvipadilla.me.crm.models.MeetingContact;
import kelvipadilla.me.crm.models.MeetingContactId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingContactRepository extends JpaRepository<MeetingContact, MeetingContactId> {

    @Query("SELECT mc FROM MeetingContact mc WHERE mc.meeting.id = :meetingId")
    List<MeetingContact> findByMeetingId(@Param("meetingId") Integer meetingId);

    @Query("SELECT mc FROM MeetingContact mc WHERE mc.contact.id = :contactId")
    List<MeetingContact> findByContactId(@Param("contactId") Integer contactId);

    @Query("DELETE FROM MeetingContact mc WHERE mc.meeting.id = :meetingId")
    void deleteByMeetingId(@Param("meetingId") Integer meetingId);

    @Query("DELETE FROM MeetingContact mc WHERE mc.contact.id = :contactId")
    void deleteByContactId(@Param("contactId") Integer contactId);
}
