package io.khasang.pm.model;

import io.khasang.pm.entity.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;

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
        String sql = "select * from old_cats inner join colors on (old_cats.color_id=colors.id)";
        List<Cat> packOfCats = this.jdbcTemplate.query(sql,(ResultSet rs, int rowNum)->{
            Cat cat = new Cat();
            cat.setName(rs.getString("name"));
            //cat.setColor(rs.getString("color"));
            return cat;
        });
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

    @Override
    public String deleteData() {
        String sql = "delete from pm.public.dogs where id = 1";
        this.jdbcTemplate.execute(sql);
        return "deleted";
    }
}
