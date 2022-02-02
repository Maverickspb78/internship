import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    static int countId = 0;
    static Map<Integer,Task> taskMap = new HashMap<>();

    public static void main(String[] args) {

        final Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String command = scanner.next();
            switch (command) {
                case "add":
                    add(scanner);
                    break;
                case "print":
                    print(scanner);
                    break;
                case "search":
                    search(scanner);
                    break;
                case "toggle":
                    toggle(scanner);
                    break;
                case "delete":
                    delete(scanner);
                    break;
                case "edit":
                    edit(scanner);
                    break;
                case "quit":
                    return;
                default:
                    System.err.println("Введена не правильная команда");
                    break;
            }
        }
    }

    private static void add(Scanner scanner){
        String task = scanner.nextLine().trim();
        if (task.length() == 0){
            System.err.println("Введите корректное описание задачи");
            return;
        }
        countId++;
        taskMap.put(countId, new Task(task));
    }

    private static void print(Scanner scanner){
        String line = scanner.nextLine().trim();
        boolean all = line.equals("all");
        if (!all && line.length() > 0){
            System.err.println("Введен не верный аргумент");
            return;
        }
        if (taskMap.size() == 0){
            System.out.println("Список задач пуст");
            return;
        }
        Stream<Map.Entry<Integer, Task>> mapStream = taskMap.entrySet().stream();
        if (!all){
            mapStream.filter(a->!a.getValue().isDone()).forEach(Main::printTasks);
        } else {
            mapStream.forEach(Main::printTasks);
        }
    }

    private static void printTasks(Map.Entry<Integer, Task> entry){
        System.out.printf("%d. [%s] %s", entry.getKey(),
                entry.getValue().isDone() ? "X" : " ",
                entry.getValue().getDescription() + "\n");
    }

    private static void search(Scanner scanner){
        String search = scanner.nextLine().trim();
        if (search.length() == 0){
            System.err.println("введите хотя бы один символ для поиска");
            return;
        }
        Stream<Map.Entry<Integer, Task>> mapStream = taskMap.entrySet().stream();
        mapStream.filter(a -> a.getValue().getDescription().contains(search)).forEach(Main::printTasks);
    }

    private static void toggle(Scanner scanner){
        String toggleId = scanner.nextLine().trim();
        int id;
        try {
            id = Integer.parseInt(toggleId);
        } catch (NumberFormatException exception){
            printError(exception);
            return;
        }
        try {
            taskMap.get(id).setDone(!taskMap.get(id).isDone());
        } catch (NullPointerException exception){
            printError(exception);
        }
    }

    private static void delete(Scanner scanner){
        String deleteId = scanner.nextLine().trim();
        int id;
        try {
            id = Integer.parseInt(deleteId);
        } catch (NumberFormatException exception){
            printError(exception);
            return;
        }
        if (taskMap.containsKey(id)) {
                taskMap.remove(id);
        } else printError(new NullPointerException());
    }

    private static void edit(Scanner scanner) {
        String editId = scanner.next().trim();
        String editDescription = scanner.nextLine().trim();
        int id;
        try {
            id = Integer.parseInt(editId);
        } catch (NumberFormatException exception) {
            printError(exception);
            return;
        }
        if (editDescription.length() != 0) {
            if (taskMap.containsKey(id)) {
                Task task = taskMap.get(id);
                task.setDescription(editDescription);
                taskMap.replace(id, task);
            } else printError(new NullPointerException());
        } else {
            System.err.println("Введите корректное описание задачи");
        }
    }

    private static void printError(Exception exception){
        if (exception.getClass().getName().contains("NumberFormatException")){
            System.err.println("Не верный id");
        } else if (exception.getClass().getName().contains("NullPointerException")){
            System.err.println("Задачи с таким Id не существует");
        }
    }
}
