package view;

import dao.CategoryDao;
import dao.CategoryDaoImpl;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.plaf.basic.BasicButtonUI; // <--- THE CRITICAL IMPORT
import java.awt.*;
import java.awt.event.*;
import model.Category;
import util.UITheme;

/**
 * Professional Category Management interface
 * Fixed for Button Visibility and Windows Look & Feel issues
 */
public class CategoryManagement extends JFrame {
    
    private final CategoryDao categoryDao = new CategoryDaoImpl();
    private static final java.util.logging.Logger logger = 
        java.util.logging.Logger.getLogger(CategoryManagement.class.getName());

    // Components
    private JPanel mainPanel, headerPanel, contentPanel, formPanel, buttonPanel, tablePanel;
    private JLabel titleLabel, subtitleLabel, searchLabel;
    
    // Inputs
    private JTextField categoryIdTxt, categoryNameTxt, searchTxt;
    
    // Buttons
    private JButton backButton, searchBtn, saveBtn, updateBtn, deleteBtn, displayBtn;
    
    // Table
    private JScrollPane tableScrollPane;
    private JTable categoryTable;

    public CategoryManagement() {
        initComponents();
        setLocationRelativeTo(null);
        loadCategoriesToTable();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Category Management - Car Spare Parts System");
        setResizable(true);
        setMinimumSize(new Dimension(1300, 750));

        // Main Layout
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(UITheme.BACKGROUND_LIGHT);

        createHeader();
        createContent();

        getContentPane().add(mainPanel);
        pack();
    }

    private void createHeader() {
        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(UITheme.PRIMARY);
        headerPanel.setPreferredSize(new Dimension(1300, 100));
        headerPanel.setBorder(new EmptyBorder(20, 40, 20, 40));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setOpaque(false);

        titleLabel = new JLabel("Category Management");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(UITheme.TEXT_WHITE);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        subtitleLabel = new JLabel("Manage and organize your product categories");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(255, 255, 255, 180));
        subtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        subtitleLabel.setBorder(new EmptyBorder(5, 0, 0, 0));

        leftPanel.add(titleLabel);
        leftPanel.add(subtitleLabel);

        // Header Button with FIX applied
        backButton = new JButton("← Back to Dashboard");
        backButton.setUI(new BasicButtonUI()); // Fix system override
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(52, 73, 94));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setPreferredSize(new Dimension(180, 40));
        backButton.setBorder(new EmptyBorder(10, 20, 10, 20));
        
        backButton.addActionListener(e -> {
            new AdminDashboard().setVisible(true);
            dispose();
        });
        
        backButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) { backButton.setBackground(new Color(44, 62, 80)); }
            public void mouseExited(MouseEvent evt) { backButton.setBackground(new Color(52, 73, 94)); }
        });

        headerPanel.add(leftPanel, BorderLayout.WEST);
        headerPanel.add(backButton, BorderLayout.EAST);
        mainPanel.add(headerPanel, BorderLayout.NORTH);
    }

    private void createContent() {
        contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(UITheme.BACKGROUND_LIGHT);
        contentPanel.setBorder(new EmptyBorder(30, 40, 30, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        // 1. Form Panel (Left)
        createFormPanel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.35;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0, 0, 0, 20);
        contentPanel.add(formPanel, gbc);

        // 2. Table Panel (Right)
        createTablePanel();
        gbc.gridx = 1;
        gbc.weightx = 0.65;
        gbc.insets = new Insets(0, 0, 0, 0);
        contentPanel.add(tablePanel, gbc);

        mainPanel.add(contentPanel, BorderLayout.CENTER);
    }

    private void createFormPanel() {
        formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(UITheme.SURFACE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(230, 230, 230), 1, true),
            new EmptyBorder(30, 30, 30, 30)
        ));

        // Input Fields
        addFormField("Category ID", categoryIdTxt = createTextField());
        addFormField("Category Name", categoryNameTxt = createTextField());

        // Search Section
        searchLabel = createLabel("Search by ID");
        searchLabel.setBorder(new EmptyBorder(30, 0, 0, 0));
        formPanel.add(searchLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
        searchPanel.setOpaque(false);
        searchPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        searchPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));

        searchTxt = createTextField();
        searchBtn = createButton("Search", UITheme.INFO, new Color(23, 162, 184)); // Cyan
        searchBtn.addActionListener(this::searchBtnActionPerformed);

        searchPanel.add(searchTxt);
        searchPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        searchPanel.add(searchBtn);
        formPanel.add(searchPanel);

        // Buttons
        createButtons();
        formPanel.add(Box.createVerticalGlue());
    }

    private void createButtons() {
        buttonPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        buttonPanel.setOpaque(false);
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));
        buttonPanel.setBorder(new EmptyBorder(30, 0, 0, 0));

        saveBtn = createButton("Save", UITheme.BUTTON_SECONDARY, new Color(23, 162, 184));
        saveBtn.addActionListener(this::saveBtnActionPerformed);

        updateBtn = createButton("Update", UITheme.BUTTON_WARNING, new Color(227, 151, 0));
        updateBtn.setForeground(Color.BLACK); // Better contrast for yellow button
        updateBtn.addActionListener(this::updateBtnActionPerformed);

        deleteBtn = createButton("Delete", UITheme.BUTTON_DANGER, new Color(200, 35, 51));
        deleteBtn.addActionListener(this::deleteBtnActionPerformed);

        displayBtn = createButton("View All", UITheme.BUTTON_PRIMARY, UITheme.PRIMARY);
        displayBtn.addActionListener(this::displayBtnActionPerformed);

        buttonPanel.add(saveBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(displayBtn);
        formPanel.add(buttonPanel);
    }

    private void createTablePanel() {
        tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(UITheme.SURFACE);
        tablePanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(230, 230, 230), 1, true),
            new EmptyBorder(20, 20, 20, 20)
        ));

        JLabel tableTitle = new JLabel("Categories List");
        tableTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tableTitle.setForeground(UITheme.TEXT_PRIMARY);
        tableTitle.setBorder(new EmptyBorder(0, 0, 15, 0));

        categoryTable = new JTable();
        categoryTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        categoryTable.setRowHeight(40);
        categoryTable.setGridColor(new Color(240, 240, 240));
        categoryTable.setSelectionBackground(UITheme.PRIMARY_LIGHT);
        categoryTable.setSelectionForeground(UITheme.TEXT_PRIMARY);
        categoryTable.setShowVerticalLines(false);
        categoryTable.setIntercellSpacing(new Dimension(0, 1));
        
        categoryTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        categoryTable.getTableHeader().setBackground(UITheme.BACKGROUND_DARK);
        categoryTable.getTableHeader().setForeground(UITheme.TEXT_PRIMARY);

        categoryTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                categoryTableMouseClicked(evt);
            }
        });

        tableScrollPane = new JScrollPane(categoryTable);
        tableScrollPane.setBorder(new LineBorder(new Color(230, 230, 230), 1));

        tablePanel.add(tableTitle, BorderLayout.NORTH);
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);
    }

    // --- Helper Methods ---

    private void addFormField(String labelText, JTextField field) {
        JLabel label = createLabel(labelText);
        if (formPanel.getComponentCount() > 0) {
            label.setBorder(new EmptyBorder(15, 0, 0, 0));
        }
        formPanel.add(label);
        formPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        formPanel.add(field);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(UITheme.TEXT_PRIMARY);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JTextField createTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBackground(UITheme.SURFACE);
        field.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1, true),
            new EmptyBorder(10, 12, 10, 12)
        ));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        return field;
    }

    /**
     * MAJOR FIX: Use BasicButtonUI to ensure colors render correctly
     */
    private JButton createButton(String text, Color normalColor, Color hoverColor) {
        JButton btn = new JButton(text);
        btn.setUI(new BasicButtonUI()); // Removes Windows look/feel interference
        
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(normalColor);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(new EmptyBorder(10, 20, 10, 20));

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) { btn.setBackground(hoverColor); }
            public void mouseExited(MouseEvent evt) { btn.setBackground(normalColor); }
        });
        return btn;
    }

    // --- Logic Implementation ---

    private void saveBtnActionPerformed(ActionEvent evt) {
        if (categoryIdTxt.getText().trim().isEmpty() || categoryNameTxt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Category c = new Category();
        c.setCategoryId(categoryIdTxt.getText().trim());
        c.setCategoryName(categoryNameTxt.getText().trim());

        if (categoryDao.create(c) > 0) {
            JOptionPane.showMessageDialog(this, "Category saved successfully!");
            clearFields();
            loadCategoriesToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to save category.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateBtnActionPerformed(ActionEvent evt) {
        if (categoryIdTxt.isEditable()) {
            JOptionPane.showMessageDialog(this, "Please search for a category first.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Category c = new Category();
        c.setCategoryId(categoryIdTxt.getText().trim());
        c.setCategoryName(categoryNameTxt.getText().trim());

        if (categoryDao.update(c) > 0) {
            JOptionPane.showMessageDialog(this, "Category updated successfully!");
            clearFields();
            loadCategoriesToTable();
        }
    }

    private void deleteBtnActionPerformed(ActionEvent evt) {
        if (categoryIdTxt.isEditable()) {
            JOptionPane.showMessageDialog(this, "Please search for a category first.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Delete this category?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (categoryDao.delete(categoryIdTxt.getText().trim()) > 0) {
                JOptionPane.showMessageDialog(this, "Category deleted successfully!");
                clearFields();
                loadCategoriesToTable();
            }
        }
    }

    private void displayBtnActionPerformed(ActionEvent evt) {
        loadCategoriesToTable();
        clearFields();
    }

    private void searchBtnActionPerformed(ActionEvent evt) {
        String id = searchTxt.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter Category ID to search.");
            return;
        }

        Category found = categoryDao.searchById(id);
        if (found != null) {
            categoryIdTxt.setText(found.getCategoryId());
            categoryNameTxt.setText(found.getCategoryName());
            categoryIdTxt.setEditable(false);
            searchTxt.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Category not found.");
            clearFields();
        }
    }

    private void categoryTableMouseClicked(MouseEvent evt) {
        int row = categoryTable.getSelectedRow();
        if (row != -1) {
            categoryIdTxt.setText(categoryTable.getValueAt(row, 0).toString());
            categoryNameTxt.setText(categoryTable.getValueAt(row, 1).toString());
            categoryIdTxt.setEditable(false);
        }
    }

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
            data[i][0] = list.get(i).getCategoryId();
            data[i][1] = list.get(i).getCategoryName();
        }
        
        categoryTable.setModel(new DefaultTableModel(data, cols) {
            public boolean isCellEditable(int row, int column) { return false; }
        });
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(() -> new CategoryManagement().setVisible(true));
    }
}