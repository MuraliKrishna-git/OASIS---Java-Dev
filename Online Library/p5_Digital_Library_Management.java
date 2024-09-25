import java.util.*;

class Book {
    String title;
    String author;
    boolean isIssued;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    @Override
    public String toString() {
        return title + " by " + author + (isIssued ? " (Issued)" : " (Available)");
    }
}

class User {
    String name;
    String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

class Library {
    private List<Book> books = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<String> queries = new ArrayList<>();

    public void addBook(String title, String author) {
        books.add(new Book(title, author));
        System.out.println("Book added: " + title);
    }

    public void removeBook(String title) {
        books.removeIf(book -> book.title.equalsIgnoreCase(title));
        System.out.println("Book removed: " + title);
    }

    public void viewBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void issueBook(String title) {
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title) && !book.isIssued) {
                book.isIssued = true;
                System.out.println("Book issued: " + title);
                return;
            }
        }
        System.out.println("Book not available for issuing: " + title);
    }

    public void returnBook(String title) {
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title) && book.isIssued) {
                book.isIssued = false;
                System.out.println("Book returned: " + title);
                return;
            }
        }
        System.out.println("Book not found: " + title);
    }

    public void addUser(String name, String email) {
        users.add(new User(name, email));
        System.out.println("User added: " + name);
    }

    public void query(String message) {
        queries.add(message); // Store user queries
        System.out.println("Query received: " + message);
    }

    public void viewQueries() {
        System.out.println("User Queries:");
        for (String query : queries) {
            System.out.println("- " + query);
        }
    }
}

public class p5_Digital_Library_Management {
    private static Library library = new Library();
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "password";
    private static final String USER_USERNAME = "user";
    private static final String USER_PASSWORD = "password";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to the Library Management System");
            System.out.println("1. Login as Admin");
            System.out.println("2. Login as User");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    if (adminLogin(scanner)) {
                        adminMenu(scanner);
                    }else
                        System.out.println("Sorry, wrong login credentials!");
                    break;
                case 2:
                    if (userLogin(scanner)) {
                        userMenu(scanner);
                    }else
                        System.out.println("Sorry, wrong login credentials!");
                    break;
                case 3:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    static {
        library.addBook("Books and Bakes", "Lume John");
        library.addBook("Holand Princess", "James Jayroy");
        library.addBook("Walking Truth", "Kortarala Siva");
    }

    private static boolean adminLogin(Scanner scanner) {
        System.out.print("Enter Admin Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Admin Password: ");
        String password = scanner.nextLine();
        return username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD);
    }

    private static boolean userLogin(Scanner scanner) {
        System.out.print("Enter User Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter User Password: ");
        String password = scanner.nextLine();
        return username.equals(USER_USERNAME) && password.equals(USER_PASSWORD);
    }

    private static void adminMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. View Books");
            System.out.println("4. View User Queries");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    library.addBook(title, author);
                    break;
                case 2:
                    System.out.print("Enter book title to remove: ");
                    title = scanner.nextLine();
                    library.removeBook(title);
                    break;
                case 3:
                    library.viewBooks();
                    break;
                case 4:
                    library.viewQueries();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void userMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nUser Menu:");
            System.out.println("1. View Books");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. Query");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    library.viewBooks();
                    break;
                case 2:
                    System.out.print("Enter book title to issue: ");
                    String title = scanner.nextLine();
                    library.issueBook(title);
                    break;
                case 3:
                    System.out.print("Enter book title to return: ");
                    title = scanner.nextLine();
                    library.returnBook(title);
                    break;
                case 4:
                    System.out.print("Enter your query: ");
                    String message = scanner.nextLine();
                    library.query(message);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
