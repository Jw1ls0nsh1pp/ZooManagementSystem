import java.util.*;

// === Animal Class ===
class Animal {
    int animalID;
    String name;
    String type;
    int age;

    Animal(int id, String name, String type, int age) {
        this.animalID = id;
        this.name = name;
        this.type = type;
        this.age = age;
    }

    public void display() {
        System.out.println("Animal ID: " + animalID + ", Name: " + name + ", Type: " + type + ", Age: " + age);
    }
}

// === Visitor Class and Subclasses ===
abstract class Visitor {
    String name;
    int age;

    Visitor(String name, int age) {
        this.name = name;
        this.age = age;
    }

    abstract double getTicketPrice();
}

class ChildVisitor extends Visitor {
    ChildVisitor(String name, int age) { super(name, age); }

    double getTicketPrice() { return 5.0; }
}

class AdultVisitor extends Visitor {
    AdultVisitor(String name, int age) { super(name, age); }

    double getTicketPrice() { return 10.0; }
}

class SeniorVisitor extends Visitor {
    SeniorVisitor(String name, int age) { super(name, age); }

    double getTicketPrice() { return 7.0; }
}

// === Ticket System ===
class TicketSystem {
    static void issueTicket(Visitor v) {
        System.out.println("Ticket issued to " + v.name + " (Age: " + v.age + ") for $" + v.getTicketPrice());
    }
}

// === Zoo Management System Main Class ===
public class ZooManagementSystem {
    static Scanner sc = new Scanner(System.in);
    static List<Animal> animals = new ArrayList<>();
    static int animalCounter = 1;

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n=== Zoo Management System ===");
            System.out.println("1. Add Animal");
            System.out.println("2. Search Animal by Name");
            System.out.println("3. Remove Animal by ID");
            System.out.println("4. Issue Ticket to Visitor");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1 -> addAnimal();
                case 2 -> searchAnimal();
                case 3 -> removeAnimal();
                case 4 -> issueVisitorTicket();
                case 0 -> System.out.println("Exiting System.");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    static void addAnimal() {
        System.out.print("Enter animal name: ");
        String name = sc.nextLine();
        System.out.print("Enter animal type: ");
        String type = sc.nextLine();
        System.out.print("Enter animal age: ");
        int age = sc.nextInt(); sc.nextLine();

        Animal a = new Animal(animalCounter++, name, type, age);
        animals.add(a);
        System.out.println("Animal added successfully.");
    }

    static void searchAnimal() {
        System.out.print("Enter animal name to search: ");
        String name = sc.nextLine();
        boolean found = false;
        for (Animal a : animals) {
            if (a.name.equalsIgnoreCase(name)) {
                a.display();
                found = true;
            }
        }
        if (!found) System.out.println("Animal not found.");
    }

    static void removeAnimal() {
        System.out.print("Enter animal ID to remove: ");
        int id = sc.nextInt(); sc.nextLine();
        boolean removed = animals.removeIf(a -> a.animalID == id);
        if (removed) System.out.println("Animal removed.");
        else System.out.println("Animal ID not found.");
    }

    static void issueVisitorTicket() {
        System.out.print("Enter visitor name: ");
        String name = sc.nextLine();
        System.out.print("Enter visitor age: ");
        int age = sc.nextInt(); sc.nextLine();

        Visitor v;
        if (age < 13) v = new ChildVisitor(name, age);
        else if (age >= 60) v = new SeniorVisitor(name, age);
        else v = new AdultVisitor(name, age);

        TicketSystem.issueTicket(v);
    }
}
