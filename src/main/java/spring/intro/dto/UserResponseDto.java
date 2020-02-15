package spring.intro.dto;

import lombok.Data;
import spring.intro.model.User;

@Data
public class UserResponseDto {
    private String email;
    private String password;

    public UserResponseDto(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}
