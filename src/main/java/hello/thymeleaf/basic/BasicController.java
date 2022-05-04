package hello.thymeleaf.basic;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {
    @GetMapping("text-basic")
    public String textBasic(Model model) {
        model.addAttribute("data", "<b>hello spring</b>");
        return "basic/text-basic";
    }

    @GetMapping("text-unescaped")
    public String unescaped(Model model) {
        model.addAttribute("data", "<b>hello spring</b>");
        return "basic/text-unescaped";
    }

    @GetMapping("/variable")
    public String variable(Model model) {
        User userA = new User("userA", 10);
        User userB = new User("userB", 20);

        List<User> users = new ArrayList<>();
        users.add(userA);
        users.add(userB);

        Map<String, User> userMap = new HashMap<>();
        userMap.put("userA", userA);
        userMap.put("userB", userB);

        model.addAttribute("user", userA);
        model.addAttribute("users", users);
        model.addAttribute("userMap", userMap);

        return "basic/variable";
    }

    @GetMapping("/basic-objects")
    public String basicObjects(HttpSession session) {
        session.setAttribute("sessionData", "hello session");
        return "basic/basic-objects";
    }

    @GetMapping("/date")
    public String date(Model model) {
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "/basic/date";
    }

    @Component("helloBean")
    static class HelloBean {
        public String hello(String data) {
            return "hello" + data;
        }
    }

    @Data
    static class User {
        private final String username;
        private final int age;
    }

}
