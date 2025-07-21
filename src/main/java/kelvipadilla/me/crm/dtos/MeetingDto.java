package kelvipadilla.me.crm.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
public class MeetingDto {
    @NotNull(message = "Project ID is required")
    private Integer projectId;

    @NotEmpty(message = "Title is required")
    private String title;

    private LocalDate date;

    private LocalTime time;

    private String notes;
}
