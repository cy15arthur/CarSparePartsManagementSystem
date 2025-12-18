package view;

import util.UITheme;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.User;

/**
 * Home page / dashboard for non-admin staff users.
 * Shown from {@link Login} when the logged in user is not an administrator.
 */
public class HomePage extends JFrame {

    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(HomePage.class.getName());

    // Layout components
    private JPanel mainPanel;
    private JPanel headerPanel;
    private JPanel contentPanel;
    private JPanel gridPanel;

    // Header
    private JLabel welcomeLabel;
    private JLabel roleLabel;
    private JButton logoutButton;

    // Cards (staff dashboard)
    private JPanel partsCard;
    private JLabel partsIconLabel;
    private JLabel partsTitleLabel;
    private JLabel partsDescLabel;

    private JPanel customersCard;
    private JLabel customersIconLabel;
    private JLabel customersTitleLabel;
    private JLabel customersDescLabel;

    private JPanel salesCard;
    private JLabel salesIconLabel;
    private JLabel salesTitleLabel;
    private JLabel salesDescLabel;

    public HomePage() {
        initComponents();
        setLocationRelativeTo(null);
        loadUserInfo();
    }

    private void loadUserInfo() {
        User user = Login.getCurrentUser();
        if (user != null) {
            welcomeLabel.setText("Welcome, " + user.getFullName());
            roleLabel.setText("Staff Dashboard");
        } else {
            welcomeLabel.setText("Welcome");
            roleLabel.setText("Staff Dashboard");
        }
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Home - Car Spare Parts Management System");
        setResizable(true);
        setMinimumSize(new Dimension(1200, 700));

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(UITheme.BACKGROUND_LIGHT);

        initHeader();
        initContent();

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        pack();
    }

    private void initHeader() {
        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(UITheme.PRIMARY);
        headerPanel.setPreferredSize(new Dimension(1200, 90));
        headerPanel.setBorder(new EmptyBorder(20, 40, 20, 40));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setOpaque(false);

        welcomeLabel = new JLabel("Welcome");
        welcomeLabel.setFont(UITheme.FONT_TITLE);
        welcomeLabel.setForeground(UITheme.TEXT_WHITE);
        welcomeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        roleLabel = new JLabel("Staff Dashboard");
        roleLabel.setFont(UITheme.FONT_SUBHEADING);
        roleLabel.setForeground(new Color(255, 255, 255, 200));
        roleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        roleLabel.setBorder(new EmptyBorder(4, 0, 0, 0));

        leftPanel.add(welcomeLabel);
        leftPanel.add(roleLabel);

        logoutButton = new JButton("Logout");
        logoutButton.setFont(UITheme.FONT_BUTTON);
        logoutButton.setForeground(UITheme.TEXT_WHITE);
        logoutButton.setBackground(UITheme.ERROR);
        logoutButton.setBorder(new EmptyBorder(10, 26, 10, 26));
        logoutButton.setFocusPainted(false);
        logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutButton.addActionListener(e -> logout());
        logoutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                logoutButton.setBackground(new Color(200, 35, 51));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                logoutButton.setBackground(UITheme.ERROR);
            }
        });

        headerPanel.add(leftPanel, BorderLayout.WEST);
        headerPanel.add(logoutButton, BorderLayout.EAST);
        mainPanel.add(headerPanel, BorderLayout.NORTH);
    }

    private void initContent() {
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(UITheme.BACKGROUND_LIGHT);
        contentPanel.setBorder(new EmptyBorder(40, 40, 40, 40));

        // 2x2 grid; currently used for 3 staff tiles
        gridPanel = new JPanel(new GridLayout(2, 2, 30, 30));
        gridPanel.setBackground(UITheme.BACKGROUND_LIGHT);

        // Parts card
        partsCard = new JPanel();
        partsIconLabel = new JLabel();
        partsTitleLabel = new JLabel();
        partsDescLabel = new JLabel();
        setupCard(partsCard, partsIconLabel, partsTitleLabel, partsDescLabel,
                "⚙️", "Spare Parts", "View and manage spare parts inventory", UITheme.PRIMARY);
        partsCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new PartManagement().setVisible(true);
                dispose();
            }
        });

        // Customers card
        customersCard = new JPanel();
        customersIconLabel = new JLabel();
        customersTitleLabel = new JLabel();
        customersDescLabel = new JLabel();
        setupCard(customersCard, customersIconLabel, customersTitleLabel, customersDescLabel,
                "👥", "Customers", "Register and manage customers", UITheme.ACCENT_PURPLE);
        customersCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new CustomerManagement().setVisible(true);
                dispose();
            }
        });

        // Sales card
        salesCard = new JPanel();
        salesIconLabel = new JLabel();
        salesTitleLabel = new JLabel();
        salesDescLabel = new JLabel();
        setupCard(salesCard, salesIconLabel, salesTitleLabel, salesDescLabel,
                "💰", "Sales", "Record customer sales", UITheme.ACCENT_ORANGE);
        salesCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new SalesManagement().setVisible(true);
                dispose();
            }
        });

        gridPanel.add(partsCard);
        gridPanel.add(customersCard);
        gridPanel.add(salesCard);

        contentPanel.add(gridPanel, BorderLayout.CENTER);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
    }

    private void setupCard(JPanel card, JLabel iconLabel, JLabel titleLabel, JLabel descLabel,
                           String icon, String title, String description, Color accentColor) {
        card.setLayout(new BorderLayout());
        card.setBackground(UITheme.SURFACE);
        card.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(230, 230, 230), 1, true),
                new EmptyBorder(25, 25, 25, 25)
        ));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBackground(UITheme.BACKGROUND_DARK);
                card.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(accentColor, 2, true),
                        new EmptyBorder(24, 24, 24, 24)
                ));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                card.setBackground(UITheme.SURFACE);
                card.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(new Color(230, 230, 230), 1, true),
                        new EmptyBorder(25, 25, 25, 25)
                ));
            }
        });

        JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        iconPanel.setOpaque(false);

        iconLabel.setText(icon);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));
        iconLabel.setForeground(accentColor);
        iconPanel.add(iconLabel);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);
        textPanel.setBorder(new EmptyBorder(15, 0, 0, 0));

        titleLabel.setText(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(UITheme.TEXT_PRIMARY);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        descLabel.setText(description);
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        descLabel.setForeground(new Color(120, 120, 120));
        descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        descLabel.setBorder(new EmptyBorder(5, 0, 0, 0));

        textPanel.add(titleLabel);
        textPanel.add(descLabel);

        card.add(iconPanel, BorderLayout.NORTH);
        card.add(textPanel, BorderLayout.CENTER);
    }

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to logout?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            Login.logout();
            new Login().setVisible(true);
            dispose();
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(() -> new HomePage().setVisible(true));
    }
}


