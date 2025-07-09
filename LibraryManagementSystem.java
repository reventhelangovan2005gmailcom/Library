import java.util.*;

class Book {
    String bookId;
    String title;
    String author;
    String status; // "Available" or "Issued"
    String issuedTo; // Member name

    public Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.status = "Available";
        this.issuedTo = "";
    }

    @Override
    public String toString() {
        return bookId + " | " + title + " | " + author + " | " + status + 
               (status.equals("Issued") ? " to " + issuedTo : "");
    }
}

public class LibraryManagementSystem {
    static Map<String, Book> books = new HashMap<>();
    static final int MAX_BOOKS = 1000;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

           switch (choice) {
    case 1:
        addBook();
        break;
    case 2:
        issueBook();
        break;
    case 3:
        returnBook();
        break;
    case 4:
        viewBooks("Available");
        break;
    case 5:
        viewBooks("Issued");
        break;
    case 6:
        System.out.println("Exiting the system. Goodbye!");
        break;
    default:
        System.out.println("Invalid choice! Please try again.");
}

        } while (choice != 6);
    }

    static void displayMenu() {
        System.out.println("\nLibrary Management System");
        System.out.println("1. Add Book");
        System.out.println("2. Issue Book");
        System.out.println("3. Return Book");
        System.out.println("4. View Available Books");
        System.out.println("5. View Issued Books");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    static void addBook() {
        if (books.size() >= MAX_BOOKS) {
            System.out.println("Book limit reached. Cannot add more books.");
            return;
        }

        System.out.print("Enter Book ID (no spaces): ");
        String id = scanner.next();
        if (books.containsKey(id)) {
            System.out.println("Book ID already exists. Use a unique ID.");
            return;
        }

        scanner.nextLine(); // Consume leftover newline
        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Author Name: ");
        String author = scanner.nextLine();

        Book newBook = new Book(id, title, author);
        books.put(id, newBook);
        System.out.println("Book added successfully.");
    }

    static void issueBook() {
        System.out.print("Enter Book ID to issue: ");
        String id = scanner.next();

        if (!books.containsKey(id)) {
            System.out.println("Book ID does not exist.");
            return;
        }

        Book book = books.get(id);
        if (book.status.equals("Issued")) {
            System.out.println("Book is already issued.");
            return;
        }

        System.out.print("Enter Member Name: ");
        String member = scanner.next();
        book.status = "Issued";
        book.issuedTo = member;
        System.out.println("Book issued successfully to " + member + ".");
    }

    static void returnBook() {
        System.out.print("Enter Book ID to return: ");
        String id = scanner.next();

        if (!books.containsKey(id)) {
            System.out.println("Book ID does not exist.");
            return;
        }

        Book book = books.get(id);
        if (book.status.equals("Available")) {
            System.out.println("Book is already available in the library.");
            return;
        }

        book.status = "Available";
        book.issuedTo = "";
        System.out.println("Book returned successfully.");
    }

    static void viewBooks(String statusFilter) {
        System.out.println("\nBook ID | Title | Author | Status");
        boolean found = false;
        for (Book book : books.values()) {
            if (book.status.equals(statusFilter)) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No " + statusFilter.toLowerCase() + " books found.");
        }
    }
}
