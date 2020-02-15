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
        User testUser1 = new User();
        testUser1.setEmail("test_user_1@deneg.net");
        testUser1.setPassword("qwerty");
        userService.add(testUser1);

        User testUser2 = new User();
        testUser2.setEmail("test_user_2@deneg.net");
        testUser2.setPassword("123456");
        userService.add(testUser2);

        User testUser3 = new User();
        testUser3.setEmail("test_user_3@deneg.net");
        testUser3.setPassword("password");
        userService.add(testUser3);

        User testUser4 = new User();
        testUser4.setEmail("test_user_4@deneg.net");
        testUser4.setPassword("123");
        userService.add(testUser4);

        return "Users injected";
    }

    @GetMapping("/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        UserResponseDto response = new UserResponseDto();
        User user = userService.get(id);
        response.setEmail(user.getEmail());
        response.setPassword(user.getPassword());
        return response;
    }

    @GetMapping("/")
    public List<UserResponseDto> getAll() {
        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        userService.listUsers().forEach(u -> {
            UserResponseDto response = new UserResponseDto();
            response.setEmail(u.getEmail());
            response.setPassword(u.getPassword());
            userResponseDtos.add(response);
        });
        return userResponseDtos;
    }
}
