package view;

import dao.UserDao;
import dao.UserDaoImpl;
import model.User;
import util.UITheme;
import javax.swing.JOptionPane;

/**
 * Modern login interface for the Car Spare Parts Management System.
 * 
 * @author cyita
 */
public class Login extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = 
        java.util.logging.Logger.getLogger(Login.class.getName());
    
    private static User currentUser = null;

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        setLocationRelativeTo(null);
    }
    
    /**
     * Gets the currently logged-in user.
     * 
     * @return the current user, or null if not logged in
     */
    public static User getCurrentUser() {
        return currentUser;
    }
    
    /**
     * Checks if a user is currently logged in.
     * 
     * @return true if logged in, false otherwise
     */
    public static boolean isLoggedIn() {
        return currentUser != null;
    }
    
    /**
     * Logs out the current user.
     */
    public static void logout() {
        currentUser = null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        leftPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        subtitleLabel = new javax.swing.JLabel();
        rightPanel = new javax.swing.JPanel();
        loginTitle = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        loginButton = new javax.swing.JButton();
        errorLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login - Car Spare Parts Management System");
        setResizable(false);

        mainPanel.setBackground(UITheme.BACKGROUND_LIGHT);
        mainPanel.setLayout(new java.awt.BorderLayout());

        // Left Panel - Branding
        leftPanel.setBackground(UITheme.PRIMARY);
        leftPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titleLabel.setFont(UITheme.FONT_TITLE);
        titleLabel.setForeground(UITheme.TEXT_WHITE);
        titleLabel.setText("Car Spare Parts");
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        leftPanel.add(titleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 400, 50));

        subtitleLabel.setFont(UITheme.FONT_SUBHEADING);
        subtitleLabel.setForeground(UITheme.TEXT_WHITE);
        subtitleLabel.setText("Management System");
        subtitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        leftPanel.add(subtitleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 400, 30));

        mainPanel.add(leftPanel, java.awt.BorderLayout.WEST);

        // Right Panel - Login Form
        rightPanel.setBackground(UITheme.SURFACE);
        rightPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        rightPanel.setBorder(new javax.swing.border.EmptyBorder(50, 50, 50, 50));

        loginTitle.setFont(UITheme.FONT_HEADING);
        loginTitle.setForeground(UITheme.TEXT_PRIMARY);
        loginTitle.setText("Welcome Back");
        rightPanel.add(loginTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 400, 40));

        usernameLabel.setFont(UITheme.FONT_BODY_BOLD);
        usernameLabel.setForeground(UITheme.TEXT_PRIMARY);
        usernameLabel.setText("Username");
        rightPanel.add(usernameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 400, 25));

        usernameField.setFont(UITheme.FONT_BODY);
        usernameField.setBorder(UITheme.createInputBorder());
        usernameField.setBackground(UITheme.SURFACE);
        usernameField.setPreferredSize(new java.awt.Dimension(400, UITheme.INPUT_HEIGHT));
        usernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordField.requestFocus();
            }
        });
        rightPanel.add(usernameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 145, 400, UITheme.INPUT_HEIGHT));

        passwordLabel.setFont(UITheme.FONT_BODY_BOLD);
        passwordLabel.setForeground(UITheme.TEXT_PRIMARY);
        passwordLabel.setText("Password");
        rightPanel.add(passwordLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 400, 25));

        passwordField.setFont(UITheme.FONT_BODY);
        passwordField.setBorder(UITheme.createInputBorder());
        passwordField.setBackground(UITheme.SURFACE);
        passwordField.setPreferredSize(new java.awt.Dimension(400, UITheme.INPUT_HEIGHT));
        passwordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });
        rightPanel.add(passwordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 225, 400, UITheme.INPUT_HEIGHT));

        loginButton.setBackground(UITheme.BUTTON_PRIMARY);
        loginButton.setFont(UITheme.FONT_BUTTON);
        loginButton.setForeground(UITheme.TEXT_WHITE);
        loginButton.setText("Login");
        loginButton.setBorder(UITheme.createButtonBorder(UITheme.BUTTON_PRIMARY));
        loginButton.setFocusPainted(false);
        loginButton.setPreferredSize(new java.awt.Dimension(400, UITheme.BUTTON_HEIGHT));
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });
        rightPanel.add(loginButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 400, UITheme.BUTTON_HEIGHT));

        errorLabel.setFont(UITheme.FONT_SMALL);
        errorLabel.setForeground(UITheme.ERROR);
        errorLabel.setText(" ");
        errorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rightPanel.add(errorLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 400, 20));

        mainPanel.add(rightPanel, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );

        pack();
    }

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {
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
            
            // Show success message
            JOptionPane.showMessageDialog(this, 
                "Welcome, " + user.getFullName() + "!", 
                "Login Successful", 
                JOptionPane.INFORMATION_MESSAGE);
            
            // Navigate based on role
            if (user.isAdmin()) {
                new AdminDashboard().setVisible(true);
            } else {
                new HomePage().setVisible(true);
            }
            
            this.dispose();
        } else {
            errorLabel.setText("Invalid username or password");
            passwordField.setText("");
            passwordField.requestFocus();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new Login().setVisible(true));
    }

    // Variables declaration
    private javax.swing.JLabel errorLabel;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel loginTitle;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JLabel subtitleLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JTextField usernameField;
    private javax.swing.JLabel usernameLabel;
}

