import java.util.*;

public class taskThree{
    
    static class Book {
        private String id;
        private String title;
        private String author;
        private boolean isIssued;

        public Book(String id, String title, String author) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.isIssued = false;
        }

        public String getId() { return id; }
        public String getTitle() { return title; }
        public String getAuthor() { return author; }
        public boolean isIssued() { return isIssued; }

        public boolean issue() {
            if (!isIssued) {
                isIssued = true;
                return true;
            }
            return false;
        }

        public void returned() {
            isIssued = false;
        }

        @Override
        public String toString() {
            return String.format("Book[id=%s, title=%s, author=%s, issued=%s]",
                                 id, title, author, isIssued);
        }
    }


    static class User {
        private String userId;
        private String name;
        private List<Book> borrowedBooks;
        private static final int MAX_BORROW = 5;

        public User(String userId, String name) {
            this.userId = userId;
            this.name = name;
            this.borrowedBooks = new ArrayList<>();
        }

        public String getUserId() { return userId; }
        public String getName() { return name; }
        public List<Book> getBorrowedBooks() { return borrowedBooks; }

        public boolean borrowBook(Book book) {
            if (borrowedBooks.size() >= MAX_BORROW) {
                System.out.println("User " + name + " has reached maximum borrow limit.");
                return false;
            }
            if (book.issue()) {
                borrowedBooks.add(book);
                return true;
            } else {
                System.out.println("Book \"" + book.getTitle() + "\" is already issued.");
                return false;
            }
        }

        public boolean returnBook(Book book) {
            if (borrowedBooks.contains(book)) {
                borrowedBooks.remove(book);
                book.returned();
                return true;
            } else {
                System.out.println("User " + name + " did not borrow book \"" + book.getTitle() + "\".");
                return false;
            }
        }

        @Override
        public String toString() {
            return String.format("User[id=%s, name=%s, borrowedCount=%d]",
                                 userId, name, borrowedBooks.size());
        }
    }

    
    static class Library {
        private Map<String, Book> books;
        private Map<String, User> users;

        public Library() {
            books = new HashMap<>();
            users = new HashMap<>();
        }

        public void addBook(Book book) {
            books.put(book.getId(), book);
        }

        public void addUser(User user) {
            users.put(user.getUserId(), user);
        }

        public boolean issueBook(String bookId, String userId) {
            Book book = books.get(bookId);
            if (book == null) {
                System.out.println("Book with ID " + bookId + " not found.");
                return false;
            }
            User user = users.get(userId);
            if (user == null) {
                System.out.println("User with ID " + userId + " not found.");
                return false;
            }
            return user.borrowBook(book);
        }

        public boolean returnBook(String bookId, String userId) {
            Book book = books.get(bookId);
            if (book == null) {
                System.out.println("Book with ID " + bookId + " not found.");
                return false;
            }
            User user = users.get(userId);
            if (user == null) {
                System.out.println("User with ID " + userId + " not found.");
                return false;
            }
            return user.returnBook(book);
        }

        public void listAllBooks() {
            System.out.println("All books in library:");
            for (Book b : books.values()) {
                System.out.println(b);
            }
        }

        public void showUserBorrowed(String userId) {
            User u = users.get(userId);
            if (u == null) {
                System.out.println("No user with ID " + userId);
                return;
            }
            System.out.println("Books borrowed by " + u.getName() + ":");
            for (Book b : u.getBorrowedBooks()) {
                System.out.println(b);
            }
        }
    }

    
    public static void main(String[] args) {
        Library lib = new Library();

        
        lib.addBook(new Book("B001", "The Hobbit", "J.R.R. Tolkien"));
        lib.addBook(new Book("B002", "1984", "George Orwell"));
        lib.addBook(new Book("B003", "Clean Code", "Robert C. Martin"));
        lib.addBook(new Book("B004", "Effective Java", "Joshua Bloch"));

        
        lib.addUser(new User("U001", "Alice"));
        lib.addUser(new User("U002", "Bob"));

        System.out.println("=== Initial library ===");
        lib.listAllBooks();
        System.out.println();

        System.out.println("=== Issuing books ===");
        lib.issueBook("B001", "U001");
        lib.issueBook("B003", "U001");
        lib.issueBook("B003", "U002");  
        System.out.println();

        System.out.println("=== Borrowed by Alice ===");
        lib.showUserBorrowed("U001");
        System.out.println();

        System.out.println("=== Returning a book ===");
        lib.returnBook("B003", "U001");
        System.out.println();

        System.out.println("=== After return, borrowed by Alice ===");
        lib.showUserBorrowed("U001");
        System.out.println();

        System.out.println("=== Final library state ===");
        lib.listAllBooks();
    }
}
