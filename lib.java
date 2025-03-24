import java.io.*;
import java.util.*;

class Book implements Serializable {
    int id;
    String title;
    String author;

    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Author: " + author;
    }
}

public class LibraryManagement {
    private static final String FILE_NAME = "books.dat";

    public static void main(String[] args) {
        ArrayList<Book> books = loadBooks();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Book by ID");
            System.out.println("4. Remove Book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    books.add(new Book(id, title, author));
                    saveBooks(books);
                    System.out.println("Book added successfully!");
                    break;
                
                case 2:
                    System.out.println("\nList of Books:");
                    for (Book b : books) {
                        System.out.println(b);
                    }
                    break;
                
                case 3:
                    System.out.print("Enter Book ID to search: ");
                    int searchId = scanner.nextInt();
                    boolean found = false;
                    for (Book b : books) {
                        if (b.id == searchId) {
                            System.out.println("Book Found: " + b);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Book not found!");
                    }
                    break;
                
                case 4:
                    System.out.print("Enter Book ID to remove: ");
                    int removeId = scanner.nextInt();
                    books.removeIf(b -> b.id == removeId);
                    saveBooks(books);
                    System.out.println("Book removed successfully!");
                    break;
                
                case 5:
                    System.out.println("Exiting program...");
                    break;
                
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 5);
        
        scanner.close();
    }

    private static void saveBooks(ArrayList<Book> books) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(books);
        } catch (IOException e) {
            System.out.println("Error saving book data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static ArrayList<Book> loadBooks() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<Book>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading book data: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
