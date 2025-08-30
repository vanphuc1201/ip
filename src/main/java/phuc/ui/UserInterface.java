package phuc.ui;

import phuc.model.Task;
import phuc.model.TaskList;
import phuc.storage.Storage;
import phuc.exception.PhucException;
import phuc.model.DeadlineTask;
import phuc.model.EventTask;
import phuc.model.ToDoTask;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class UserInterface {
    private static final String LINE = "____________________________________________________________";
    private final TaskList taskList;
    private Integer count = 0;
    private final Storage storage;

    public UserInterface() {
        this.taskList = new TaskList();
        this.storage = new Storage();
    }

    /**
     * Finds all the tasks contain keyword then print out to the user
     *
     * @param word a string keyword that need to find
     */
    public void find(String word) {
        ArrayList<Task> tasks = taskList.find(word);

        if (tasks.isEmpty()) {
            print("No tasks found for word: " + word);
        } else {
            StringBuilder temp = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                temp.append(i + 1).append(". ")
                        .append(tasks.get(i))
                        .append("\n");
            }

            System.out.println(LINE);
            System.out.print(temp);
            System.out.println(LINE);
        }
    }


    public void load() throws PhucException {
        try {
            taskList.clear();
            taskList.addAll(storage.load().getAllTasks());
            count = taskList.size();
        } catch (IOException e) {
            throw new PhucException("Failed to load tasks: " + e.getMessage());
        }
    }

    public void saveToStorage() throws PhucException {
        try {
            storage.save(taskList);
        } catch (IOException e) {
            throw new PhucException("Failed to save tasks: " + e.getMessage());
        }
    }

    public int numTasks() {
        return count+1;
    }

    public void print(String input) {
        System.out.println(LINE);
        System.out.println(input);
        System.out.println(LINE);
    }

    public void sayHello() {
        print(" Hello! I'm Phuc \uD83D\uDE03\uD83D\uDD90\uFE0F\n" +
                " What can I do for you?");
    }

    public void sayGoodbye() {
        print(" Bye. Hope to see you again soon! \uD83E\uDD27");
    }

    public void list() {
        String temp = "Here are the tasks in your list:\n";
        for(int i=0; i<count; i++) {
            temp += (i+1) + ". ";
            temp += taskList.get(i) + "\n";
        }

        System.out.println(LINE);
        System.out.print(temp);
        System.out.println(LINE);
    }

    public void mark(String num) {
        int id = Integer.parseInt(num)-1;
        taskList.get(id).setDone();
        print("Nice! I've marked this task as done:\n" + taskList.get(id));
    }

    public void unMark(String num) {
        int id = Integer.parseInt(num)-1;
        taskList.get(id).setNotDone();
        print("OK, I've marked this task as not done yet:\n" + taskList.get(id));
    }

    public String notiNumOfTasks() {
        return "Now you have " + (count+1) + " tasks in the list.";
    }

    public String notiAddTasks() {
        return "Got it. I've added this task:\n";
    }

    public void toDo(String newtask) {
        taskList.add(new ToDoTask(newtask));
        String temp = notiAddTasks() + taskList.get(count) + "\n" + this.notiNumOfTasks();
        print(temp);
        count++;
    }

    public void deadline(String newtask, LocalDateTime deadline) {
        taskList.add(new DeadlineTask(newtask, deadline));
        String temp = notiAddTasks() + taskList.get(count) + "\n" + this.notiNumOfTasks();
        print(temp);
        count++;
    }

    public void event(String newtask, LocalDateTime startDate, LocalDateTime endDate) {
        taskList.add(new EventTask(newtask, startDate, endDate));
        String temp = notiAddTasks() + taskList.get(count) + "\n" + this.notiNumOfTasks();
        print(temp);
        count++;
    }

    public void delete(String num) {
        count-=2;
        String temp = "Noted. I've removed this task:\n" +
                    taskList.get(Integer.parseInt(num)-1) + "\n" +
                    this.notiNumOfTasks();
        taskList.remove(Integer.parseInt(num)-1);
        print(temp);
        count++;
    }

    public static void showError(String error) {
        System.out.println(LINE);
        System.out.println(error);
        System.out.println(LINE);
    }
}
