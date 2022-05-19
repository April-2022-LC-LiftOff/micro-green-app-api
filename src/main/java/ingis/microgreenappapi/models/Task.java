package ingis.microgreenappapi.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
public class Task {

    @Id
    @GeneratedValue
    private int id;

//    @NotBlank
    private String task;
    private boolean complete = Boolean.FALSE;
    private LocalDate dueDate;

    public Task(String task, boolean complete, LocalDate dueDate) {
        this.task = task;
        this.complete = complete;
        this.dueDate = dueDate;
    }

    public Task() {}

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task that = (Task) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Task{" +
                "task='" + task + '\'' +
                '}';
    }

}
