package tacos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * hello world
 *
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/12/22
 * @since 11
 */
@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
