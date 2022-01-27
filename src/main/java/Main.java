import java.util.Scanner;

public class Main {

    static String description = null;
    static boolean done;
    static final int ID = 1;

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
                case "toggle":
                    toggle(scanner);
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
        description = task;
        done = false;
    }

    private static void print(Scanner scanner){
        String line = scanner.nextLine().trim();
        boolean all = line.equals("all");
        if (!all && line.length() > 0){
            System.err.println("Введен не верный аргумент");
            return;
        }
        if (description == null){
            System.out.println("Список задач пуст");
            return;
        }
        if (!done || all){
            System.out.printf("%d. [%s] %s", ID, done ? "X" : " ", description + "\n");
        }
        if (description != null && done){
            System.err.println("Нет не выполненых задач");
        }
    }

    private static void toggle(Scanner scanner){

        String toggleId = scanner.nextLine().trim();
        int id;
        try {
            id = Integer.parseInt(toggleId);
        } catch (NumberFormatException exception){
            System.err.println("Не верный id");
            return;
        }
        if (description == null && ID != id) {
            System.err.println("Задачи с таким id не существует");
            return;
        }
        done = !done;
    }
}
