package view;

import util.UITheme;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import model.User;

/**
 * Professional Admin Dashboard with modern UI design
 * Compatible with NetBeans 8.1
 * 
 * @author cyita
 */
public class AdminDashboard extends JFrame {
    
    private static final java.util.logging.Logger logger = 
        java.util.logging.Logger.getLogger(AdminDashboard.class.getName());

    public AdminDashboard() {
        initComponents();
        setLocationRelativeTo(null);
        loadUserInfo();
    }
    
    private void loadUserInfo() {
        User user = Login.getCurrentUser();
        if (user != null) {
            welcomeLabel.setText("Welcome back, " + user.getFullName());
            roleLabel.setText("Administrator Dashboard");
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        mainPanel = new JPanel();
        headerPanel = new JPanel();
        welcomeLabel = new JLabel();
        roleLabel = new JLabel();
        logoutButton = new JButton();
        contentPanel = new JPanel();
        gridPanel = new JPanel();
        
        // Cards
        categoriesCard = new JPanel();
        categoriesIconLabel = new JLabel();
        categoriesTitleLabel = new JLabel();
        categoriesDescLabel = new JLabel();
        
        partsCard = new JPanel();
        partsIconLabel = new JLabel();
        partsTitleLabel = new JLabel();
        partsDescLabel = new JLabel();
        
        customersCard = new JPanel();
        customersIconLabel = new JLabel();
        customersTitleLabel = new JLabel();
        customersDescLabel = new JLabel();
        
        suppliersCard = new JPanel();
        suppliersIconLabel = new JLabel();
        suppliersTitleLabel = new JLabel();
        suppliersDescLabel = new JLabel();
        
        salesCard = new JPanel();
        salesIconLabel = new JLabel();
        salesTitleLabel = new JLabel();
        salesDescLabel = new JLabel();
        
        reportsCard = new JPanel();
        reportsIconLabel = new JLabel();
        reportsTitleLabel = new JLabel();
        reportsDescLabel = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin Dashboard - Car Spare Parts Management System");
        setResizable(true);
        setMinimumSize(new Dimension(1300, 750));

        // Main Panel
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(UITheme.BACKGROUND_LIGHT);

        // ===== Header Panel =====
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(UITheme.PRIMARY);
        headerPanel.setPreferredSize(new Dimension(1300, 100));
        headerPanel.setBorder(new EmptyBorder(20, 40, 20, 40));

        // Left side - Welcome text
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setOpaque(false);

        welcomeLabel.setText("Welcome back, Admin");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        welcomeLabel.setForeground(UITheme.TEXT_WHITE);
        welcomeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        roleLabel.setText("Administrator Dashboard");
        roleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        roleLabel.setForeground(new Color(255, 255, 255, 180));
        roleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        roleLabel.setBorder(new EmptyBorder(5, 0, 0, 0));

        leftPanel.add(welcomeLabel);
        leftPanel.add(roleLabel);

        // Right side - Logout button
        logoutButton.setText("Logout");
        logoutButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        logoutButton.setForeground(UITheme.TEXT_WHITE);
        logoutButton.setBackground(new Color(220, 53, 69));
        logoutButton.setBorder(new EmptyBorder(12, 30, 12, 30));
        logoutButton.setFocusPainted(false);
        logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });
        logoutButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                logoutButton.setBackground(new Color(200, 35, 51));
            }
            public void mouseExited(MouseEvent evt) {
                logoutButton.setBackground(new Color(220, 53, 69));
            }
        });

        headerPanel.add(leftPanel, BorderLayout.WEST);
        headerPanel.add(logoutButton, BorderLayout.EAST);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // ===== Content Panel =====
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(UITheme.BACKGROUND_LIGHT);
        contentPanel.setBorder(new EmptyBorder(40, 40, 40, 40));

        // Grid Panel for cards
        gridPanel.setLayout(new GridLayout(2, 3, 30, 30));
        gridPanel.setBackground(UITheme.BACKGROUND_LIGHT);

        // ===== Categories Card =====
        setupCard(categoriesCard, categoriesIconLabel, categoriesTitleLabel, categoriesDescLabel,
                "📁", "Categories", "Manage product categories", UITheme.SECONDARY);
        categoriesCard.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                categoriesCardClicked(evt);
            }
            public void mouseEntered(MouseEvent evt) {
                cardMouseEntered(categoriesCard, UITheme.SECONDARY);
            }
            public void mouseExited(MouseEvent evt) {
                cardMouseExited(categoriesCard);
            }
        });

        // ===== Parts Card =====
        setupCard(partsCard, partsIconLabel, partsTitleLabel, partsDescLabel,
                "⚙️", "Spare Parts", "View and manage inventory", UITheme.PRIMARY);
        partsCard.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                partsCardClicked(evt);
            }
            public void mouseEntered(MouseEvent evt) {
                cardMouseEntered(partsCard, UITheme.PRIMARY);
            }
            public void mouseExited(MouseEvent evt) {
                cardMouseExited(partsCard);
            }
        });

        // ===== Customers Card =====
        setupCard(customersCard, customersIconLabel, customersTitleLabel, customersDescLabel,
                "👥", "Customers", "Customer information", UITheme.ACCENT_PURPLE);
        customersCard.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                customersCardClicked(evt);
            }
            public void mouseEntered(MouseEvent evt) {
                cardMouseEntered(customersCard, UITheme.ACCENT_PURPLE);
            }
            public void mouseExited(MouseEvent evt) {
                cardMouseExited(customersCard);
            }
        });

        // ===== Suppliers Card =====
        setupCard(suppliersCard, suppliersIconLabel, suppliersTitleLabel, suppliersDescLabel,
                "🏭", "Suppliers", "Supplier management", UITheme.ACCENT_PINK);
        suppliersCard.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                suppliersCardClicked(evt);
            }
            public void mouseEntered(MouseEvent evt) {
                cardMouseEntered(suppliersCard, UITheme.ACCENT_PINK);
            }
            public void mouseExited(MouseEvent evt) {
                cardMouseExited(suppliersCard);
            }
        });

        // ===== Sales Card =====
        setupCard(salesCard, salesIconLabel, salesTitleLabel, salesDescLabel,
                "💰", "Sales", "Sales transactions", UITheme.ACCENT_ORANGE);
        salesCard.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                salesCardClicked(evt);
            }
            public void mouseEntered(MouseEvent evt) {
                cardMouseEntered(salesCard, UITheme.ACCENT_ORANGE);
            }
            public void mouseExited(MouseEvent evt) {
                cardMouseExited(salesCard);
            }
        });

        // ===== Reports Card =====
        setupCard(reportsCard, reportsIconLabel, reportsTitleLabel, reportsDescLabel,
                "📊", "Reports", "Analytics & insights", UITheme.INFO);
        reportsCard.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                reportsCardClicked(evt);
            }
            public void mouseEntered(MouseEvent evt) {
                cardMouseEntered(reportsCard, UITheme.INFO);
            }
            public void mouseExited(MouseEvent evt) {
                cardMouseExited(reportsCard);
            }
        });

        // Add cards to grid
        gridPanel.add(categoriesCard);
        gridPanel.add(partsCard);
        gridPanel.add(customersCard);
        gridPanel.add(suppliersCard);
        gridPanel.add(salesCard);
        gridPanel.add(reportsCard);

        contentPanel.add(gridPanel, BorderLayout.CENTER);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Layout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, 1300, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
        );

        pack();
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

        // Icon Panel
        JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        iconPanel.setOpaque(false);
        
        iconLabel.setText(icon);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        iconLabel.setForeground(accentColor);
        iconPanel.add(iconLabel);

        // Text Panel
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);
        textPanel.setBorder(new EmptyBorder(15, 0, 0, 0));

        titleLabel.setText(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
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

    private void cardMouseEntered(JPanel card, Color accentColor) {
        card.setBackground(UITheme.BACKGROUND_DARK);
        card.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(accentColor, 2, true),
            new EmptyBorder(24, 24, 24, 24)
        ));
    }

    private void cardMouseExited(JPanel card) {
        card.setBackground(UITheme.SURFACE);
        card.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(230, 230, 230), 1, true),
            new EmptyBorder(25, 25, 25, 25)
        ));
    }

    // Event Handlers
    private void categoriesCardClicked(MouseEvent evt) {
        new CategoryManagement().setVisible(true);
        this.dispose();
    }

    private void partsCardClicked(MouseEvent evt) {
        new PartManagement().setVisible(true);
        this.dispose();
    }

    private void customersCardClicked(MouseEvent evt) {
        new CustomerManagement().setVisible(true);
        this.dispose();
    }

    private void suppliersCardClicked(MouseEvent evt) {
        new SupplierManagement().setVisible(true);
        this.dispose();
    }

    private void salesCardClicked(MouseEvent evt) {
        new SalesManagement().setVisible(true);
        this.dispose();
    }

    private void reportsCardClicked(MouseEvent evt) {
        JOptionPane.showMessageDialog(this,
            "Reports feature coming soon!",
            "Reports",
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void logoutButtonActionPerformed(ActionEvent evt) {
        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to logout?",
            "Confirm Logout",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            Login.logout();
            new Login().setVisible(true);
            this.dispose();
        }
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration
    private JPanel mainPanel;
    private JPanel headerPanel;
    private JLabel welcomeLabel;
    private JLabel roleLabel;
    private JButton logoutButton;
    private JPanel contentPanel;
    private JPanel gridPanel;
    
    private JPanel categoriesCard;
    private JLabel categoriesIconLabel;
    private JLabel categoriesTitleLabel;
    private JLabel categoriesDescLabel;
    
    private JPanel partsCard;
    private JLabel partsIconLabel;
    private JLabel partsTitleLabel;
    private JLabel partsDescLabel;
    
    private JPanel customersCard;
    private JLabel customersIconLabel;
    private JLabel customersTitleLabel;
    private JLabel customersDescLabel;
    
    private JPanel suppliersCard;
    private JLabel suppliersIconLabel;
    private JLabel suppliersTitleLabel;
    private JLabel suppliersDescLabel;
    
    private JPanel salesCard;
    private JLabel salesIconLabel;
    private JLabel salesTitleLabel;
    private JLabel salesDescLabel;
    
    private JPanel reportsCard;
    private JLabel reportsIconLabel;
    private JLabel reportsTitleLabel;
    private JLabel reportsDescLabel;
}