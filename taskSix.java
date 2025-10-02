import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class taskSix {

    private static final String TASKS_FILE = "tasks.txt";

    private JFrame frame;
    private DefaultListModel<String> listModel;
    private JList<String> taskList;
    private JTextField inputField;
    private JButton addButton;
    private JButton deleteButton;

    public taskSix() {
        initComponents();
        loadTasksFromFile();
    }

    private void initComponents() {
        frame = new JFrame("To-Do List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScrollPane = new JScrollPane(taskList);

        inputField = new JTextField();
        addButton = new JButton("Add Task");
        deleteButton = new JButton("Delete Task");

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(deleteButton);

        frame.setLayout(new BorderLayout());
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(listScrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Add task action
        addButton.addActionListener(e -> {
            String text = inputField.getText().trim();
            if (!text.isEmpty()) {
                listModel.addElement(text);
                inputField.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "Enter a non-empty task.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Delete task action
        deleteButton.addActionListener(e -> {
            int selected = taskList.getSelectedIndex();
            if (selected >= 0) {
                listModel.remove(selected);
            } else {
                JOptionPane.showMessageDialog(frame, "Select a task to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Save tasks on window closing
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveTasksToFile();
            }
        });

        frame.setVisible(true);
    }

    private void loadTasksFromFile() {
        File file = new File(TASKS_FILE);
        if (!file.exists()) {
            return;  // no tasks yet
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                listModel.addElement(line);
            }
        } catch (IOException e) {
            System.err.println("Error loading tasks: " + e.getMessage());
        }
    }

    private void saveTasksToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TASKS_FILE))) {
            for (int i = 0; i < listModel.getSize(); i++) {
                bw.write(listModel.get(i));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new taskSix());
    }
}
