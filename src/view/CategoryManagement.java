package view;

import dao.CategoryDao;
import dao.CategoryDaoImpl;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import model.Category;
import util.UITheme;

/**
 * Professional Category Management interface
 * Compatible with NetBeans 8.1
 * 
 * @author cyita
 */
public class CategoryManagement extends JFrame {
    
    private final CategoryDao categoryDao = new CategoryDaoImpl();
    private static final java.util.logging.Logger logger = 
        java.util.logging.Logger.getLogger(CategoryManagement.class.getName());

    public CategoryManagement() {
        initComponents();
        setLocationRelativeTo(null);
        loadCategoriesToTable();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        mainPanel = new JPanel();
        headerPanel = new JPanel();
        titleLabel = new JLabel();
        subtitleLabel = new JLabel();
        backButton = new JButton();
        contentPanel = new JPanel();
        formPanel = new JPanel();
        categoryIdLabel = new JLabel();
        categoryIdTxt = new JTextField();
        categoryNameLabel = new JLabel();
        categoryNameTxt = new JTextField();
        searchLabel = new JLabel();
        searchTxt = new JTextField();
        searchBtn = new JButton();
        buttonPanel = new JPanel();
        saveBtn = new JButton();
        updateBtn = new JButton();
        deleteBtn = new JButton();
        displayBtn = new JButton();
        tablePanel = new JPanel();
        tableScrollPane = new JScrollPane();
        categoryTable = new JTable();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Category Management - Car Spare Parts System");
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

        // Left side - Title
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setOpaque(false);

        titleLabel.setText("Category Management");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(UITheme.TEXT_WHITE);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        subtitleLabel.setText("Manage and organize your product categories");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(255, 255, 255, 180));
        subtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        subtitleLabel.setBorder(new EmptyBorder(5, 0, 0, 0));

        leftPanel.add(titleLabel);
        leftPanel.add(subtitleLabel);

        // Right side - Back button
        backButton.setText("← Back to Dashboard");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setForeground(UITheme.TEXT_WHITE);
        backButton.setBackground(new Color(52, 73, 94));
        backButton.setBorder(new EmptyBorder(12, 30, 12, 30));
        backButton.setFocusPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        backButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                backButton.setBackground(new Color(44, 62, 80));
            }
            public void mouseExited(MouseEvent evt) {
                backButton.setBackground(new Color(52, 73, 94));
            }
        });

        headerPanel.add(leftPanel, BorderLayout.WEST);
        headerPanel.add(backButton, BorderLayout.EAST);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // ===== Content Panel =====
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBackground(UITheme.BACKGROUND_LIGHT);
        contentPanel.setBorder(new EmptyBorder(30, 40, 30, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 0, 20);

        // ===== Form Panel =====
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(UITheme.SURFACE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(230, 230, 230), 1, true),
            new EmptyBorder(30, 30, 30, 30)
        ));
        formPanel.setPreferredSize(new Dimension(450, 600));

        // Category ID
        categoryIdLabel.setText("Category ID");
        categoryIdLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        categoryIdLabel.setForeground(UITheme.TEXT_PRIMARY);
        categoryIdLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        categoryIdTxt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        categoryIdTxt.setBackground(UITheme.SURFACE);
        categoryIdTxt.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1, true),
            new EmptyBorder(10, 12, 10, 12)
        ));
        categoryIdTxt.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        categoryIdTxt.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Category Name
        categoryNameLabel.setText("Category Name");
        categoryNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        categoryNameLabel.setForeground(UITheme.TEXT_PRIMARY);
        categoryNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        categoryNameLabel.setBorder(new EmptyBorder(20, 0, 0, 0));

        categoryNameTxt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        categoryNameTxt.setBackground(UITheme.SURFACE);
        categoryNameTxt.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1, true),
            new EmptyBorder(10, 12, 10, 12)
        ));
        categoryNameTxt.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        categoryNameTxt.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Search Section
        searchLabel.setText("Search by ID");
        searchLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        searchLabel.setForeground(UITheme.TEXT_PRIMARY);
        searchLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        searchLabel.setBorder(new EmptyBorder(30, 0, 0, 0));

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
        searchPanel.setOpaque(false);
        searchPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        searchPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));

        searchTxt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchTxt.setBackground(UITheme.SURFACE);
        searchTxt.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1, true),
            new EmptyBorder(10, 12, 10, 12)
        ));

        searchBtn.setText("Search");
        searchBtn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        searchBtn.setForeground(UITheme.TEXT_WHITE);
        searchBtn.setBackground(UITheme.INFO);
        searchBtn.setBorder(new EmptyBorder(10, 20, 10, 20));
        searchBtn.setFocusPainted(false);
        searchBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });
        setupButtonHover(searchBtn, UITheme.INFO, new Color(23, 162, 184));

        searchPanel.add(searchTxt);
        searchPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        searchPanel.add(searchBtn);

        // Button Panel
        buttonPanel.setLayout(new GridLayout(2, 2, 15, 15));
        buttonPanel.setOpaque(false);
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));
        buttonPanel.setBorder(new EmptyBorder(30, 0, 0, 0));

        saveBtn.setText("Save");
        saveBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        saveBtn.setForeground(UITheme.TEXT_WHITE);
        saveBtn.setBackground(UITheme.BUTTON_SECONDARY);
        saveBtn.setBorder(new EmptyBorder(12, 20, 12, 20));
        saveBtn.setFocusPainted(false);
        saveBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });
        setupButtonHover(saveBtn, UITheme.BUTTON_SECONDARY, new Color(23, 162, 184));

        updateBtn.setText("Update");
        updateBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        updateBtn.setForeground(UITheme.TEXT_WHITE);
        updateBtn.setBackground(UITheme.BUTTON_WARNING);
        updateBtn.setBorder(new EmptyBorder(12, 20, 12, 20));
        updateBtn.setFocusPainted(false);
        updateBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        updateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });
        setupButtonHover(updateBtn, UITheme.BUTTON_WARNING, new Color(227, 151, 0));

        deleteBtn.setText("Delete");
        deleteBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        deleteBtn.setForeground(UITheme.TEXT_WHITE);
        deleteBtn.setBackground(UITheme.BUTTON_DANGER);
        deleteBtn.setBorder(new EmptyBorder(12, 20, 12, 20));
        deleteBtn.setFocusPainted(false);
        deleteBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        setupButtonHover(deleteBtn, UITheme.BUTTON_DANGER, new Color(200, 35, 51));

        displayBtn.setText("View All");
        displayBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        displayBtn.setForeground(UITheme.TEXT_WHITE);
        displayBtn.setBackground(UITheme.BUTTON_PRIMARY);
        displayBtn.setBorder(new EmptyBorder(12, 20, 12, 20));
        displayBtn.setFocusPainted(false);
        displayBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        displayBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                displayBtnActionPerformed(evt);
            }
        });
        setupButtonHover(displayBtn, UITheme.BUTTON_PRIMARY, UITheme.PRIMARY);

        buttonPanel.add(saveBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(displayBtn);

        // Add components to form panel
        formPanel.add(categoryIdLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        formPanel.add(categoryIdTxt);
        formPanel.add(categoryNameLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        formPanel.add(categoryNameTxt);
        formPanel.add(searchLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        formPanel.add(searchPanel);
        formPanel.add(buttonPanel);
        formPanel.add(Box.createVerticalGlue());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.35;
        gbc.weighty = 1.0;
        contentPanel.add(formPanel, gbc);

        // ===== Table Panel =====
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBackground(UITheme.SURFACE);
        tablePanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(230, 230, 230), 1, true),
            new EmptyBorder(20, 20, 20, 20)
        ));

        JLabel tableTitle = new JLabel("Categories List");
        tableTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tableTitle.setForeground(UITheme.TEXT_PRIMARY);
        tableTitle.setBorder(new EmptyBorder(0, 0, 15, 0));

        categoryTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        categoryTable.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"Category ID", "Category Name"}
        ));
        categoryTable.setRowHeight(40);
        categoryTable.setGridColor(new Color(240, 240, 240));
        categoryTable.setSelectionBackground(UITheme.PRIMARY_LIGHT);
        categoryTable.setSelectionForeground(UITheme.TEXT_PRIMARY);
        categoryTable.setShowVerticalLines(false);
        categoryTable.setIntercellSpacing(new Dimension(0, 1));
        categoryTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        categoryTable.getTableHeader().setBackground(UITheme.BACKGROUND_DARK);
        categoryTable.getTableHeader().setForeground(UITheme.TEXT_PRIMARY);
        categoryTable.getTableHeader().setBorder(new LineBorder(new Color(230, 230, 230)));
        categoryTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                categoryTableMouseClicked(evt);
            }
        });

        tableScrollPane.setViewportView(categoryTable);
        tableScrollPane.setBorder(new LineBorder(new Color(230, 230, 230), 1));

        tablePanel.add(tableTitle, BorderLayout.NORTH);
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.65;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0, 0, 0, 0);
        contentPanel.add(tablePanel, gbc);

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

    private void setupButtonHover(JButton button, Color normalColor, Color hoverColor) {
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(hoverColor);
            }
            public void mouseExited(MouseEvent evt) {
                button.setBackground(normalColor);
            }
        });
    }

    // Event Handlers
    private void backButtonActionPerformed(ActionEvent evt) {
        new AdminDashboard().setVisible(true);
        this.dispose();
    }

    private void saveBtnActionPerformed(ActionEvent evt) {
        if (categoryIdTxt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Category ID is required!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (categoryNameTxt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Category Name is required!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Category c = new Category();
        c.setCategoryId(categoryIdTxt.getText().trim());
        c.setCategoryName(categoryNameTxt.getText().trim());

        int rowAffected = categoryDao.create(c);
        if (rowAffected > 0) {
            JOptionPane.showMessageDialog(this, "Category saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            loadCategoriesToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to save category!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateBtnActionPerformed(ActionEvent evt) {
        if (categoryIdTxt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Search for a category first!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!categoryIdTxt.isEditable()) {
            // ID is locked, proceed with update
        } else {
            JOptionPane.showMessageDialog(this, "Search a category first (ID must be locked).", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (categoryNameTxt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Category name is required!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Category c = new Category();
        c.setCategoryId(categoryIdTxt.getText().trim());
        c.setCategoryName(categoryNameTxt.getText().trim());

        int result = categoryDao.update(c);
        if (result > 0) {
            JOptionPane.showMessageDialog(this, "Category updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            categoryIdTxt.setEditable(true);
            clearFields();
            loadCategoriesToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update category!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteBtnActionPerformed(ActionEvent evt) {
        if (categoryIdTxt.getText().trim().isEmpty() || categoryIdTxt.isEditable()) {
            JOptionPane.showMessageDialog(this, "Search for a category first!", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete category: " + categoryIdTxt.getText() + "?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirm != JOptionPane.YES_OPTION) return;

        int result = categoryDao.delete(categoryIdTxt.getText().trim());
        if (result > 0) {
            JOptionPane.showMessageDialog(this, "Category deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            categoryIdTxt.setEditable(true);
            clearFields();
            loadCategoriesToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Could not delete category!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayBtnActionPerformed(ActionEvent evt) {
        loadCategoriesToTable();
    }

    private void searchBtnActionPerformed(ActionEvent evt) {
        String id = searchTxt.getText().trim();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter Category ID to search!", "Missing Input", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Category found = categoryDao.searchById(id);

        if (found == null) {
            JOptionPane.showMessageDialog(this, "Category not found!", "Not Found", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            categoryIdTxt.setEditable(true);
            return;
        }

        categoryIdTxt.setText(found.getCategoryId());
        categoryNameTxt.setText(found.getCategoryName());
        categoryIdTxt.setEditable(false);
        searchTxt.setText("");
    }

    private void categoryTableMouseClicked(MouseEvent evt) {
        int selectedRow = categoryTable.getSelectedRow();
        if (selectedRow != -1) {
            categoryIdTxt.setText(categoryTable.getValueAt(selectedRow, 0).toString());
            categoryNameTxt.setText(categoryTable.getValueAt(selectedRow, 1).toString());
            categoryIdTxt.setEditable(false);
        }
    }

    // Helper Methods
    private void clearFields() {
        categoryIdTxt.setText("");
        categoryNameTxt.setText("");
        searchTxt.setText("");
        categoryIdTxt.setEditable(true);
    }

    private void loadCategoriesToTable() {
        List<Category> list = categoryDao.displayAllCategories();

        String[] cols = {"Category ID", "Category Name"};
        Object[][] data = new Object[list.size()][2];
        
        for (int i = 0; i < list.size(); i++) {
            Category c = list.get(i);
            data[i][0] = c.getCategoryId();
            data[i][1] = c.getCategoryName();
        }
        
        categoryTable.setModel(new DefaultTableModel(data, cols) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CategoryManagement().setVisible(true);
            }
        });
    }

    // Variables declaration
    private JPanel mainPanel;
    private JPanel headerPanel;
    private JLabel titleLabel;
    private JLabel subtitleLabel;
    private JButton backButton;
    private JPanel contentPanel;
    private JPanel formPanel;
    private JLabel categoryIdLabel;
    private JTextField categoryIdTxt;
    private JLabel categoryNameLabel;
    private JTextField categoryNameTxt;
    private JLabel searchLabel;
    private JTextField searchTxt;
    private JButton searchBtn;
    private JPanel buttonPanel;
    private JButton saveBtn;
    private JButton updateBtn;
    private JButton deleteBtn;
    private JButton displayBtn;
    private JPanel tablePanel;
    private JScrollPane tableScrollPane;
    private JTable categoryTable;
}