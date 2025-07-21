package kelvipadilla.me.crm.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProjectDto {
    @NotNull(message = "Client ID is required")
    private Integer clientId;

    @NotEmpty(message = "Name is required")
    private String name;

    private String description;

    private String status;
}
