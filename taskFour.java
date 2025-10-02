import java.io.*;
import java.util.*;

public class taskFour {
    private static final String NOTES_FILENAME = "notes.txt";

    public static void writeNote(String note, boolean append) {
        try (FileWriter fw = new FileWriter(NOTES_FILENAME, append);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(note);
            bw.newLine();
            System.out.println("Saved note.");
        } catch (IOException e) {
            System.err.println("Error writing note: " + e.getMessage());
        }
    }

    public static void readNotes() {
        File f = new File(NOTES_FILENAME);
        if (!f.exists()) {
            System.out.println("No notes file exists yet.");
            return;
        }
        try (FileReader fr = new FileReader(f);
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            System.out.println("----- Notes -----");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("----- End -----");
        } catch (IOException e) {
            System.err.println("Error reading notes: " + e.getMessage());
        }
    }

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add a note");
            System.out.println("2. View all notes");
            System.out.println("3. Overwrite notes (clear and write new)");
            System.out.println("4. Exit");
            System.out.print("Choose: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.print("Enter note: ");
                    String note = scanner.nextLine();
                    writeNote(note, true);
                    break;
                case "2":
                    readNotes();
                    break;
                case "3":
                    System.out.println("Warning: This will delete existing notes.");
                    System.out.print("Enter new note: ");
                    String newNote = scanner.nextLine();
                    writeNote(newNote, false);
                    break;
                case "4":
                    System.out.println("Exiting.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Notes Manager");
        menu();
    }
}
