package io.khasang.pm.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CreateTable {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getTableCreationStatus(){
        jdbcTemplate.execute("DROP TABLE IF EXISTS dogs");
        jdbcTemplate.execute("create table dogs\n" +
                "(\n" +
                "\tid int not null,\n" +
                "\tname varchar(255)\n" +
                ");\n" +
                "\n" +
                "create unique index dogs_id_uindex\n" +
                "\ton dogs (id);\n");
        return "table created";
    }
}
