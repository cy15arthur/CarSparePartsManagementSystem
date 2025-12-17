package view;

import dao.UserDao;
import dao.UserDaoImpl;
import model.User;
import util.UITheme;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

/**
 * Simple signup form for creating new staff users.
 */
public class Signup extends JFrame {

    private JPanel mainPanel;
    private JLabel titleLabel;
    private JLabel fullNameLabel;
    private JTextField fullNameField;
    private JLabel emailLabel;
    private JTextField emailField;
    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JLabel confirmPasswordLabel;
    private JPasswordField confirmPasswordField;
    private JButton createAccountButton;
    private JButton cancelButton;

    public Signup() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sign Up - Car Spare Parts Management System");
        setResizable(false);
        setSize(480, 520);

        mainPanel = new JPanel();
        mainPanel.setBackground(UITheme.SURFACE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        titleLabel = new JLabel("Create Your Account");
        titleLabel.setFont(UITheme.FONT_HEADING);
        titleLabel.setForeground(UITheme.TEXT_PRIMARY);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        mainPanel.add(titleLabel);

        fullNameLabel = new JLabel("Full Name");
        fullNameLabel.setFont(UITheme.FONT_BODY_BOLD);
        fullNameLabel.setForeground(UITheme.TEXT_PRIMARY);
        fullNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(fullNameLabel);

        fullNameField = new JTextField();
        fullNameField.setFont(UITheme.FONT_BODY);
        fullNameField.setBorder(UITheme.createInputBorder());
        fullNameField.setBackground(UITheme.SURFACE);
        fullNameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, UITheme.INPUT_HEIGHT));
        fullNameField.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(fullNameField);
        mainPanel.add(Box.createVerticalStrut(12));

        emailLabel = new JLabel("Email");
        emailLabel.setFont(UITheme.FONT_BODY_BOLD);
        emailLabel.setForeground(UITheme.TEXT_PRIMARY);
        emailLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(emailLabel);

        emailField = new JTextField();
        emailField.setFont(UITheme.FONT_BODY);
        emailField.setBorder(UITheme.createInputBorder());
        emailField.setBackground(UITheme.SURFACE);
        emailField.setMaximumSize(new Dimension(Integer.MAX_VALUE, UITheme.INPUT_HEIGHT));
        emailField.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(emailField);
        mainPanel.add(Box.createVerticalStrut(12));

        usernameLabel = new JLabel("Username");
        usernameLabel.setFont(UITheme.FONT_BODY_BOLD);
        usernameLabel.setForeground(UITheme.TEXT_PRIMARY);
        usernameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setFont(UITheme.FONT_BODY);
        usernameField.setBorder(UITheme.createInputBorder());
        usernameField.setBackground(UITheme.SURFACE);
        usernameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, UITheme.INPUT_HEIGHT));
        usernameField.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(usernameField);
        mainPanel.add(Box.createVerticalStrut(12));

        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(UITheme.FONT_BODY_BOLD);
        passwordLabel.setForeground(UITheme.TEXT_PRIMARY);
        passwordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setFont(UITheme.FONT_BODY);
        passwordField.setBorder(UITheme.createInputBorder());
        passwordField.setBackground(UITheme.SURFACE);
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, UITheme.INPUT_HEIGHT));
        passwordField.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(passwordField);
        mainPanel.add(Box.createVerticalStrut(12));

        confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setFont(UITheme.FONT_BODY_BOLD);
        confirmPasswordLabel.setForeground(UITheme.TEXT_PRIMARY);
        confirmPasswordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(confirmPasswordLabel);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setFont(UITheme.FONT_BODY);
        confirmPasswordField.setBorder(UITheme.createInputBorder());
        confirmPasswordField.setBackground(UITheme.SURFACE);
        confirmPasswordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, UITheme.INPUT_HEIGHT));
        confirmPasswordField.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(confirmPasswordField);
        mainPanel.add(Box.createVerticalStrut(24));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(UITheme.FONT_BUTTON);
        cancelButton.setForeground(UITheme.TEXT_PRIMARY);
        cancelButton.setBackground(UITheme.BACKGROUND_DARK);
        cancelButton.setBorder(UITheme.createButtonBorder(UITheme.BORDER));
        cancelButton.setFocusPainted(false);
        cancelButton.addActionListener(e -> dispose());

        createAccountButton = new JButton("Create Account");
        createAccountButton.setFont(UITheme.FONT_BUTTON);
        createAccountButton.setForeground(UITheme.TEXT_WHITE);
        createAccountButton.setBackground(UITheme.BUTTON_PRIMARY);
        createAccountButton.setBorder(UITheme.createButtonBorder(UITheme.BUTTON_PRIMARY));
        createAccountButton.setFocusPainted(false);
        createAccountButton.addActionListener(e -> handleCreateAccount());

        buttonPanel.add(cancelButton);
        buttonPanel.add(createAccountButton);
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(buttonPanel);

        setContentPane(mainPanel);
    }

    private void handleCreateAccount() {
        String fullName = fullNameField.getText().trim();
        String email = emailField.getText().trim();
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (fullName.isEmpty() || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Full name, username, and password are required.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this,
                    "Passwords do not match.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        UserDao userDao = new UserDaoImpl();
        if (userDao.usernameExists(username)) {
            JOptionPane.showMessageDialog(this,
                    "Username already exists. Please choose another.",
                    "Duplicate Username",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setUsername(username);
        user.setPassword(password); // Note: plain text for now
        user.setFullName(fullName);
        user.setEmail(email);
        user.setRole("staff");
        user.setActive(true);

        int rows = userDao.create(user);
        if (rows > 0) {
            JOptionPane.showMessageDialog(this,
                    "Account created successfully. You can now log in.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new Login().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Failed to create account. Please try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}


