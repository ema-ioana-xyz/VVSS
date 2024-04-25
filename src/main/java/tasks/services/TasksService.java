package tasks.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;
import tasks.model.ArrayTaskList;
import tasks.model.Task;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;


public class TasksService {

    private ArrayTaskList tasks;
    private static final Logger log = Logger.getLogger(TasksService.class.getName());

    public TasksService(ArrayTaskList tasks){
        this.tasks = tasks;
    }

    public ObservableList<Task> getObservableList(){
        return FXCollections.observableArrayList(tasks.getAll());
    }
    public String getIntervalInHours(Task task){
        int seconds = task.getRepeatInterval();
        int minutes = seconds / DateService.SECONDS_IN_MINUTE;
        int hours = minutes / DateService.MINUTES_IN_HOUR;
        minutes = minutes % DateService.MINUTES_IN_HOUR;
        return formTimeUnit(hours) + ":" + formTimeUnit(minutes);//hh:MM
    }
    public String formTimeUnit(int timeUnit){
        StringBuilder sb = new StringBuilder();
        if (timeUnit < 10) sb.append("0");
        if (timeUnit == 0) sb.append("0");
        else {
            sb.append(timeUnit);
        }
        return sb.toString();
    }

    public int parseFromStringToSeconds(String stringTime){//hh:MM
        String[] units = stringTime.split(":");
        int hours = Integer.parseInt(units[0]);
        int minutes = Integer.parseInt(units[1]);
        int result = (hours * DateService.MINUTES_IN_HOUR + minutes) * DateService.SECONDS_IN_MINUTE;
        return result;
    }

    public Iterable<Task> filterTasks(ObservableList<Task> tasks, Date start, Date end){
        log.info("TaskService: filterTasks() called with parameters tasks=" + tasks + "start=" +start+" end="+end);
        ArrayList<Task> incomingTasks = new ArrayList<>();
        if (!end.before(start)){
            for (int i=0; i<tasks.size(); i++) {
                Task currentTask = tasks.get(i);
                Date nextTime = currentTask.nextTimeAfter(start);
                if (nextTime != null) {
                    if (nextTime.before(end) || nextTime.equals(end)) {
                        incomingTasks.add(currentTask);
                        log.info(currentTask.getTitle() + "with start=" + currentTask.getFormattedDateStart() + "selected");
                    }
                }
            }
        }
        return incomingTasks;
    }
    public Task addTask(Task task) throws IllegalArgumentException {
        tasks.add(task);

        return task;
    }
}
