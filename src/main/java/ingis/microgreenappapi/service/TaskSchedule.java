package ingis.microgreenappapi.service;

import ingis.microgreenappapi.data.TaskRepository;
import ingis.microgreenappapi.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TaskSchedule {

    @Autowired
    private static  TaskRepository taskrepo;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    static LocalDateTime now = LocalDateTime.now();

    @Scheduled
    public static void TaskScheduler(int id, String dueDate, Boolean complete) {
        int currentTasks = Integer.parseInt(taskrepo.findById(id).get().getDueDate());
        if (now.minus. !((21) >= dueDate.parseInt || taskComplete != true)) {delete/task}


    }
}