package io.khasang.pm.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SelectFromTable {
    private JdbcTemplate jdbcTemplate;

    public String getTableSelectionStatus() {
        jdbcTemplate.execute("select * from dogs;");
        return "data selected from table";
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
