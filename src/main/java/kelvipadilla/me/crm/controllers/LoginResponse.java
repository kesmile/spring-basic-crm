package kelvipadilla.me.crm.controllers;

import lombok.Getter;
import lombok.Setter;

public class LoginResponse {
    @Getter
    @Setter
    private String token;
    @Getter
    @Setter
    private long expiresIn;

}
