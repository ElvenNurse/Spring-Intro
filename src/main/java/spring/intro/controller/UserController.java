package spring.intro.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.intro.dto.UserResponseDto;
import spring.intro.model.User;
import spring.intro.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/inject")
    public String inject() {
        for (int i = 1; i <= 4; i++) {
            User user = new User();
            user.setEmail(String.format("user%d@ukr.net", i));
            user.setPassword("123");
            userService.add(user);
        }
        return "Users injected";
    }

    @GetMapping("/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        User user = userService.get(id);
        return getDtoFromUser(user);
    }

    @GetMapping("/")
    public List<UserResponseDto> getAll() {
        List<UserResponseDto> usersResponseDto = new ArrayList<>();
        userService.listUsers().forEach(u -> usersResponseDto.add(getDtoFromUser(u)));
        return usersResponseDto;
    }

    private UserResponseDto getDtoFromUser(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setPassword(user.getPassword());
        return userResponseDto;
    }
}
