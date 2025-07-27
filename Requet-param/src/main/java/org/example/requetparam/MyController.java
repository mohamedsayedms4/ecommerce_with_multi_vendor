package org.example.requetparam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class MyController {

    @GetMapping("/welcome")
    public String welcome() {
        return "✅ Welcome! You are authenticated.";
    }

    @GetMapping("/admin")
    public String admin() {
        return "🔒 This is Admin page. You must have proper role to see this.";
    }
}
