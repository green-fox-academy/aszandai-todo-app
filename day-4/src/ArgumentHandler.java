import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ArgumentHandler {

    public void handleArgument(String[] arguments) {
        if (arguments.length == 0) {
            System.out.println(getInstructions());
        } else {
            switch (arguments[0]) {
                case "-a": {
                    System.out.println(addTask());
                    break;
                }
                case "-l": {
                    System.out.println(getTasks());
                    break;
                }
//                case "-r": {
//                    System.out.println(arguments[1]);
//                }
                default: {
                    System.out.println("invalid arguments");
                    break;
                }
            }
        }
    }

    private String addTask() {
        String taskString = new String();
        Path taskPath = Paths.get("tasks.txt");
        try {
            Files.writeString(taskPath, taskString);
        } catch (IOException e) {
            System.out.println("Tasks are not available.");
        }
        return taskString;
    }

    private String getTasks() {
        Path taskPath = Paths.get("tasks.txt");
        List<String> tasks = new ArrayList<>();

        try {
            tasks = Files.readAllLines(taskPath);
        } catch (IOException e) {
            System.out.println("Tasks are not available.");
        }
        StringBuilder contentAsString = new StringBuilder();
        int counter = 0;
        if (tasks.isEmpty()) {
            System.out.println("No todos for today! :)");
        } else for (String task : tasks) {
            counter++;
            System.out.println(counter + " - " + task);
        }
        return contentAsString.toString();
    }

    private String getInstructions() {
        Path instructionPath = Paths.get("instructions");
        List<String> content = new ArrayList<>();
        try {
            content = Files.readAllLines(instructionPath);
        } catch (IOException e) {
            System.out.println("Instructions are not available.");
        }
        StringBuilder contentAsString = new StringBuilder();
        for (String line : content) {
            contentAsString.append(line);
            contentAsString.append("\n");
        }
        return contentAsString.toString();
    }

}