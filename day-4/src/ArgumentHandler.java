import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class ArgumentHandler {

    public void handleArgument(String[] arguments) {
        if (arguments.length == 0) {
            System.out.println(getInstructions());
        } else {
            switch (arguments[0]) {
                case "-a" -> {
                    try {
                        System.out.println(addTask("\n" + arguments[1]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Unable to add: no task provided");
                    }
                }
                case "-l" -> {
                    System.out.println(getTasks());
                }
//                case "-r" -> {
//                    System.out.println(getTaskRemoved());
//                }
                default -> {
                    System.out.println("invalid arguments");
                }
            }
        }
    }

    private void getTaskRemoved() {
        List<String> currentList = readFromFile();


    }

    private List<String> readFromFile() {
        Path taskPath = Paths.get("tasks.txt");
        List<String> tasks = new ArrayList<>();

        try {
            tasks = Files.readAllLines(taskPath);
        } catch (IOException e) {
            System.out.println("Can't read file with tasks");
        }
        return tasks;
    }

    private String addTask(String argument) {
        writeToFile(argument);
        return argument;
    }

    private void writeToFile(String argument) {
        try {
            Files.write(Paths.get("tasks.txt"), argument.getBytes(), StandardOpenOption.APPEND);
        } catch (Exception e) {
            System.out.println("Can't add tasks");
        }
    }

    private String getTasks() {
        List<String> tasks = readFromFile();
        StringBuilder taskListAsString = new StringBuilder();

        int counter = 0;
        if (tasks.isEmpty()) {
            System.out.println("No todos for today! :)");
        } else for (String task : tasks) {
            counter++;
            System.out.println(counter + " - " + task);
        }
        return taskListAsString.toString();
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