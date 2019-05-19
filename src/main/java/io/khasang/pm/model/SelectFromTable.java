package io.khasang.pm.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SelectFromTable {
    private JdbcTemplate jdbcTemplate;

    public String getTableSelectionStatus() {
        jdbcTemplate.execute("select d.name, c.color from dogs d, colors c where d.color_id=c.id;");
        return "data selected from table";
    }

    public String getTableSelectionStatusWithInnerJoin() {
        jdbcTemplate.execute("select d.name, c.color from dogs d inner join colors c on (d.color_id=c.id);");
        return "data selected from table with Inner Join ";
    }

    public String getTableSelectionStatusWithLeftJoin() {
        jdbcTemplate.execute("select d.name, c.color from dogs d left join colors c on (d.color_id=c.id);");
        return "data selected from table with Left Join";
    }

    public String getTableSelectionStatusWithRightJoin() {
        jdbcTemplate.execute("select d.name, c.color from dogs d right join colors c on (d.color_id=c.id);");
        return "data selected from table with Right Join";
    }

    public String getTableSelectionStatusWithFullJoin() {
        jdbcTemplate.execute("select d.name, c.color from dogs d full join colors c on (d.color_id=c.id);");
        return "data selected from table with Full Join";
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
