package kelvipadilla.me.crm.services;

import kelvipadilla.me.crm.dtos.MeetingDto;
import kelvipadilla.me.crm.models.Meeting;
import kelvipadilla.me.crm.models.Project;
import kelvipadilla.me.crm.repositories.MeetingRepository;
import kelvipadilla.me.crm.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MeetingService {

    private final MeetingRepository meetingRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public MeetingService(MeetingRepository meetingRepository, ProjectRepository projectRepository) {
        this.meetingRepository = meetingRepository;
        this.projectRepository = projectRepository;
    }

    public Page<Meeting> findAllMeetings(Pageable pageable) {
        return meetingRepository.findAll(pageable);
    }

    public Optional<Meeting> findMeetingById(Integer id) {
        return meetingRepository.findById(id);
    }

    public Meeting createMeeting(MeetingDto meetingDto) {
        Project project = projectRepository.findById(meetingDto.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + meetingDto.getProjectId()));

        Meeting newMeeting = new Meeting();
        newMeeting.setProject(project);
        newMeeting.setTitle(meetingDto.getTitle());
        newMeeting.setDate(meetingDto.getDate());
        newMeeting.setTime(meetingDto.getTime());
        newMeeting.setNotes(meetingDto.getNotes());
        return meetingRepository.save(newMeeting);
    }

    public Optional<Meeting> updateMeeting(Integer id, MeetingDto updatedMeetingDto) {
        return meetingRepository.findById(id)
                .map(meeting -> {
                    if (updatedMeetingDto.getProjectId() != null) {
                        Project project = projectRepository.findById(updatedMeetingDto.getProjectId())
                                .orElseThrow(() -> new RuntimeException("Project not found with id: " + updatedMeetingDto.getProjectId()));
                        meeting.setProject(project);
                    }
                    meeting.setTitle(updatedMeetingDto.getTitle());
                    meeting.setDate(updatedMeetingDto.getDate());
                    meeting.setTime(updatedMeetingDto.getTime());
                    meeting.setNotes(updatedMeetingDto.getNotes());
                    return meetingRepository.save(meeting);
                });
    }

    public boolean deleteMeeting(Integer id) {
        return meetingRepository.findById(id)
                .map(meeting -> {
                    meetingRepository.delete(meeting);
                    return true;
                }).orElse(false);
    }
}
