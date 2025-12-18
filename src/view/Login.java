package view;

import dao.UserDao;
import dao.UserDaoImpl;
import model.User;
import util.UITheme;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login extends JFrame {

    private static final Logger logger =
            Logger.getLogger(Login.class.getName());

    private static User currentUser = null;

    private JPanel mainPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JLabel titleLabel;
    private JLabel subtitleLabel;
    private JLabel loginTitle;
    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel errorLabel;
    private JLabel signupLabel;
    private JLabel forgotPasswordLabel;

    public Login() {
        initComponents();
        setLocationRelativeTo(null);

        // 🔥 FULL SIZE (NOT MINIMIZED)
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true);
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    public static void logout() {
        currentUser = null;
    }

    private void initComponents() {

        mainPanel = new JPanel(new BorderLayout());
        leftPanel = new JPanel();
        rightPanel = new JPanel();

        titleLabel = new JLabel();
        subtitleLabel = new JLabel();
        loginTitle = new JLabel();
        usernameLabel = new JLabel();
        usernameField = new JTextField();
        passwordLabel = new JLabel();
        passwordField = new JPasswordField();
        loginButton = new JButton();
        errorLabel = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login - Car Spare Parts Management System");

        mainPanel.setBackground(UITheme.BACKGROUND_LIGHT);

        // LEFT PANEL
        leftPanel.setBackground(UITheme.PRIMARY);
        leftPanel.setPreferredSize(new Dimension(420, 0));
        leftPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titleLabel.setFont(UITheme.FONT_TITLE);
        titleLabel.setForeground(UITheme.TEXT_WHITE);
        titleLabel.setText("Car Spare Parts");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        leftPanel.add(titleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 420, 50));

        subtitleLabel.setFont(UITheme.FONT_SUBHEADING);
        subtitleLabel.setForeground(UITheme.TEXT_WHITE);
        subtitleLabel.setText("Management System");
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        leftPanel.add(subtitleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 420, 30));

        mainPanel.add(leftPanel, BorderLayout.WEST);

        // RIGHT PANEL
        rightPanel.setBackground(UITheme.SURFACE);
        rightPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        rightPanel.setBorder(BorderFactory.createEmptyBorder(80, 80, 80, 80));

        loginTitle.setFont(UITheme.FONT_HEADING);
        loginTitle.setForeground(UITheme.TEXT_PRIMARY);
        loginTitle.setText("Welcome Back");
        rightPanel.add(loginTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 400, 40));

        usernameLabel.setFont(UITheme.FONT_BODY_BOLD);
        usernameLabel.setForeground(UITheme.TEXT_PRIMARY);
        usernameLabel.setText("Username");
        rightPanel.add(usernameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 400, 25));

        usernameField.setFont(UITheme.FONT_BODY);
        usernameField.setBorder(UITheme.createInputBorder());
        usernameField.setBackground(UITheme.SURFACE);
        rightPanel.add(usernameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 185, 400, UITheme.INPUT_HEIGHT));

        passwordLabel.setFont(UITheme.FONT_BODY_BOLD);
        passwordLabel.setForeground(UITheme.TEXT_PRIMARY);
        passwordLabel.setText("Password");
        rightPanel.add(passwordLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 400, 25));

        passwordField.setFont(UITheme.FONT_BODY);
        passwordField.setBorder(UITheme.createInputBorder());
        passwordField.setBackground(UITheme.SURFACE);
        passwordField.addActionListener(e -> loginButtonActionPerformed());
        rightPanel.add(passwordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 265, 400, UITheme.INPUT_HEIGHT));

        loginButton.setBackground(UITheme.BUTTON_PRIMARY);
        loginButton.setFont(UITheme.FONT_BUTTON);
        loginButton.setForeground(UITheme.TEXT_WHITE);
        loginButton.setText("Login");
        loginButton.setBorder(UITheme.createButtonBorder(UITheme.BUTTON_PRIMARY));
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(e -> loginButtonActionPerformed());
        rightPanel.add(loginButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 400, UITheme.BUTTON_HEIGHT));

        errorLabel.setFont(UITheme.FONT_SMALL);
        errorLabel.setForeground(UITheme.ERROR);
        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        errorLabel.setText(" ");
        rightPanel.add(errorLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 400, 20));

        // "Forgot password?" link
        forgotPasswordLabel = new JLabel("Forgot password?");
        forgotPasswordLabel.setFont(UITheme.FONT_SMALL);
        forgotPasswordLabel.setForeground(UITheme.INFO);
        forgotPasswordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgotPasswordLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                handleForgotPassword();
            }
        });
        rightPanel.add(forgotPasswordLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 200, 20));

        // "Don't have an account? Sign up" link
        signupLabel = new JLabel("Don't have an account? Sign up");
        signupLabel.setFont(UITheme.FONT_SMALL);
        signupLabel.setForeground(UITheme.PRIMARY);
        signupLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signupLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                new Signup().setVisible(true);
                dispose();
            }
        });
        rightPanel.add(signupLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 250, 20));

        mainPanel.add(rightPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
    }

    private void loginButtonActionPerformed() {

        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty()) {
            errorLabel.setText("Please enter your username");
            usernameField.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            errorLabel.setText("Please enter your password");
            passwordField.requestFocus();
            return;
        }

        UserDao userDao = new UserDaoImpl();
        User user = userDao.authenticate(username, password);

        if (user != null) {
            currentUser = user;
            errorLabel.setText(" ");

            JOptionPane.showMessageDialog(
                    this,
                    "Welcome, " + user.getFullName() + "!",
                    "Login Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );

            if (user.isAdmin()) {
                new AdminDashboard().setVisible(true);
            } else {
                new HomePage().setVisible(true);
            }

            dispose();
        } else {
            errorLabel.setText("Invalid username or password");
            passwordField.setText("");
            passwordField.requestFocus();
        }
    }

    /**
     * Very simple "forgot password" flow:
     * - Asks for username and a new password
     * - If user exists, updates the password in the database
     */
    private void handleForgotPassword() {
        String username = JOptionPane.showInputDialog(this,
                "Enter your username:",
                "Forgot Password",
                JOptionPane.QUESTION_MESSAGE);

        if (username == null || username.trim().isEmpty()) {
            return;
        }

        UserDao userDao = new UserDaoImpl();
        User user = userDao.findByUsername(username.trim());
        if (user == null) {
            JOptionPane.showMessageDialog(this,
                    "No user found with that username.",
                    "Not Found",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        JPasswordField newPassField = new JPasswordField();
        int result = JOptionPane.showConfirmDialog(this, newPassField,
                "Enter new password", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String newPassword = new String(newPassField.getPassword());
        if (newPassword.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Password cannot be empty.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (newPassword.trim().length() < 5) {
            JOptionPane.showMessageDialog(this,
                    "New password is too short.\nPlease use at least 5 characters.",
                    "Weak Password",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        user.setPassword(newPassword);
        int rows = userDao.update(user);
        if (rows > 0) {
            JOptionPane.showMessageDialog(this,
                    "Password updated successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Failed to update password.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(() -> new Login().setVisible(true));
    }
}
