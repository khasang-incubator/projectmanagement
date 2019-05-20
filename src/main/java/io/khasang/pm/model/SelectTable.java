package io.khasang.pm.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SelectTable {
    private JdbcTemplate jdbcTemplate;

    public String getTableSelectStatus(){
        jdbcTemplate.execute("select * from ships");
        jdbcTemplate.execute("insert into ships values (5,'supership',9)");
        jdbcTemplate.execute("update ships set level = level + 2 ");
        jdbcTemplate.execute("SELECT * FROM ships LEFT OUTER JOIN locations ON (ships.name = locations.name)");
        jdbcTemplate.execute("DELETE FROM ships WHERE level > 13");

        return "select worked";
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
