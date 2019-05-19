package io.khasang.pm.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class InsertIntoTable {
    private JdbcTemplate jdbcTemplate;

    public String getTableInsertionStatus() {
        jdbcTemplate.execute("insert into dogs(name, color_id) values ('Jack', 1) ;");
        jdbcTemplate.execute("insert into dogs(name, color_id) values ('Daniel', 2) ;");
        jdbcTemplate.execute("insert into dogs(name, color_id) values ('Sharic', 3) ;");
        jdbcTemplate.execute("insert into colors(color) values ('black') ;");
        jdbcTemplate.execute("insert into colors(color) values ('white') ;");
        jdbcTemplate.execute("insert into colors(color) values ('brown') ;");
        jdbcTemplate.execute("insert into colors(color) values ('foxy') ;");
        return "data inserted into table";
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}