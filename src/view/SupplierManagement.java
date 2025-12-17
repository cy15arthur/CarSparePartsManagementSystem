package view;

import dao.SupplierDao;
import dao.SupplierDaoImpl;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.plaf.basic.BasicButtonUI; // <--- CRITICAL IMPORT FOR BUTTON COLORS
import java.awt.*;
import java.awt.event.*;
import model.Supplier;
import util.UITheme;

/**
 * Professional Supplier Management interface
 * Fixed for Button Visibility and Windows Look & Feel issues
 */
public class SupplierManagement extends JFrame {
    
    private final SupplierDao supplierDao = new SupplierDaoImpl();
    private static final java.util.logging.Logger logger = 
        java.util.logging.Logger.getLogger(SupplierManagement.class.getName());

    // Components
    private JPanel mainPanel, headerPanel, contentPanel, formPanel, buttonPanel, tablePanel;
    private JLabel titleLabel, subtitleLabel, searchLabel;
    
    // Inputs
    private JTextField supplierIdTxt, supplierNameTxt, contactTxt, emailTxt, addressTxt, searchTxt;
    
    // Buttons
    private JButton backButton, searchBtn, saveBtn, updateBtn, deleteBtn, displayBtn;
    
    // Table
    private JScrollPane tableScrollPane;
    private JTable supplierTable;

    public SupplierManagement() {
        initComponents();
        setLocationRelativeTo(null);
        loadSuppliersToTable();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Supplier Management - Car Spare Parts System");
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

        titleLabel = new JLabel("Supplier Management");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(UITheme.TEXT_WHITE);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        subtitleLabel = new JLabel("Manage supplier information and contacts");
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
        
        setupButtonHover(backButton, new Color(52, 73, 94), new Color(44, 62, 80));

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
        addFormField("Supplier ID", supplierIdTxt = createTextField());
        addFormField("Supplier Name", supplierNameTxt = createTextField());
        addFormField("Contact Number", contactTxt = createTextField());
        addFormField("Email Address", emailTxt = createTextField());
        addFormField("Address", addressTxt = createTextField());

        // Search Section
        searchLabel = createLabel("Search by ID");
        searchLabel.setBorder(new EmptyBorder(20, 0, 0, 0));
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
        buttonPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

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

        JLabel tableTitle = new JLabel("Suppliers List");
        tableTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tableTitle.setForeground(UITheme.TEXT_PRIMARY);
        tableTitle.setBorder(new EmptyBorder(0, 0, 15, 0));

        supplierTable = new JTable();
        supplierTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        supplierTable.setRowHeight(40);
        supplierTable.setGridColor(new Color(240, 240, 240));
        supplierTable.setSelectionBackground(UITheme.PRIMARY_LIGHT);
        supplierTable.setSelectionForeground(UITheme.TEXT_PRIMARY);
        supplierTable.setShowVerticalLines(false);
        supplierTable.setIntercellSpacing(new Dimension(0, 1));
        
        supplierTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        supplierTable.getTableHeader().setBackground(UITheme.BACKGROUND_DARK);
        supplierTable.getTableHeader().setForeground(UITheme.TEXT_PRIMARY);

        supplierTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                supplierTableMouseClicked(evt);
            }
        });

        tableScrollPane = new JScrollPane(supplierTable);
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

        setupButtonHover(btn, normalColor, hoverColor);
        return btn;
    }
    
    private void setupButtonHover(JButton button, Color normalColor, Color hoverColor) {
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) { button.setBackground(hoverColor); }
            public void mouseExited(MouseEvent evt) { button.setBackground(normalColor); }
        });
    }

    // --- Logic Implementation ---

    private void saveBtnActionPerformed(ActionEvent evt) {
        if (!validateFields()) return;

        Supplier s = new Supplier();
        s.setSupplierId(supplierIdTxt.getText().trim());
        s.setSupplierNames(supplierNameTxt.getText().trim());
        s.setSuppliercontact(contactTxt.getText().trim());
        s.setSupplierEmail(emailTxt.getText().trim());
        s.setAddress(addressTxt.getText().trim());

        if (supplierDao.create(s) > 0) {
            JOptionPane.showMessageDialog(this, "Supplier saved successfully!");
            clearFields();
            loadSuppliersToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to save supplier.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateBtnActionPerformed(ActionEvent evt) {
        if (supplierIdTxt.isEditable()) {
            JOptionPane.showMessageDialog(this, "Please search for a supplier first.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!validateFields()) return;
        
        Supplier s = new Supplier();
        s.setSupplierId(supplierIdTxt.getText().trim());
        s.setSupplierNames(supplierNameTxt.getText().trim());
        s.setSuppliercontact(contactTxt.getText().trim());
        s.setSupplierEmail(emailTxt.getText().trim());
        s.setAddress(addressTxt.getText().trim());

        if (supplierDao.update(s) > 0) {
            JOptionPane.showMessageDialog(this, "Supplier updated successfully!");
            supplierIdTxt.setEditable(true);
            clearFields();
            loadSuppliersToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update supplier.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteBtnActionPerformed(ActionEvent evt) {
        if (supplierIdTxt.isEditable()) {
            JOptionPane.showMessageDialog(this, "Please search for a supplier first.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Delete this supplier?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (supplierDao.delete(supplierIdTxt.getText().trim()) > 0) {
                JOptionPane.showMessageDialog(this, "Supplier deleted successfully!");
                supplierIdTxt.setEditable(true);
                clearFields();
                loadSuppliersToTable();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete supplier.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void displayBtnActionPerformed(ActionEvent evt) {
        loadSuppliersToTable();
        clearFields();
    }

    private void searchBtnActionPerformed(ActionEvent evt) {
        String id = searchTxt.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter Supplier ID to search.");
            return;
        }

        Supplier found = supplierDao.searchByCode(id);
        if (found != null) {
            supplierIdTxt.setText(found.getSupplierId());
            supplierNameTxt.setText(found.getSupplierNames());
            contactTxt.setText(found.getSuppliercontact());
            emailTxt.setText(found.getSupplierEmail());
            addressTxt.setText(found.getAddress());
            supplierIdTxt.setEditable(false);
            searchTxt.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Supplier not found.");
            clearFields();
        }
    }
    
    private boolean validateFields() {
        if (supplierIdTxt.getText().trim().isEmpty() || supplierNameTxt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "ID and Name are required!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Basic email validation
        if (!emailTxt.getText().trim().isEmpty() && !emailTxt.getText().contains("@")) {
             JOptionPane.showMessageDialog(this, "Invalid Email format!", "Validation Error", JOptionPane.ERROR_MESSAGE);
             return false;
        }
        return true;
    }

    private void supplierTableMouseClicked(MouseEvent evt) {
        int row = supplierTable.getSelectedRow();
        if (row != -1) {
            supplierIdTxt.setText(supplierTable.getValueAt(row, 0).toString());
            supplierNameTxt.setText(supplierTable.getValueAt(row, 1).toString());
            contactTxt.setText(supplierTable.getValueAt(row, 2).toString());
            emailTxt.setText(supplierTable.getValueAt(row, 3).toString());
            addressTxt.setText(supplierTable.getValueAt(row, 4).toString());
            supplierIdTxt.setEditable(false);
        }
    }

    private void clearFields() {
        supplierIdTxt.setText("");
        supplierNameTxt.setText("");
        contactTxt.setText("");
        emailTxt.setText("");
        addressTxt.setText("");
        searchTxt.setText("");
        supplierIdTxt.setEditable(true);
    }

    private void loadSuppliersToTable() {
        List<Supplier> list = supplierDao.displayAllSuppliers();
        String[] cols = {"Supplier ID", "Name", "Contact", "Email", "Address"};
        Object[][] data = new Object[list.size()][5];
        
        for (int i = 0; i < list.size(); i++) {
            Supplier s = list.get(i);
            data[i][0] = s.getSupplierId();
            data[i][1] = s.getSupplierNames();
            data[i][2] = s.getSuppliercontact();
            data[i][3] = s.getSupplierEmail();
            data[i][4] = s.getAddress();
        }
        
        supplierTable.setModel(new DefaultTableModel(data, cols) {
            public boolean isCellEditable(int row, int column) { return false; }
        });
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(() -> new SupplierManagement().setVisible(true));
    }
}