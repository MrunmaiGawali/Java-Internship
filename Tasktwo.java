import java.util.*;

public class Tasktwo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        boolean exit = false;
        while (!exit) {
            printMenu();
            int choice = readInt(scanner, "Enter your choice: ");
            switch (choice) {
                case 1:
                    handleAdd(scanner, manager);
                    break;
                case 2:
                    handleList(manager);
                    break;
                case 3:
                    handleUpdate(scanner, manager);
                    break;
                case 4:
                    handleDelete(scanner, manager);
                    break;
                case 5:
                    handleViewOne(scanner, manager);
                    break;
                case 0:
                    exit = true;
                    System.out.println("Exiting. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n=== Student Management System ===");
        System.out.println("1. Add student");
        System.out.println("2. List all students");
        System.out.println("3. Update student");
        System.out.println("4. Delete student");
        System.out.println("5. View student by ID");
        System.out.println("0. Exit");
    }

    private static void handleAdd(Scanner scanner, StudentManager manager) {
        System.out.println("--- Add Student ---");
        int id = readInt(scanner, "Enter ID: ");
        String name = readString(scanner, "Enter name: ");
        int age = readInt(scanner, "Enter age: ");
        String grade = readString(scanner, "Enter grade: ");
        Student s = new Student(id, name, age, grade);
        manager.addStudent(s);
        System.out.println("Student added.");
    }

    private static void handleList(StudentManager manager) {
        System.out.println("--- All Students ---");
        List<Student> all = manager.getAllStudents();
        if (all.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student s : all) {
                System.out.println(s);
            }
        }
    }

    private static void handleUpdate(Scanner scanner, StudentManager manager) {
        System.out.println("--- Update Student ---");
        int id = readInt(scanner, "Enter ID of student to update: ");
        Student s = manager.findById(id);
        if (s == null) {
            System.out.println("Student not found with ID " + id);
            return;
        }
        System.out.println("Current: " + s);
        String name = readString(scanner, "Enter new name (or blank to skip): ");
        String ageInput = readString(scanner, "Enter new age (or blank to skip): ");
        Integer age = null;
        if (!ageInput.isBlank()) {
            try {
                age = Integer.parseInt(ageInput.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid age input; skipping age update.");
            }
        }
        String grade = readString(scanner, "Enter new grade (or blank to skip): ");
        boolean ok = manager.updateStudent(
                id,
                name.isBlank() ? null : name,
                age,
                grade.isBlank() ? null : grade
        );
        if (ok) {
            System.out.println("Student updated.");
        } else {
            System.out.println("Update failed.");
        }
    }

    private static void handleDelete(Scanner scanner, StudentManager manager) {
        System.out.println("--- Delete Student ---");
        int id = readInt(scanner, "Enter ID of student to delete: ");
        boolean ok = manager.deleteStudent(id);
        if (ok) {
            System.out.println("Student deleted.");
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    private static void handleViewOne(Scanner scanner, StudentManager manager) {
        System.out.println("--- View Student by ID ---");
        int id = readInt(scanner, "Enter ID: ");
        Student s = manager.findById(id);
        if (s != null) {
            System.out.println(s);
        } else {
            System.out.println("Student not found with ID " + id);
        }
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            try {
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    private static String readString(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}


class Student {
    private int id;
    private String name;
    private int age;
    private String grade;

    public Student(int id, String name, int age, String grade) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    @Override
    public String toString() {
        return "Student{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", age=" + age +
               ", grade='" + grade + '\'' +
               '}';
    }
}


class StudentManager {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student s) {
        students.add(s);
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public Student findById(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    public boolean updateStudent(int id, String newName, Integer newAge, String newGrade) {
        Student s = findById(id);
        if (s == null) {
            return false;
        }
        if (newName != null) {
            s.setName(newName);
        }
        if (newAge != null) {
            s.setAge(newAge);
        }
        if (newGrade != null) {
            s.setGrade(newGrade);
        }
        return true;
    }

    public boolean deleteStudent(int id) {
        Student s = findById(id);
        if (s != null) {
            students.remove(s);
            return true;
        }
        return false;
    }
}
