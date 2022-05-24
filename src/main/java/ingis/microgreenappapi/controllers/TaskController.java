package ingis.microgreenappapi.controllers;

import ingis.microgreenappapi.data.SeedRepository;
import ingis.microgreenappapi.data.TaskRepository;
import ingis.microgreenappapi.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskRepository taskRepo;

    @Autowired
    private SeedRepository seedRepo;



    public ArrayList<Object> todaysTasks = new ArrayList<>();
    private String dailyTasks;


// view all tasks
    @GetMapping
    public List<Task> findAll() {
        return taskRepo.findAll();
    }

    // view today's tasks
    @GetMapping("/{today}")
    public ArrayList todayTasks(@PathVariable String today) {
    //iterate through task
        LocalDate dueDate = LocalDate.parse(today);
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




