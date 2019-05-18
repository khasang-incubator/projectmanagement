package io.khasang.pm.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class InsertIntoTable {
    private JdbcTemplate jdbcTemplate;

    public String getTableInsertionStatus() {
        jdbcTemplate.execute("insert into dogs(name) values ('Jack') ;");
        jdbcTemplate.execute("insert into dogs(name) values ('Daniel') ;");
        jdbcTemplate.execute("insert into dogs(name) values ('Sharic') ;");
        return "data inserted into table";
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}