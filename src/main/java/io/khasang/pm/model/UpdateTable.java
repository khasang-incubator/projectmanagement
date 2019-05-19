package io.khasang.pm.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UpdateTable {
    private JdbcTemplate jdbcTemplate;

    public String getTableUdtatingStatus() {
        jdbcTemplate.execute("update dogs set name = 'Chan' where name ='Daniel'");
        return "table updated";
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
