package dev.james.restfulwebservice.dto;


public class PostDto {
    private int id;

    private String description;

    public PostDto(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public PostDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
