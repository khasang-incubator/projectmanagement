package io.khasang.pm.model;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CreateTable {
    private JdbcTemplate jdbcTemplate;

    public String getTableCreartionStatus() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS dogs");
        jdbcTemplate.execute("create table dogs(id serial not null, name varchar(255));\n" +
                "\n" +
                "create unique index dogs_id_uindex on dogs (id);\n" +
                "\n" +
                "alter table dogs add constraint dogs_pk primary key (id);\n");
        return "table created";
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
