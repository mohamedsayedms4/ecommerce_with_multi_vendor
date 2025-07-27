package org.example.springwithjbdc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatabaseHealthController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/db-health")
    public String checkDatabaseConnection() {
        try {
            Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            if (result != null && result == 1) {
                return "✅ Database connection is OK!";
            } else {
                return "⚠️ Connected but unexpected result.";
            }
        } catch (Exception e) {
            return "❌ Database connection failed: " + e.getMessage();
        }
    }
}
