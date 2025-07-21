package kelvipadilla.me.crm.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClientDto {
    @NotEmpty(message = "Name is required")
    private String name;

    @Email(message = "Invalid email format")
    private String email;

    private String phone;

    private String address;
}
