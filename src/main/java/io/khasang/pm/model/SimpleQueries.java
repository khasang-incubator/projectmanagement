package io.khasang.pm.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SimpleQueries implements Queries{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String getTableCreationStatus(){
        this.tableCreation();
        return "table created";
    }

    private void tableCreation(){
        jdbcTemplate.execute("DROP TABLE IF EXISTS dogs");
        jdbcTemplate.execute("create table dogs\n" +
                "(\n" +
                "\tid int not null,\n" +
                "\tname varchar(255)\n" +
                ");\n" +
                "\n" +
                "create unique index dogs_id_uindex\n" +
                "\ton dogs (id);\n");
    }

    @Override
    public String getSelectionStatus() {
        String sql = "select * from pm.public.cats";
        //sql="select * from cats inner join colors on (cats.color_id=colors.id)";
        this.jdbcTemplate.execute(sql);
        return "selected";
    }

    @Override
    public String insertData() {
        String sql = "insert into pm.public.dogs  (id, name) values  (1, 'Тузик')";
        this.tableCreation();
        this.jdbcTemplate.execute(sql);
        return "inserted";
    }

    @Override
    public String updateData() {
        String sql = "update pm.public.dogs set name='Жучка' where id = 1";
        this.jdbcTemplate.execute(sql);
        return "udated";
    }
}
