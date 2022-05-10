package ingis.microgreenappapi.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
public class Task {
    @Id
    @GeneratedValue
    private int id;
    private static int nextId = 1;

//    @NotBlank
    private String task;
    private boolean complete = Boolean.FALSE;

    public Task(String task, boolean complete) {
        this.task = task;
        this.complete = complete;
        this.id=nextId;
        nextId++;
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
