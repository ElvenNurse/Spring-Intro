package spring.intro;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.intro.config.AppConfig;
import spring.intro.model.User;
import spring.intro.service.UserService;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

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

        userService.listUsers().forEach(System.out::println);
    }
}
