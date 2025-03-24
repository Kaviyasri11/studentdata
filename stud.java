import java.util.ArrayList;
import java.util.Scanner;

class Student {
    int id;
    String name;
    int age;

    Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age;
    }
}

public class StudentManagement {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int choice;
        
        do {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Age: ");
                    int age = scanner.nextInt();
                    students.add(new Student(id, name, age));
                    System.out.println("Student added successfully!");
                    break;
                
                case 2:
                    System.out.println("\nList of Students:");
                    for (Student s : students) {
                        System.out.println(s);
                    }
                    break;
                
                case 3:
                    System.out.print("Enter ID to search: ");
                    int searchId = scanner.nextInt();
                    boolean found = false;
                    for (Student s : students) {
                        if (s.id == searchId) {
                            System.out.println("Student Found: " + s);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Student not found!");
                    }
                    break;
                
                case 4:
                    System.out.println("Exiting program...");
                    break;
                
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 4);
        
        scanner.close();
    }
}
