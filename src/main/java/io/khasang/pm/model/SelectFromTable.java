package io.khasang.pm.model;

import io.khasang.pm.controller.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class SelectFromTable {
    private JdbcTemplate jdbcTemplate;

    public List<String> getTableSelectionStatus() {
//        jdbcTemplate.execute("select d.name, c.color from dogs d, colors c where d.color_id=c.id;");
        List<String> list = jdbcTemplate.query("select d.name, c.color from dogs d, colors c where d.color_id=c.id;", new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(rs.getString("name"));
                stringBuffer.append(" is ");
                stringBuffer.append(rs.getString("color"));
                return stringBuffer.toString();
            }
        });
        return list;//"data selected from table";
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
