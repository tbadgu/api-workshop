package com.badgu.apiworkshop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Entity
@Schema(description = "Todo Item")
public class Todo {
    @Id
    @GeneratedValue
    @Schema(description = "ID", example = "1")
    private Long id;

    @NotBlank
    @Size(min = 1, max = 50)
    @Schema(description = "Creation User", example = "Tushar Badgu")
    private String createdBy;

    @NotBlank
    @Schema(description = "Todo Task Description", example = "Sample task description")
    private String description;

    @Schema(description = "Creation Time", example = "2020-07-23T19:30:15.632+00:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date createdOn = new Date();

    @Schema(description = "Todo Done Marker", example = "false")
    private boolean done = false;

    protected Todo() {
    }

    public Todo(String createdBy, String description) {
        this.createdBy = createdBy;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", createdBy='" + createdBy + '\'' +
                ", createdOn=" + createdOn +
                ", description='" + description + '\'' +
                ", done=" + done +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return done == todo.done &&
                Objects.equals(id, todo.id) &&
                Objects.equals(createdBy, todo.createdBy) &&
                Objects.equals(createdOn, todo.createdOn) &&
                Objects.equals(description, todo.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdBy, createdOn, description, done);
    }
}
