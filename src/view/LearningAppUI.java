package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LearningAppUI extends JFrame {
    private JTextField subjectField;
    private JTextField topicField;
    private JTextArea resultArea;
    private JButton submitButton;
    
    public LearningAppUI() {
        setTitle("Learning AI Management");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Panel for input fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));
        
        inputPanel.add(new JLabel("Subject:"));
        subjectField = new JTextField();
        inputPanel.add(subjectField);
        
        inputPanel.add(new JLabel("Topic:"));
        topicField = new JTextField();
        inputPanel.add(topicField);
        
        submitButton = new JButton("Submit");
        inputPanel.add(submitButton);
        
        // Text area for displaying results
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        
        // Adding components to frame
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);
        
        // Action listener for button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String subject = subjectField.getText().trim();
                String topic = topicField.getText().trim();
                if (!subject.isEmpty() && !topic.isEmpty()) {
                    // Call the method to handle the submission
                    handleSubmission(subject, topic);
                } else {
                    JOptionPane.showMessageDialog(LearningAppUI.this, "Please fill in all fields", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        setLocationRelativeTo(null); // Center the window
    }

    private void handleSubmission(String subject, String topic) {
        // Here you can implement the logic to call the GPT API
        // For demonstration, we just append the subject and topic to the result area
        // Replace this with actual API call logic
        String result = "Subject: " + subject + "\nTopic: " + topic + "\n\nResult from GPT will be shown here.";
        resultArea.setText(result); // Display result
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LearningAppUI app = new LearningAppUI();
                app.setVisible(true);
            }
        });
    }
}
