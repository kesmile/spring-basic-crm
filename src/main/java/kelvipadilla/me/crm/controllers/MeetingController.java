package kelvipadilla.me.crm.controllers;

import jakarta.validation.Valid;
import kelvipadilla.me.crm.dtos.MeetingDto;
import kelvipadilla.me.crm.models.Meeting;
import kelvipadilla.me.crm.services.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meetings")
public class MeetingController {

    private final MeetingService meetingService;

    @Autowired
    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @GetMapping
    public Page<Meeting> getAllMeetings(Pageable pageable) {
        return meetingService.findAllMeetings(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meeting> getMeetingById(@PathVariable Integer id) {
        return meetingService.findMeetingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Meeting> createMeeting(@Valid @RequestBody MeetingDto meetingDto) {
        try {
            Meeting createdMeeting = meetingService.createMeeting(meetingDto);
            return new ResponseEntity<>(createdMeeting, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Meeting> updateMeeting(@PathVariable Integer id, @Valid @RequestBody MeetingDto meetingDto) {
        try {
            return meetingService.updateMeeting(id, meetingDto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeeting(@PathVariable Integer id) {
        boolean deleted = meetingService.deleteMeeting(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
