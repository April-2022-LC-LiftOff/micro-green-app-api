package ingis.microgreenappapi.controllers;

import ingis.microgreenappapi.data.TaskRepository;
import ingis.microgreenappapi.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskRepository taskRepo;

    public ArrayList<Object> todaysTasks = new ArrayList<>();
    private String dailyTasks;

// view all tasks
    @GetMapping
    public List<Task> findAll() {
        return taskRepo.findAll();

    }
    // view today's tasks
    @GetMapping("/today/{dueDate}")
    public ArrayList todayTasks(@PathVariable String dueDate) {
    //iterate through task
        todaysTasks.clear();
        for (int i = 0; i < taskRepo.count(); i++) {
            if(taskRepo.findAll().get(i).getDueDate().equals(dueDate)) {
                Object task = taskRepo.findAll().get(i);
                todaysTasks.add(task);
            }
        }
        return todaysTasks;
    }

// add a task
    @PostMapping(value = "add")
    public String addTask(@RequestBody Task task) {
        taskRepo.save(task);
        return "Saved....";
    }

// delete a task

    @DeleteMapping(value = "/delete/{id}")
    public String deleteTask(@PathVariable Integer id) {
        Task deletedTask = taskRepo.findById(id).get();
        taskRepo.delete(deletedTask);
        return "deleted...";
    }



}
