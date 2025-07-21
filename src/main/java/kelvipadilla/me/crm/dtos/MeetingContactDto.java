package kelvipadilla.me.crm.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MeetingContactDto {
    @NotNull(message = "Meeting ID is required")
    private Integer meetingId;

    @NotNull(message = "Contact ID is required")
    private Integer contactId;
}
