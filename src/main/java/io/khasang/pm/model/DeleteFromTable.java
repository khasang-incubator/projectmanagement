package io.khasang.pm.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DeleteFromTable {
    private JdbcTemplate jdbcTemplate;

    public String getTableDeletingStatus() {
        jdbcTemplate.execute("delete from colors where color='foxy'");
        return "record deleted from table";
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
