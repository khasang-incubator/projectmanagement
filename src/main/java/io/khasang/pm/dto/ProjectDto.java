package io.khasang.pm.dto;

import io.khasang.pm.entity.Book;
import io.khasang.pm.entity.Project;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProjectDto {

    private long id;

    private String name;
    private String description;
    private String time;
    private String result;

    private List<BookDto> bookDtoList = new ArrayList<>();

    public List<BookDto> getBookDtoList() {
        return bookDtoList;
    }

    public void setBookDtoList(List<BookDto> bookDtoList) {
        this.bookDtoList = bookDtoList;
    }

    public ProjectDto getProjectDto(Project project){
        List<BookDto> bookDtos = new ArrayList<>();
        ProjectDto projectDto = new ProjectDto();
        projectDto.setName(project.getName());
        projectDto.setId(project.getId());
        projectDto.setDescription(project.getDescription());

        getBookDtoFromBook(project, bookDtos);

        projectDto.setBookDtoList(bookDtos);
        return projectDto;
    }

    private void getBookDtoFromBook(Project project, List<BookDto> bookDtos) {
        //???
        for (Book book : project.getBookList()) {
            BookDto bookDto = new BookDto();
            bookDto.setId(book.getId());
            bookDto.setModel(book.getModel());
            bookDto.setYear(book.getYear());

            bookDtos.add(bookDto);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


}
