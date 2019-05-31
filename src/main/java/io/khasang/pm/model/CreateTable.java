package io.khasang.pm.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CreateTable {
    private JdbcTemplate jdbcTemplate;

    public String getTableCreationStatus(){
        jdbcTemplate.execute("DROP TABLE IF EXISTS dogZ");
        jdbcTemplate.execute("create table dogZ\n" +
                "(\n" +
                "\tid int,\n" +
                "\tname VARCHAR(255)\n" +
                ");\n" +
                "\n" +
                "create unique index dogZ_id_uindex\n" +
                "\ton dogZ (id);");
        return "table was created!!!";
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
