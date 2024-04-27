import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Factory factory = new Factory();

        System.out.println("Цехи:");
        factory.addDepartment("Производственный цех");
        factory.addDepartment("Химический цех");

        System.out.println("-----------------------------");

        System.out.println("Вы работаете?");
        char work = scanner.next().charAt(0);

        if (!factory.isWorking(work)) {
            System.out.println("Будешь работать?");
            char workOrNot = scanner.next().charAt(0);
            if (workOrNot == 'y') {
                System.out.println("Введите имя");
                String name = scanner.next();

                System.out.println("Кем будете работать?");
                String job = scanner.next();

                Employee employee = factory.addEmployee(name, job);
                System.out.println(employee.getName() + " | " + employee.getJob());
            }
        } else {
            System.out.println("Молодец");
            List<String> tools = factory.getTools();

            System.out.println("Посмотреть инструменты?");
            char watchTool = scanner.next().charAt(0);

            if (watchTool == 'y') {
                System.out.println("-----------------------------");
                for (String tool : tools) {
                    System.out.println(tool);
                }
                System.out.print("Выберите инструмент ");
                int index = scanner.nextInt();
                if (index < tools.size() && index >= 0) {
                    System.out.print("Сколько инструментов? ");
                    int amount = scanner.nextInt();
                    String toolsString = factory.getToolsString(amount, tools.get(index));
                    System.out.println(toolsString);

                    System.out.print("-----------------------------");
                    int totalTools = factory.getTotalTools(tools.size(), amount);
                    System.out.println("Общее количество инструментов: " + totalTools);
                }
            }
        }
    }
}

class Factory {
    private List<String> departments = new ArrayList<>();
    private List<Employee> employees = new ArrayList<>();
    private List<String> tools = new ArrayList<>();

    public void addDepartment(String department) {
        departments.add(department);
        System.out.println(department);
    }

    public boolean isWorking(char work) {
        return work == 'y';
    }

    public Employee addEmployee(String name, String job) {
        Employee employee = new Employee(name, job);
        employees.add(employee);
        return employee;
    }

    public List<String> getTools() {
        if (tools.isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Сколько инструментов добавить?");
            int amount = scanner.nextInt();

            System.out.println("Добавьте их в ваш арсенал.");
            for (int i = 0; i < amount; i++) {
                String tool = scanner.next();
                tools.add(tool);
            }
        }
        return tools;
    }

    public String getToolsString(int amount, String tool) {
        return amount + " " + tool;
    }

    public int getTotalTools(int currentToolsCount, int additionalToolsCount) {
        return currentToolsCount + additionalToolsCount;
    }
}

class Employee {
    private String name;

    private String job;

    public Employee(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }
}

